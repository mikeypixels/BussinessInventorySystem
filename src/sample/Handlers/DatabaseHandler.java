package sample.Handlers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONException;
import org.json.JSONObject;
import sample.Objects.*;

import java.sql.*;
import java.time.LocalDate;

public class DatabaseHandler {
    public static Connection conn;
    private static String url = "jdbc:mysql://localhost:3306/inventory_system";
    private static String user = "root";
    private static String password = "";

    private static Connection createConn() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url,user,password);
        }catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }

    public LoginResult login(String username, String passwd){
        String userSql = "SELECT * FROM USERS WHERE USERNAME=? AND PASSWORD=?";
        LoginResult result = null;
        try {
            PreparedStatement ps = createConn().prepareStatement(userSql);
            ps.setString(1,username);
            ps.setString(2,passwd);
            ResultSet rs = ps.executeQuery();
            JSONObject userCredentials = new JSONObject();

            if(rs.first()){
                userCredentials.put("status",rs.getString("status"));
                userCredentials.put("role",rs.getString("role"));
                userCredentials.put("password",rs.getString("password"));
                userCredentials.put("fname",rs.getString("f_name"));
                userCredentials.put("lname",rs.getString("l_name"));

                result = new LoginResult(true,userCredentials);
            }else{
                result = new LoginResult(false,userCredentials);
            }
        }catch (SQLException e){
            e.printStackTrace();
        } catch (JSONException j) {
            j.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public ObservableList<String> loadStores(){
        ObservableList<String> stores = FXCollections.observableArrayList();
        String storesSql = "SELECT * FROM stores";

        try{
            PreparedStatement ps = createConn().prepareStatement(storesSql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                stores.add(rs.getString("store_name"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return stores;
    }

    public boolean addPurchase(int product_id,String store,int quantity,double cost,LocalDate date){
        String regExpSql = "INSERT INTO purchases(product_id,store_id,quantity,cost,date) VALUES(?,?,?,?,?)";
        String storeIdSlq = "SELECT * FROM STORES WHERE store_name='"+store+"'";
        int store_id = 0;
        boolean success = false;

        try{
            PreparedStatement pd = createConn().prepareStatement(storeIdSlq);
            ResultSet rs = pd.executeQuery();
            if(rs.first()){
                store_id = rs.getInt("store_id");
            }

            System.out.println(product_id+" "+store_id+" "+quantity+" "+cost+" "+date);

            PreparedStatement ps = createConn().prepareStatement(regExpSql);
            ps.setInt(1,product_id);
            ps.setInt(2,store_id);
            ps.setInt(3,quantity);
            ps.setDouble(4,cost);
            ps.setDate(5,java.sql.Date.valueOf(date));

            ps.execute();

            if(product_id==1||product_id==2){
                success = addStock(store_id,1,quantity);
            }else{
                success = true;
            }

//            success = product_id!=1 || addStock(store_id,product_id,quantity);

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return success;
    }

    public boolean addSale(int product_id,String store,int quantity,double cost,LocalDate date){
        String regExpSql = "INSERT INTO sales(product_id,store_id,quantity,cost,date) VALUES(?,?,?,?,?)";
        String storeIdSlq = "SELECT * FROM STORES WHERE store_name='"+store+"'";
        int store_id = 0;
        boolean success = false;

        try{
            PreparedStatement pd = createConn().prepareStatement(storeIdSlq);
            ResultSet rs = pd.executeQuery();
            if(rs.first()){
                store_id = rs.getInt("store_id");
            }

            PreparedStatement ps = createConn().prepareStatement(regExpSql);
            ps.setInt(1,product_id);
            ps.setInt(2,store_id);
            ps.setInt(3,quantity);
            ps.setDouble(4,cost);
            ps.setDate(5,java.sql.Date.valueOf(date));

            ps.execute();

            if(product_id==1||product_id==2){
                success = reduceStock(store_id,1,quantity);
            }else{
                success = true;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return success;
    }

    public int getQuantity(String store){
        String storeIdSlq = "SELECT * FROM STORES WHERE store_name='"+store+"'";
        int store_id = 0;
        int quantity = 0;

        try{
            PreparedStatement pd = createConn().prepareStatement(storeIdSlq);
            ResultSet rs = pd.executeQuery();
            if(rs.first()){
                store_id = rs.getInt("store_id");
            }

            String stockSql = "SELECT * FROM STOCKS WHERE store_id="+store_id;
            PreparedStatement ps = createConn().prepareStatement(stockSql);
            ResultSet rd = ps.executeQuery();
            if(rd.first()){
                quantity = rd.getInt("available_quantity");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return quantity;
    }

    public boolean addStock(int store_id,int product_id,int quantity){
        String stockSql = "SELECT * FROM stocks WHERE store_id="+store_id+" AND  product_id="+product_id;
        boolean success;
        int new_quantity = 0;

        try{
            PreparedStatement pd = createConn().prepareStatement(stockSql);
            ResultSet rs = pd.executeQuery();
            if(rs.first()){
                new_quantity = rs.getInt("available_quantity");
            }
            new_quantity = new_quantity + quantity;

            String sql = "UPDATE stocks SET available_quantity='"+new_quantity+"' WHERE store_id="+store_id+" AND  product_id="+product_id;
            PreparedStatement ps = createConn().prepareStatement(sql);
            ps.execute();
            success=true;
        }catch (SQLException e){
            success=false;
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return success;
    }

    public boolean reduceStock(int store_id,int product_id,int quantity){
        String stockSql = "SELECT * FROM stocks WHERE store_id="+store_id+" AND  product_id="+product_id;
        boolean success;
        int new_quantity = 0;

        try{
            PreparedStatement pd = createConn().prepareStatement(stockSql);
            ResultSet rs = pd.executeQuery();
            if(rs.first()){
                new_quantity = rs.getInt("available_quantity");
            }
            new_quantity = new_quantity - quantity;

            String sql = "UPDATE stocks SET available_quantity='"+new_quantity+"' WHERE store_id="+store_id+" AND  product_id="+product_id;
            PreparedStatement ps = createConn().prepareStatement(sql);
            ps.execute();
            success=true;
        }catch (SQLException e){
            success=false;
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return success;
    }

    public boolean addExpense(String name,double amount,String description,LocalDate date, int user_id){
        String addExpSql = "INSERT INTO expenses(date,expense_name,description,cost,user_id) VALUES(?,?,?,?,?)";
//        String storeIdSlq = "SELECT * FROM STORES WHERE store_name='"+store+"'";
//        int store_id = 0;
        boolean success = false;

        try{
//            PreparedStatement pd = createConn().prepareStatement(storeIdSlq);
//            ResultSet rs = pd.executeQuery();
//            if(rs.first()){
//                store_id = rs.getInt("store_id");
//            }

            PreparedStatement ps = createConn().prepareStatement(addExpSql);
//            ps.setInt(1,store_id);
            ps.setDate(1,java.sql.Date.valueOf(date));
            ps.setString(2,name);
            ps.setString(3,description);
            ps.setDouble(4,amount);
            ps.setInt(5,user_id);

            ps.execute();
            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return success;
    }

//    public ObservableList<Expense> loadExpenses(){
//        ObservableList<Expense> expenses = FXCollections.observableArrayList();
//        String expensesSql = "SELECT * FROM expenses";
//
//        try{
//            PreparedStatement ps = createConn().prepareStatement(expensesSql);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()){
//                int id = rs.getInt("id");
//                String name = rs.getString("expense_name");
//                String description = rs.getString("description");
//                String date = rs.getString("date");
//                String amount = String.format("%,.0f", rs.getDouble("cost"));
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
//                expenses.add(new Expense(id, name,store,amount,description,date));
//            }
//        }catch (SQLException e){
//            e.printStackTrace();
//        }finally {
//            try {
//                conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return expenses;
//    }

    public boolean addUser(String fname,String lname,String uname,String password,String gender,String role,String status){
        String userSql = "INSERT INTO USERS (f_name,l_name,username,password,gender,role,status) VALUES(?,?,?,?,?,?,?)";
        boolean result = false;

        try{
            PreparedStatement ps = createConn().prepareStatement(userSql);
            ps.setString(1,fname);
            ps.setString(2,lname);
            ps.setString(3,uname);
            ps.setString(4,password);
            ps.setString(5,gender);
            ps.setString(6,role);
            ps.setString(7,status);

            ps.execute();
            result = true;

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public void updateUserStatus(String action,int id){
        String status;
        if (action.equals("activate")){
            status = "active";
        }else {
            status = "deactive";
        }

        String statusSql = "UPDATE USERS SET status='"+status+"' WHERE ID="+id;

        try {
            PreparedStatement ps = createConn().prepareStatement(statusSql);
            ps.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public boolean updatePassword(String password,int id){
        String passSql = "UPDATE USERS SET PASSWORD='"+password+"' WHERE ID="+id;
        boolean success = false;
        try{
            PreparedStatement ps = createConn().prepareStatement(passSql);
            ps.execute();
            success = true;
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return success;
    }

    public boolean deleteUser(int id){
        String userSql = "DELETE FROM USERS WHERE ID="+id;
        boolean success = false;
        try{
            PreparedStatement ps = createConn().prepareStatement(userSql);
            ps.execute();
            success = true;
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return success;
    }

//    public ObservableList<User> getUsers(String level){
//        ObservableList<User> users = FXCollections.observableArrayList();
//        String usersSql;
//
//        if(level.equals("Main Admin"))
//            usersSql = "SELECT * FROM USERS";
//        else
//            usersSql = "SELECT * FROM USERS WHERE role='worker' ";
//
//        try{
//            PreparedStatement ps = createConn().prepareStatement(usersSql);
//            ResultSet rs = ps.executeQuery();
//
//            while (rs.next()){
//                users.add(new User("USER/"+rs.getInt("id"),rs.getString("f_name")+" "+rs.getString("l_name"),rs.getString("status"),rs.getString("role")));
//            }
//        }catch (SQLException e){
//            e.printStackTrace();
//        }finally {
//            try {
//                conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return users;
//    }

    public JSONObject getPrices(){
        String sql = "SELECT * FROM PRICES";
        JSONObject jsonObject = new JSONObject();

        try {
            PreparedStatement ps = createConn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                if(rs.getInt("product_id")==1){
                    jsonObject.put("crate",rs.getDouble("price"));
                }else if(rs.getInt("product_id")==2){
                    jsonObject.put("full shell",rs.getDouble("price"));
                }else if(rs.getInt("product_id")==3){
                    jsonObject.put("bottle",rs.getDouble("price"));
                }
            }
        }catch (JSONException j){
            j.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return jsonObject;
    }

//    public ObservableList<Sale> loadSales(String store_name){
//        ObservableList<Sale> sales = FXCollections.observableArrayList();
//
//        try{
//            String storeIdSlq = "SELECT * FROM stores WHERE store_name='"+store_name+"'";
//            PreparedStatement pd = createConn().prepareStatement(storeIdSlq);
//            ResultSet rd = pd.executeQuery();
//            int store_id = 0;
//            if(rd.first()){
//                store_id = rd.getInt("store_id");
//            }
//
//            String salesSql = "SELECT * FROM sales WHERE store_id="+store_id;
//            PreparedStatement ps = createConn().prepareStatement(salesSql);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()){
//                int id = rs.getInt("id");
//                int product_id = rs.getInt("product_id");
//                String quantity = rs.getString("quantity");
//                String date = rs.getString("date");
//                String cost = String.format("%,.0f", rs.getDouble("cost"));
//
//                String productSql = "SELECT * FROM products WHERE product_id="+product_id;
//                PreparedStatement pc = createConn().prepareStatement(productSql);
//                ResultSet rc = pc.executeQuery();
//                String product_name = "";
//                if(rc.first()){
//                    product_name = rc.getString("product_name");
//                }
//                sales.add(new Sale(id, product_name,quantity,cost,date));
//            }
//        }catch (SQLException e){
//            e.printStackTrace();
//        }finally {
//            try {
//                conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return sales;
//    }
//
//    public ObservableList<Purchase> loadPurchases(String store_name){
//        ObservableList<Purchase> purchases = FXCollections.observableArrayList();
//
//        try{
//            String storeIdSlq = "SELECT * FROM stores WHERE store_name='"+store_name+"'";
//            PreparedStatement pd = createConn().prepareStatement(storeIdSlq);
//            ResultSet rd = pd.executeQuery();
//            int store_id = 0;
//            if(rd.first()){
//                store_id = rd.getInt("store_id");
//            }
//
//            String salesSql = "SELECT * FROM purchases WHERE store_id="+store_id;
//            PreparedStatement ps = createConn().prepareStatement(salesSql);
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()){
//                int id = rs.getInt("id");
//                int product_id = rs.getInt("product_id");
//                String quantity = rs.getString("quantity");
//                String date = rs.getString("date");
//                String cost = String.format("%,.0f", rs.getDouble("cost"));
//
//                String productSql = "SELECT * FROM products WHERE product_id="+product_id;
//                PreparedStatement pc = createConn().prepareStatement(productSql);
//                ResultSet rc = pc.executeQuery();
//                String product_name = "";
//                if(rc.first()){
//                    product_name = rc.getString("product_name");
//                }
//                purchases.add(new Purchase(id, product_name,quantity,cost,date));
//            }
//        }catch (SQLException e){
//            e.printStackTrace();
//        }finally {
//            try {
//                conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return purchases;
//    }

    public boolean deleteExpense(int id){
        String delSql = "DELETE FROM expenses WHERE id="+id;
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

    public boolean deleteSale(int id){
        String delSql = "DELETE FROM sales WHERE id="+id;
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

    public boolean deletePurchase(int id){
        String delSql = "DELETE FROM purchases WHERE id="+id;
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

    public boolean editExpense(int id,String name,String store,double amount,String description,LocalDate date){
        Date edate = java.sql.Date.valueOf(date);
        boolean success;

        try{
            String storeIdSlq = "SELECT * FROM STORES WHERE store_name='"+store+"'";
            int store_id = 0;
            PreparedStatement pd = createConn().prepareStatement(storeIdSlq);
            ResultSet rs = pd.executeQuery();
            if(rs.first()){
                store_id = rs.getInt("store_id");
            }

            String sql = "UPDATE expenses SET expense_name='"+name+"',store_id='"+store_id+"',cost="+amount+",description='"+description+"',date='"+edate+"' WHERE ID="+id;
            PreparedStatement ps = createConn().prepareStatement(sql);
            ps.execute();
            success=true;
        }catch (SQLException e){
            success=false;
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return success;
    }

    public boolean editPurchase(int id,String product_name,int quantity,double amount,LocalDate date,String store,String state,int extra){
        Date edate = java.sql.Date.valueOf(date);
        boolean success;

        try{
            String storeIdSlq = "SELECT * FROM products WHERE product_name='"+product_name+"'";
            int product_id = 0;
            PreparedStatement pd = createConn().prepareStatement(storeIdSlq);
            ResultSet rs = pd.executeQuery();
            if(rs.first()){
                product_id = rs.getInt("product_id");
            }
            String sql = "UPDATE purchases SET product_id='"+product_id+"',quantity='"+quantity+"',cost="+amount+",date='"+edate+"' WHERE ID="+id;
            PreparedStatement ps = createConn().prepareStatement(sql);
            ps.execute();

            if(product_id==1||product_id==2){
                String storeSlq = "SELECT * FROM stores WHERE store_name='"+store+"'";
                PreparedStatement pc = createConn().prepareStatement(storeSlq);
                ResultSet rd = pc.executeQuery();
                int store_id = 0;
                if(rd.first()){
                    store_id = rd.getInt("store_id");
                }
                String updateStockSql = "SELECT * FROM stocks WHERE store_id="+store_id;
                PreparedStatement pt = createConn().prepareStatement(updateStockSql);
                ResultSet rt = pt.executeQuery();
                int stock_quantity = 0;
                if(rt.first()){
                    stock_quantity = rt.getInt("available_quantity");
                }
                if(state.equals("add")){
                    stock_quantity = stock_quantity + extra;
                }else if(state.equals("sub")){
                    stock_quantity = stock_quantity - extra;
                }
                String newStockSql = "UPDATE stocks SET available_quantity ="+stock_quantity+" WHERE store_id="+store_id;
                PreparedStatement pr = createConn().prepareStatement(newStockSql);
                pr.execute();
            }

            success=true;
        }catch (SQLException e){
            success=false;
            e.printStackTrace();
        }finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return success;
    }

    public boolean editSale(int id,String product_name,int quantity,double amount,LocalDate date,String store,String state,int extra){
        Date edate = java.sql.Date.valueOf(date);
        boolean success;

        try{
            String storeIdSlq = "SELECT * FROM products WHERE product_name='"+product_name+"'";
            int product_id = 0;
            PreparedStatement pd = createConn().prepareStatement(storeIdSlq);
            ResultSet rs = pd.executeQuery();
            if(rs.first()){
                product_id = rs.getInt("product_id");
            }
            String sql = "UPDATE sales SET product_id='"+product_id+"',quantity='"+quantity+"',cost="+amount+",date='"+edate+"' WHERE ID="+id;
            PreparedStatement ps = createConn().prepareStatement(sql);
            ps.execute();

            if(product_id==1||product_id==2){
                String storeSlq = "SELECT * FROM stores WHERE store_name='"+store+"'";
                PreparedStatement pc = createConn().prepareStatement(storeSlq);
                ResultSet rd = pc.executeQuery();
                int store_id = 0;
                if(rd.first()){
                    store_id = rd.getInt("store_id");
                }
                String updateStockSql = "SELECT * FROM stocks WHERE store_id="+store_id;
                PreparedStatement pt = createConn().prepareStatement(updateStockSql);
                ResultSet rt = pt.executeQuery();
                int stock_quantity = 0;
                if(rt.first()){
                    stock_quantity = rt.getInt("available_quantity");
                }
                if(state.equals("add")){
                    stock_quantity = stock_quantity + extra;
                }else if(state.equals("sub")){
                    stock_quantity = stock_quantity - extra;
                }
                String newStockSql = "UPDATE stocks SET available_quantity ="+stock_quantity+" WHERE store_id="+store_id;
                PreparedStatement pr = createConn().prepareStatement(newStockSql);
                pr.execute();
            }

            success=true;
        }catch (SQLException e){
            success=false;
            e.printStackTrace();
        }finally {
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
//    public PurchaseEdit viewSale(int id) {
//        String editSql = "SELECT * FROM sales WHERE id=" + id;
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
//    public ObservableList<Evaluation> evaluation(String store_name){
//
//        ObservableList<Evaluation> evaluations = FXCollections.observableArrayList();
//
//        try {
//            String storeIdSlq = "SELECT * FROM stores WHERE store_name='"+store_name+"'";
//            PreparedStatement pd = createConn().prepareStatement(storeIdSlq);
//            ResultSet rd = pd.executeQuery();
//            int store_id = 0;
//            if(rd.first()){
//                store_id = rd.getInt("store_id");
//            }
//
//            String evaluationSql = "select sum(quantity) as p_total,MONTHNAME(date) AS p_date,YEAR (date) " +
//                    "AS p_year from purchases where store_id="+store_id+" and product_id=1 or product_id=2 Group by Month(date),YEAR(date) ORDER BY YEAR(date) desc,MONTH (date) desc";
//
//            PreparedStatement ps = createConn().prepareStatement(evaluationSql);
//            ResultSet rs = ps.executeQuery();
//            ObservableList<EvaluationPurchase> evaluationPurchases = FXCollections.observableArrayList();
//            while (rs.next()) {
//                String date = rs.getString("p_date") + "/" + rs.getString("p_year");
//
//                evaluationPurchases.add(new EvaluationPurchase(date,rs.getString("p_total")));
//            }
////
//            String expense_sql ="select sum(cost)as e_amount,MONTHNAME(date) as e_month,YEAR(date) as e_year from expenses where store_id="+store_id+" GROUP BY MONTH(date) ORDER BY YEAR(date) desc,MONTH (date) desc";
//            PreparedStatement pr = createConn().prepareStatement(expense_sql);
//            ResultSet rr = pr.executeQuery();
//            ObservableList<EvaluationExpense> evaluationExpenses = FXCollections.observableArrayList();
//            for(int i=0;i<evaluationPurchases.size();i++){
//                evaluationExpenses.add(i,new EvaluationExpense(evaluationPurchases.get(i).date,0));
//            }
//            while (rr.next()) {
//                String e_date = rr.getString("e_month") + "/" + rr.getString("e_year");
//
//                for(int i=0;i<evaluationExpenses.size();i++){
//                    if(e_date.equals(evaluationExpenses.get(i).date)){
//                        evaluationExpenses.set(i,new EvaluationExpense(e_date,rr.getDouble("e_amount")));
//                    }
//                }
//            }
//
//            String crates_income_sql ="select sum(quantity)as crates,MONTHNAME(date) as crate_month,YEAR(date) as crate_year from sales where store_id="+store_id+" and product_id=1 GROUP BY MONTH(date) ORDER BY YEAR(date) desc,MONTH (date) desc";
//            PreparedStatement pb = createConn().prepareStatement(crates_income_sql);
//            ResultSet rb = pb.executeQuery();
//            ObservableList<EvaluationCrates> evaluationCrates = FXCollections.observableArrayList();
//            for(int i=0;i<evaluationPurchases.size();i++){
//                evaluationCrates.add(i,new EvaluationCrates(evaluationPurchases.get(i).date,0));
//            }
//            double crates_income;
//            while (rb.next()) {
//                String crate_date = rb.getString("crate_month") + "/" + rb.getString("crate_year");
//                crates_income = rb.getInt("crates")*500;
//
//                for(int i=0;i<evaluationCrates.size();i++){
//                    if(crate_date.equals(evaluationCrates.get(i).date)){
//                        evaluationCrates.set(i,new EvaluationCrates(crate_date,crates_income));
//                    }
//                }
////                evaluationCrates.add(new EvaluationCrates(crate_date,crates_income));
//            }
//
//            String bottles_income_sql ="select sum(quantity)as bottles,MONTHNAME(date) as bottle_month,YEAR(date) as bottle_year from sales where store_id="+store_id+" and product_id=3 GROUP BY MONTH(date) ORDER BY YEAR(date) desc,MONTH (date) desc";
//            PreparedStatement pc = createConn().prepareStatement(bottles_income_sql);
//            ResultSet rc = pc.executeQuery();
//            ObservableList<EvaluationCrates> evaluationBottles = FXCollections.observableArrayList();
//            for(int i=0;i<evaluationPurchases.size();i++){
//                evaluationBottles.add(i,new EvaluationCrates(evaluationPurchases.get(i).date,0));
//            }
//            double bottles_income;
//            while (rc.next()) {
//                String bottle_date = rc.getString("bottle_month") + "/" + rc.getString("bottle_year");
//                bottles_income = rc.getInt("bottles")*50;
//                for(int i=0;i<evaluationBottles.size();i++){
//                    if(bottle_date.equals(evaluationBottles.get(i).date)){
//                        evaluationBottles.set(i,new EvaluationCrates(bottle_date,bottles_income));
//                    }
//                }
////                evaluationBottles.add(new EvaluationCrates(bottle_date,bottles_income));
//            }
//
//            String full_shell_income_sql ="select sum(quantity)as full_shells,MONTHNAME(date) as full_shell_month,YEAR(date) as full_shell_year from sales where store_id="+store_id+" and product_id=2 GROUP BY MONTH(date) ORDER BY YEAR(date) desc,MONTH (date) desc";
//            PreparedStatement pk = createConn().prepareStatement(full_shell_income_sql);
//            ResultSet rk = pk.executeQuery();
//            ObservableList<EvaluationCrates> evaluationFullShell = FXCollections.observableArrayList();
//            for(int i=0;i<evaluationPurchases.size();i++){
//                evaluationFullShell.add(i,new EvaluationCrates(evaluationPurchases.get(i).date,0));
//            }
//            double full_shell_income;
//            while (rk.next()) {
//                String full_shell_date = rk.getString("full_shell_month") + "/" + rk.getString("full_shell_year");
//                full_shell_income = rk.getInt("full_shells")*1700;
//                for(int i=0;i<evaluationFullShell.size();i++){
//                    if(full_shell_date.equals(evaluationFullShell.get(i).date)){
//                        evaluationFullShell.set(i,new EvaluationCrates(full_shell_date,full_shell_income));
//                    }
//                }
////                evaluationBottles.add(new EvaluationCrates(bottle_date,bottles_income));
//            }
//
//            double income;
//            double profit;
//            for(int i=0;i<evaluationPurchases.size();i++){
//                income = evaluationCrates.get(i).amount + evaluationBottles.get(i).amount + evaluationFullShell.get(i).amount;
//                profit = income - evaluationExpenses.get(i).amount;
//                System.out.println(evaluationPurchases.get(i).date+" "+evaluationPurchases.get(i).total_number+" "+ evaluationExpenses.get(i).amount +" "+income+" "+profit);
//
//                evaluations.add(new Evaluation(evaluationPurchases.get(i).date,evaluationPurchases.get(i).total_number,
//                        String.format("%,.0f", evaluationExpenses.get(i).amount),String.format("%,.0f", income),String.format("%,.0f", profit)));
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return evaluations;
//    }

}
