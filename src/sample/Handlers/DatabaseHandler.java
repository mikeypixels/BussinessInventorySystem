package sample.Handlers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONException;
import org.json.JSONObject;
import sample.DashboardController;
import sample.Objects.*;

import java.sql.*;
import java.time.LocalDate;

public class DatabaseHandler {
    public static Connection conn;
    private static String url = "jdbc:mysql://localhost:3306/inventory_system";
    private static String user = "root";
    private static String password = "";

    private static Connection createConn() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public LoginResult login(String username, String passwd) {
        String userSql = "SELECT * FROM USERS WHERE USERNAME=? AND PASSWORD=?";
        LoginResult result = null;
        try {
            PreparedStatement ps = createConn().prepareStatement(userSql);
            ps.setString(1, username);
            ps.setString(2, passwd);
            ResultSet rs = ps.executeQuery();
            JSONObject userCredentials = new JSONObject();

            if (rs.first()) {
                userCredentials.put("status", rs.getString("status"));
                userCredentials.put("role", rs.getString("role"));
                userCredentials.put("password", rs.getString("password"));
                userCredentials.put("fname", rs.getString("f_name"));
                userCredentials.put("lname", rs.getString("l_name"));
                userCredentials.put("user_id", rs.getString("id"));

                result = new LoginResult(true, userCredentials);
            } else {
                result = new LoginResult(false, userCredentials);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JSONException j) {
            j.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public String getTodaySales(){

        DashboardController dc = new DashboardController();
        Double cost = 0.0;
        String amount = "";

        try {

            String salesSql = "SELECT * FROM sales";
            PreparedStatement ps = createConn().prepareStatement(salesSql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if(dc.getCurrentDate().equals(rs.getString("date"))){
                    cost = cost + rs.getDouble("cost");
                }
            }

            amount = String.format("%,.0f", cost);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return amount;
    }

    public String getTodayExpenses(){

        DashboardController dc = new DashboardController();
        Double cost = 0.0;
        String amount = "";

        try {

            String salesSql = "SELECT * FROM expenses";
            PreparedStatement ps = createConn().prepareStatement(salesSql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if(dc.getCurrentDate().equals(rs.getString("date"))){
                    cost = cost + rs.getDouble("cost");
                }
            }

            amount = String.format("%,.0f", cost);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return amount;
    }

    public String getTodayPurchases(){

        DashboardController dc = new DashboardController();
        Double cost = 0.0;
        String amount = "";

        try {

            String salesSql = "SELECT * FROM purchases";
            PreparedStatement ps = createConn().prepareStatement(salesSql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                if(dc.getCurrentDate().equals(rs.getString("date"))){
                    cost = cost + rs.getDouble("cost");
                }
            }

            amount = String.format("%,.0f", cost);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return amount;
    }

    public boolean addPurchase(int product_id, int quantity, double cost, LocalDate date, int user_id) {
        String regExpSql = "INSERT INTO purchases(product_id,quantity,cost,date,user_id) VALUES(?,?,?,?,?)";
        boolean success = false;

        try {

//            System.out.println(product_id + " " + store_id + " " + quantity + " " + cost + " " + date);

            PreparedStatement ps = createConn().prepareStatement(regExpSql);
            ps.setInt(1, product_id);
            ps.setInt(2, quantity);
            ps.setDouble(3, cost);
            ps.setDate(4, java.sql.Date.valueOf(date));
            ps.setInt(5, user_id);

            ps.execute();

            success = addStoreStock(product_id, quantity);

//            success = product_id!=1 || addStock(store_id,product_id,quantity);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return success;
    }

    public ObservableList<Product> loadProductList() {
        ObservableList<Product> products = FXCollections.observableArrayList();
        String storesSql = "SELECT * FROM products";

        try {
            PreparedStatement ps = createConn().prepareStatement(storesSql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                products.add(new Product(String.valueOf(rs.getInt("product_id")), rs.getString("product_name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        System.out.println(products.size());
        return products;
    }

    public ObservableList<String> loadProductNames() {
        ObservableList<String> products = FXCollections.observableArrayList();
        String storesSql = "SELECT * FROM products";

        try {
            PreparedStatement ps = createConn().prepareStatement(storesSql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                products.add(rs.getString("product_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        System.out.println(products.size());

        return products;
    }

    public boolean addSale(int product_id, int quantity, double cost, LocalDate date, int user_id) {
        String regExpSql = "INSERT INTO sales(product_id,quantity,cost,date, user_id) VALUES(?,?,?,?,?)";
//        int store_id = 0;
        boolean success = false;

        try {

            PreparedStatement ps = createConn().prepareStatement(regExpSql);
            ps.setInt(1, product_id);
            ps.setInt(2, quantity);
            ps.setDouble(3, cost);
            ps.setDate(4, java.sql.Date.valueOf(date));
            ps.setInt(5, user_id);

            ps.execute();

            success = reduceStock(product_id, quantity);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return success;
    }

    public ObservableList<Stock> getStocks() {
        ObservableList<Stock> stocks = FXCollections.observableArrayList();

        try {

            String stockSql = "SELECT id,products.product_id,available_quantity,product_name FROM STOCKS,PRODUCTS WHERE stocks.product_id=products.product_id";
            PreparedStatement ps = createConn().prepareStatement(stockSql);
            ResultSet rd = ps.executeQuery();
            while (rd.next()) {
                stocks.add(new Stock(rd.getInt("id"), rd.getString("Product_name"), rd.getInt("product_id"), rd.getInt("available_quantity")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return stocks;
    }

    public ObservableList<Stock> getStoreStocks() {
        ObservableList<Stock> stocks = FXCollections.observableArrayList();
        int i = 0;

        try {

            String stockSql = "SELECT id,products.product_id,available_quantity,product_name FROM stock_store,products WHERE stock_store.product_id=products.product_id";
            PreparedStatement ps = createConn().prepareStatement(stockSql);
            ResultSet rd = ps.executeQuery();
            while (rd.next()) {
                stocks.add(new Stock(rd.getInt("id"), rd.getString("Product_name"), rd.getInt("product_id"), rd.getInt("available_quantity")));
//                System.out.println(stocks.get(i).getProduct_name());
//                i++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return stocks;
    }

    public boolean addStock(String product_name, int quantity) {

        int product_id = 0;

        for (Product product : loadProductList()) {
            if (product.getProduct_name().equals(product_name)) {
                product_id = Integer.parseInt(product.getId());
            }
        }

        System.out.println("This is the database id: " + product_id);

        String store_stockSql = "SELECT * FROM stock_store WHERE product_id='" + product_id + "'";
        String stockSql = "SELECT * FROM stocks WHERE product_id='" + product_id + "'";
        boolean success;
        int new_quantity = 0;
        int store_new_quantity = 0;

        try {
            PreparedStatement pd = createConn().prepareStatement(stockSql);
            ResultSet rs = pd.executeQuery();
            if (rs.first()) {
                new_quantity = rs.getInt("available_quantity");
            }
            new_quantity = new_quantity + quantity;

            PreparedStatement px = createConn().prepareStatement(store_stockSql);
            ResultSet rd = px.executeQuery();
            if (rd.first()) {
                store_new_quantity = rs.getInt("available_quantity");
            }
            store_new_quantity = store_new_quantity - quantity;

            String sql = "UPDATE stocks SET available_quantity='" + new_quantity + "' WHERE product_id='" + product_id + "'";
            PreparedStatement ps = createConn().prepareStatement(sql);
            ps.execute();

            String store_sql = "UPDATE stock_store SET available_quantity='" + store_new_quantity + "' WHERE product_id='" + product_id + "'";
            PreparedStatement store_ps = createConn().prepareStatement(store_sql);
            store_ps.execute();

            success = true;
        } catch (SQLException e) {
            success = false;
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return success;
    }

    public boolean addStoreStock(int product_id, int quantity) {
        String stockSql = "SELECT * FROM stock_store WHERE  product_id='" + product_id + "'";
        boolean success;
        int new_quantity = 0;

        try {
            PreparedStatement pd = createConn().prepareStatement(stockSql);
            ResultSet rs = pd.executeQuery();
            if (rs.first()) {
                new_quantity = rs.getInt("available_quantity");
            }
            new_quantity = new_quantity + quantity;

            String sql = "UPDATE stock_store SET available_quantity='" + new_quantity + "' WHERE product_id='" + product_id + "'";
            PreparedStatement ps = createConn().prepareStatement(sql);
            ps.execute();
            success = true;
        } catch (SQLException e) {
            success = false;
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return success;
    }

    public boolean reduceStock(int product_id, int quantity) {
        String stockSql = "SELECT * FROM stocks WHERE product_id='" + product_id + "'";
        boolean success;
        int new_quantity = 0;

        try {
            PreparedStatement pd = createConn().prepareStatement(stockSql);
            ResultSet rs = pd.executeQuery();
            if (rs.first()) {
                new_quantity = rs.getInt("available_quantity");
            }
            new_quantity = new_quantity - quantity;

            String sql = "UPDATE stocks SET available_quantity='" + new_quantity + "' WHERE product_id='" + product_id + "'";
            PreparedStatement ps = createConn().prepareStatement(sql);
            ps.execute();
            success = true;
        } catch (SQLException e) {
            success = false;
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return success;
    }

    public boolean reduceStoreStock(int product_id, int quantity) {
        String stockSql = "SELECT * FROM store_stocks WHERE product_id='" + product_id + "'";
        boolean success;
        int new_quantity = 0;

        try {
            PreparedStatement pd = createConn().prepareStatement(stockSql);
            ResultSet rs = pd.executeQuery();
            if (rs.first()) {
                new_quantity = rs.getInt("available_quantity");
            }
            new_quantity = new_quantity - quantity;

            String sql = "UPDATE store_stocks SET available_quantity='" + new_quantity + "' WHERE product_id='" + product_id + "'";
            PreparedStatement ps = createConn().prepareStatement(sql);
            ps.execute();
            success = true;
        } catch (SQLException e) {
            success = false;
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return success;
    }

    public boolean addExpense(String name, double amount, String description, LocalDate date, int user_id) {
        String addExpSql = "INSERT INTO expenses(date,expense_name,description,cost,user_id) VALUES(?,?,?,?,?)";
//        String storeIdSlq = "SELECT * FROM STORES WHERE store_name='"+store+"'";
//        int store_id = 0;
        boolean success = false;

        try {
//            PreparedStatement pd = createConn().prepareStatement(storeIdSlq);
//            ResultSet rs = pd.executeQuery();
//            if(rs.first()){
//                store_id = rs.getInt("store_id");
//            }

            PreparedStatement ps = createConn().prepareStatement(addExpSql);
//            ps.setInt(1,store_id);
            ps.setDate(1, java.sql.Date.valueOf(date));
            ps.setString(2, name);
            ps.setString(3, description);
            ps.setDouble(4, amount);
            ps.setInt(5, user_id);

            ps.execute();
            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return success;
    }

    public ObservableList<Expense> loadExpenses() {
        ObservableList<Expense> expenses = FXCollections.observableArrayList();
        String expensesSql = "SELECT * FROM expenses ORDER BY DATE DESC";

        try {
            PreparedStatement ps = createConn().prepareStatement(expensesSql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("expense_name");
                String description = rs.getString("description");
                String date = rs.getString("date");
                String amount = String.format("%,.0f", rs.getDouble("cost"));

                expenses.add(new Expense(id, name, amount, description, date));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return expenses;
    }

    public int getQuantity(String product) {
        int quantity = 0;
        int product_id = 0;

        System.out.println("The product id of the product is: " + product);

        try {
            String productSql = "SELECT * FROM products WHERE product_name='" + product + "'";
            PreparedStatement pd = createConn().prepareStatement(productSql);
            ResultSet rs = pd.executeQuery();
            if (rs.first()) {
                product_id = rs.getInt("product_id");
            }

            System.out.println("The product id of the product is: " + product_id);

            String stockSql = "SELECT * FROM STOCKS WHERE product_id='" + product_id + "'";
            PreparedStatement ps = createConn().prepareStatement(stockSql);
            ResultSet rd = ps.executeQuery();
            if (rd.first()) {
                quantity = rd.getInt("available_quantity");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return quantity;
    }

    public int getStoreQuantity(String product) {
        int quantity = 0;
        int product_id = 0;

        try {
            String productSql = "SELECT * FROM products WHERE product_name='" + product + "'";
            PreparedStatement pd = createConn().prepareStatement(productSql);
            ResultSet rs = pd.executeQuery();
            if (rs.first()) {
                product_id = Integer.parseInt(rs.getString("product_id"));
            }

            String stockSql = "SELECT * FROM stock_store WHERE product_id='" + product_id + "'";
            PreparedStatement ps = createConn().prepareStatement(stockSql);
            ResultSet rd = ps.executeQuery();
            if (rd.first()) {
                quantity = rd.getInt("available_quantity");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return quantity;
    }

    public boolean addUser(String fname, String lname, String uname, String password, String gender, String role, String status) {
        String userSql = "INSERT INTO USERS (f_name,l_name,username,password,gender,role,status) VALUES(?,?,?,?,?,?,?)";
        boolean result = false;

        try {
            PreparedStatement ps = createConn().prepareStatement(userSql);
            ps.setString(1, fname);
            ps.setString(2, lname);
            ps.setString(3, uname);
            ps.setString(4, password);
            ps.setString(5, gender);
            ps.setString(6, role);
            ps.setString(7, status);

            ps.execute();
            result = true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public void updateUserStatus(String action, int id) {
        String status;
        if (action.equals("activate")) {
            status = "active";
        } else {
            status = "deactive";
        }

        System.out.println(action);
        System.out.println(id);
        System.out.println(status);

        String statusSql = "UPDATE USERS SET status='" + status + "' WHERE ID='" + id + "'";

        try {
            PreparedStatement ps = createConn().prepareStatement(statusSql);
            ps.execute();
            System.out.println("success");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public boolean updatePassword(String password, int id) {
        String passSql = "UPDATE USERS SET PASSWORD='" + password + "' WHERE ID=" + id;
        boolean success = false;
        try {
            PreparedStatement ps = createConn().prepareStatement(passSql);
            ps.execute();
            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return success;
    }

    public boolean deleteUser(int id) {
        String userSql = "DELETE FROM USERS WHERE ID='" + id + "'";
        boolean success = false;
        try {
            PreparedStatement ps = createConn().prepareStatement(userSql);
            ps.execute();
            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return success;
    }

    public ObservableList<User> getUsers(String level) {
        ObservableList<User> users = FXCollections.observableArrayList();
        String usersSql;

        if (level.equals("Main Admin"))
            usersSql = "SELECT * FROM USERS";
        else
            usersSql = "SELECT * FROM USERS WHERE role='worker' ";

        try {
            PreparedStatement ps = createConn().prepareStatement(usersSql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                users.add(new User("USER/" + rs.getInt("id"), rs.getString("f_name") + " " + rs.getString("l_name"), rs.getString("status"), rs.getString("role")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    public ObservableList<Sale> loadSales() {
        ObservableList<Sale> sales = FXCollections.observableArrayList();

        try {

            String salesSql = "SELECT * FROM sales ORDER BY DATE DESC";
            PreparedStatement ps = createConn().prepareStatement(salesSql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int product_id = rs.getInt("product_id");
                String quantity = rs.getString("quantity");
                String date = rs.getString("date");
                String cost = String.format("%,.0f", rs.getDouble("cost"));

                String productSql = "SELECT * FROM products WHERE product_id='" + product_id+"'";
                PreparedStatement pc = createConn().prepareStatement(productSql);
                ResultSet rc = pc.executeQuery();
                String product_name = "";
                if (rc.first()) {
                    product_name = rc.getString("product_name");
                }
                sales.add(new Sale(String.valueOf(id), product_name, quantity, cost, date));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return sales;
    }

    public ObservableList<Purchase> loadPurchases() {
        ObservableList<Purchase> purchases = FXCollections.observableArrayList();

        try {

            String salesSql = "SELECT * FROM purchases ORDER BY DATE DESC";
            PreparedStatement ps = createConn().prepareStatement(salesSql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int product_id = rs.getInt("product_id");
                String quantity = rs.getString("quantity");
                String date = rs.getString("date");
                String cost = String.format("%,.0f", rs.getDouble("cost"));

                String productSql = "SELECT * FROM products WHERE product_id=" + product_id;
                PreparedStatement pc = createConn().prepareStatement(productSql);
                ResultSet rc = pc.executeQuery();
                String product_name = "";
                if (rc.first()) {
                    product_name = rc.getString("product_name");
                }
                purchases.add(new Purchase(id, product_name, quantity, cost, date));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return purchases;
    }

    public boolean deleteExpense(int id) {
        String delSql = "DELETE FROM expenses WHERE id='" + id + "'";
        boolean success = false;

        try {
            PreparedStatement ps = createConn().prepareStatement(delSql);
            ps.execute();

            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    public boolean deleteSale(int id) {
        String delSql = "DELETE FROM sales WHERE id='" + id + "'";
        boolean success = false;

        try {
            PreparedStatement ps = createConn().prepareStatement(delSql);
            ps.execute();

            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    public boolean deletePurchase(int id) {
        String delSql = "DELETE FROM purchases WHERE id='" + id + "'";
        boolean success = false;

        try {
            PreparedStatement ps = createConn().prepareStatement(delSql);
            ps.execute();

            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

//    public Expense viewExpense(int id) {
//        String editSql = "SELECT * FROM expenses WHERE id=" + id;
//        Expense expense = null;
//        try {
//            PreparedStatement ps = createConn().prepareStatement(editSql);
//            ResultSet rs = ps.executeQuery();
//            if (rs.first()) {
//                int store_id = rs.getInt("store_id");
//
//                String storeIdSlq = "SELECT * FROM stores WHERE store_id="+store_id;
//                PreparedStatement pd = createConn().prepareStatement(storeIdSlq);
//                ResultSet rd = pd.executeQuery();
//                String store = "";
//                if(rd.first()){
//                    store = rd.getString("store_name");
//                }
//
//                expense = new Expense(rs.getInt("id"), rs.getString("expense_name"), store,String.format("%,.0f", rs.getDouble("cost")),
//                        rs.getString("description"), rs.getString("date"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return expense;
//    }

    public boolean editExpense(int id, String name, double amount, String description, LocalDate date) {
        Date edate = java.sql.Date.valueOf(date);
        boolean success;

        try {

            String sql = "UPDATE expenses SET expense_name='" + name + "',cost=" + amount + ",description='" + description + "',date='" + edate + "' WHERE ID='" + id + "'";
            PreparedStatement ps = createConn().prepareStatement(sql);
            ps.execute();
            success = true;
        } catch (SQLException e) {
            success = false;
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return success;
    }

    public boolean editPurchase(int id, String product_name, int quantity, double amount, LocalDate date, String state, int extra) {
        Date edate = java.sql.Date.valueOf(date);
        boolean success;

        try {
            String storeIdSlq = "SELECT * FROM products WHERE product_name='" + product_name + "'";
            int product_id = 0;
            PreparedStatement pd = createConn().prepareStatement(storeIdSlq);
            ResultSet rs = pd.executeQuery();
            if (rs.first()) {
                product_id = rs.getInt("product_id");
            }
            String sql = "UPDATE purchases SET product_id='" + product_id + "',quantity='" + quantity + "',cost=" + amount + ",date='" + edate + "' WHERE ID='" + id + "'";
            PreparedStatement ps = createConn().prepareStatement(sql);
            ps.execute();
            String updateStockSql = "SELECT * FROM stock_store WHERE product_id='" + product_id + "'";
            PreparedStatement pt = createConn().prepareStatement(updateStockSql);
            ResultSet rt = pt.executeQuery();
            int stock_quantity = 0;
            if (rt.first()) {
                stock_quantity = rt.getInt("available_quantity");
            }
            if (state.equals("add")) {
                stock_quantity = stock_quantity + extra;
            } else if (state.equals("sub")) {
                stock_quantity = stock_quantity - extra;
            }
            String newStockSql = "UPDATE stock_store SET available_quantity =" + stock_quantity + " WHERE product_id='" + product_id + "'";
            PreparedStatement pr = createConn().prepareStatement(newStockSql);
            pr.execute();

            success = true;
        } catch (SQLException e) {
            success = false;
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return success;
    }

    public boolean editSale(int id, String product_name, int quantity, double amount, LocalDate date, String state, int extra) {
        Date edate = java.sql.Date.valueOf(date);
        boolean success;

        try {
            String storeIdSlq = "SELECT * FROM products WHERE product_name='" + product_name + "'";
            int product_id = 0;
            PreparedStatement pd = createConn().prepareStatement(storeIdSlq);
            ResultSet rs = pd.executeQuery();
            if (rs.first()) {
                product_id = rs.getInt("product_id");
            }

            System.out.println("The product_id in editsales is: " + product_id);

            String sql = "UPDATE sales SET product_id='" + product_id + "',quantity='" + quantity + "',cost=" + amount + ",date='" + edate + "' WHERE ID='" + id + "'";
            PreparedStatement ps = createConn().prepareStatement(sql);
            ps.execute();

            System.out.println("The quantity in editsales to be updated is: " + quantity);

            String updateStockSql = "SELECT * FROM stocks WHERE product_id='" + product_id + "'";
            PreparedStatement pt = createConn().prepareStatement(updateStockSql);
            ResultSet rt = pt.executeQuery();
            int stock_quantity = 0;
            if (rt.first()) {
                stock_quantity = rt.getInt("available_quantity");
            }

            System.out.println("The stock quantity in editsales is: " + stock_quantity);

            if (state.equals("add")) {
                stock_quantity = stock_quantity + extra;
            } else if (state.equals("sub")) {
                stock_quantity = stock_quantity - extra;
            }

            System.out.println("The stock quantity after in editsales is: " + stock_quantity);

            String newStockSql = "UPDATE stocks SET available_quantity ='" + stock_quantity + "' WHERE product_id='" + product_id + "'";
            PreparedStatement pr = createConn().prepareStatement(newStockSql);
            pr.execute();

            success = true;
        } catch (SQLException e) {
            success = false;
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return success;
    }

    //    public PurchaseEdit viewPurchase(int id) {
//        String editSql = "SELECT * FROM purchases WHERE id=" + id;
//        PurchaseEdit purchaseEdit = null;
//        try {
//            PreparedStatement ps = createConn().prepareStatement(editSql);
//            ResultSet rs = ps.executeQuery();
//            if (rs.first()) {
//                int product_id = rs.getInt("product_id");
//
//                String purchaseIdSlq = "SELECT * FROM products WHERE product_id="+product_id;
//                PreparedStatement pd = createConn().prepareStatement(purchaseIdSlq);
//                ResultSet rd = pd.executeQuery();
//                String product_name = "";
//                if(rd.first()){
//                    product_name = rd.getString("product_name");
//                }
//
//                purchaseEdit = new PurchaseEdit(product_name,rs.getString("quantity"),
//                        String.format("%,.0f", rs.getDouble("cost")),rs.getString("date"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return purchaseEdit;
//    }
//
    public PurchaseEdit viewSale(int id) {
        String editSql = "SELECT * FROM sales WHERE id='" + id + "'";
        PurchaseEdit purchaseEdit = null;
        try {
            PreparedStatement ps = createConn().prepareStatement(editSql);
            ResultSet rs = ps.executeQuery();
            if (rs.first()) {
                int product_id = rs.getInt("product_id");

                String purchaseIdSlq = "SELECT * FROM products WHERE product_id='" + product_id + "'";
                PreparedStatement pd = createConn().prepareStatement(purchaseIdSlq);
                ResultSet rd = pd.executeQuery();
                String product_name = "";
                if (rd.first()) {
                    product_name = rd.getString("product_name");
                }

                purchaseEdit = new PurchaseEdit(product_name, rs.getString("quantity"),
                        String.format("%,.0f", rs.getDouble("cost")), rs.getString("date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return purchaseEdit;
    }

    //
    public ObservableList<Evaluation> evaluation() {

        ObservableList<Evaluation> evaluations = FXCollections.observableArrayList();

        try {

            String evaluationSql = "select sum(cost) as p_total,MONTHNAME(date) AS p_date,YEAR (date) " +
                    "AS p_year from purchases Group by Month(date),YEAR(date) ORDER BY YEAR(date) desc,MONTH (date) desc";

            PreparedStatement ps = createConn().prepareStatement(evaluationSql);
            ResultSet rs = ps.executeQuery();
            ObservableList<EvaluationPurchase> evaluationPurchases = FXCollections.observableArrayList();
            while (rs.next()) {
                String date = rs.getString("p_date") + "/" + rs.getString("p_year");

                evaluationPurchases.add(new EvaluationPurchase(date, rs.getString("p_total")));
            }

//            System.out.println("Size of purchases is: " + evaluationPurchases.size());
//
            String expense_sql = "select sum(cost)as e_amount,MONTHNAME(date) as e_month,YEAR(date) as e_year from expenses GROUP BY MONTH(date) ORDER BY YEAR(date) desc,MONTH (date) desc";
            PreparedStatement pr = createConn().prepareStatement(expense_sql);
            ResultSet rr = pr.executeQuery();
            ObservableList<EvaluationExpense> evaluationExpenses = FXCollections.observableArrayList();
            System.out.println("Size of expenses before is: " + evaluationExpenses.size());
            while (rr.next()) {
                String e_date = rr.getString("e_month") + "/" + rr.getString("e_year");
                evaluationExpenses.add(new EvaluationExpense(e_date, rr.getDouble("e_amount")));
            }

//            System.out.println("Size of expenses after is: " + evaluationExpenses.size());

            String crates_income_sql = "select sum(cost)as crates,MONTHNAME(date) as crate_month,YEAR(date) as crate_year from sales GROUP BY MONTH(date) ORDER BY YEAR(date) desc,MONTH (date) desc";
            PreparedStatement pb = createConn().prepareStatement(crates_income_sql);
            ResultSet rb = pb.executeQuery();
            ObservableList<EvaluationItems> evaluationCrates = FXCollections.observableArrayList();
            System.out.println("Size of sales before is: " + evaluationCrates.size());
            double crates_income;
            while (rb.next()) {
                String crate_date = rb.getString("crate_month") + "/" + rb.getString("crate_year");
                crates_income = rb.getInt("crates");
                evaluationCrates.add(new EvaluationItems(crate_date, crates_income));
//                evaluationCrates.add(new EvaluationItems(crate_date,crates_income));
            }
//            System.out.println("Size of sales after is: " + evaluationCrates.size());

            double income = 0;
            double profit = 0;

//            if(evaluationCrates.size() > evaluationExpenses.size() && evaluationCrates.size() > evaluationPurchases.size()){
//                if(evaluationExpenses.size() > evaluationPurchases.size()){
//                    if(evaluationPurchases.size() != 0){
//                        for (int i = 0; i < evaluationCrates.size(); i++) {
//                            for (int j = 0; j < evaluationPurchases.size(); j++) {
//                                for(int k = 0; k < evaluationExpenses.size(); k++){
//                                    if(evaluationCrates.get(i).date.equals(evaluationExpenses.get(k).date) && evaluationCrates.get(i).date.equals(evaluationPurchases.get(j).date)){
//                                        income = evaluationCrates.get(i).amount;
//                                        profit = income - evaluationExpenses.get(k).amount - Double.parseDouble(evaluationPurchases.get(j).total_number);
//                                        System.out.println(evaluationPurchases.get(j).date + " " + evaluationCrates.get(i).date + " " + evaluationExpenses.get(k).date + " " + income + " " + profit);
//
//                                        evaluations.add(new Evaluation(evaluationCrates.get(i).date, evaluationPurchases.get(j).total_number,
//                                                String.format("%,.0f", evaluationExpenses.get(k).amount), String.format("%,.0f", income), String.format("%,.0f", profit)));
//                                    } else if (evaluationExpenses.get(k).date.equals(evaluationPurchases.get(j).date) && !evaluationCrates.get(i).date.equals(evaluationPurchases.get(j).date)) {
//                                        income = evaluationCrates.get(i).amount;
//                                        profit = income - evaluationExpenses.get(k).amount - Double.parseDouble(evaluationPurchases.get(j).total_number);
//                                        System.out.println(evaluationPurchases.get(j).date + " " + evaluationCrates.get(i).date + " " + evaluationExpenses.get(k).date + " " + income + " " + profit);
//
//                                        evaluations.add(new Evaluation(evaluationExpenses.get(k).date, evaluationPurchases.get(j).total_number,
//                                                String.format("%,.0f", evaluationExpenses.get(k).amount), String.format("%,.0f", income), String.format("%,.0f", profit)));
//                                    }else if(evaluationCrates.get(i).date.equals(evaluationExpenses.get(k).date) && !evaluationCrates.get(i).date.equals(evaluationPurchases.get(j).date)){
//                                        income = evaluationCrates.get(i).amount;
//                                        profit = income - evaluationExpenses.get(k).amount;
//                                        System.out.println(evaluationPurchases.get(j).date + " " + evaluationCrates.get(i).date + " " + evaluationExpenses.get(k).date + " " + income + " " + profit);
//
//                                        evaluations.add(new Evaluation(evaluationExpenses.get(k).date, "0",
//                                                String.format("%,.0f", evaluationExpenses.get(k).amount), String.format("%,.0f", income), String.format("%,.0f", profit)));
//                                    }else if(evaluationCrates.get(i).date.equals(evaluationPurchases.get(j).date) && evaluationCrates.get(i).date.equals(evaluationExpenses.get(k).date)){
//                                        income = evaluationCrates.get(i).amount;
//                                        profit = income - Double.parseDouble(evaluationPurchases.get(j).total_number.replaceAll(",", ""));
//                                        System.out.println(evaluationPurchases.get(j).date + " " + evaluationCrates.get(i).date + " " + evaluationExpenses.get(k).date + " " + income + " " + profit);
//
//                                        evaluations.add(new Evaluation(evaluationCrates.get(i).date, evaluationPurchases.get(k).total_number,
//                                                String.format("%,.0f", "0"), String.format("%,.0f", income), String.format("%,.0f", profit)));
//                                    }
//                                }
//                            }
//                        }
//                    }else{
//
//                    }
//                }else{
//
//                }
//            }else if(evaluationCrates.size() > evaluationExpenses.size() && evaluationCrates.size() < evaluationPurchases.size()){
//
//            }else if( evaluationCrates.size() < evaluationExpenses.size() && evaluationCrates.size() > evaluationPurchases.size()){
//
//            }else if(evaluationCrates.size() < evaluationExpenses.size() && evaluationCrates.size() < evaluationPurchases.size()){
//
//            }

//            for (int i = 0; i < evaluationPurchases.size(); i++) {
//                    income = evaluationCrates.get(i).amount;
//                    profit = income - evaluationExpenses.get(i).amount - Double.parseDouble(evaluationPurchases.get(i).total_number);
//                    System.out.println(evaluationPurchases.get(i).date + " " + evaluationCrates.get(i).date + " " + evaluationExpenses.get(i).date + " " + income + " " + profit);
//
//                    evaluations.add(new Evaluation(evaluationCrates.get(i).date, String.format("%,.0f", Double.parseDouble(evaluationPurchases.get(i).total_number)),
//                            String.format("%,.0f", evaluationExpenses.get(i).amount), String.format("%,.0f", income), String.format("%,.0f", profit)));
//            }

            System.out.println("this is the evaluation purchase size: " + evaluationPurchases.size());

            for (int i = 0; i < evaluationPurchases.size(); i++) {
                if (evaluationPurchases.get(i).date.equals(evaluationExpenses.get(i).date) && evaluationCrates.get(i).date.equals(evaluationPurchases.get(i).date)) {
                    income = evaluationCrates.get(i).amount;
                    profit = income - evaluationExpenses.get(i).amount - Double.parseDouble(evaluationPurchases.get(i).total_number);
                    System.out.println(evaluationPurchases.get(i).date + " " + evaluationCrates.get(i).date + " " + evaluationExpenses.get(i).date + " " + income + " " + profit + " all equals");

                    evaluations.add(new Evaluation(evaluationPurchases.get(i).date, String.format("%,.0f", Double.parseDouble(evaluationPurchases.get(i).total_number)),
                            String.format("%,.0f", evaluationExpenses.get(i).amount), String.format("%,.0f", income), String.format("%,.0f", profit)));
                }
//                else if(evaluationPurchases.get(i).date.equals(evaluationCrates.get(i).date) && !evaluationPurchases.get(i).date.equals(evaluationExpenses.get(i).date)){
//                    income = evaluationCrates.get(i).amount;
//                    profit = income - evaluationExpenses.get(i).amount - Double.parseDouble(evaluationPurchases.get(i).total_number);
//                    System.out.println(evaluationPurchases.get(i).date + " " + evaluationCrates.get(i).date + " " + evaluationExpenses.get(i).date + " " + income + " " + profit + " sales and purchases equal");
//
//                    evaluations.add(new Evaluation(evaluationPurchases.get(i).date, String.format("%,.0f", Double.parseDouble(evaluationPurchases.get(i).total_number)),
//                            String.format("%,.0f", 0.0), String.format("%,.0f", income), String.format("%,.0f", profit)));
//                }else if(evaluationPurchases.get(i).date.equals(evaluationExpenses.get(i).date) && !evaluationPurchases.get(i).date.equals(evaluationCrates.get(i).date)){
////                    income = evaluationCrates.get(i).amount;
//                    profit = 0.0 - evaluationExpenses.get(i).amount - Double.parseDouble(evaluationPurchases.get(i).total_number);
//                    System.out.println(evaluationPurchases.get(i).date + " " + evaluationCrates.get(i).date + " " + evaluationExpenses.get(i).date + " " + income + " " + profit + " expenses and purchases equal");
//
//                    evaluations.add(new Evaluation(evaluationPurchases.get(i).date, String.format("%,.0f", Double.parseDouble(evaluationPurchases.get(i).total_number)),
//                            String.format("%,.0f", evaluationExpenses.get(i).amount), String.format("%,.0f", 0.0), String.format("%,.0f", profit)));
//                }else{
//                    income = evaluationCrates.get(i).amount;
//                    profit = income - evaluationExpenses.get(i).amount - Double.parseDouble(evaluationPurchases.get(i).total_number);
//                    System.out.println(evaluationPurchases.get(i).date + " " + evaluationCrates.get(i).date + " " + evaluationExpenses.get(i).date + " " + income + " " + profit + " none equal");
//
//                    evaluations.add(new Evaluation(evaluationPurchases.get(i).date, String.format("%,.0f", Double.parseDouble(evaluationPurchases.get(i).total_number)),
//                            String.format("%,.0f", evaluationExpenses.get(i).amount), String.format("%,.0f", 0.0), String.format("%,.0f", profit)));
//                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        System.out.println(evaluations.size());

        return evaluations;
    }

}
