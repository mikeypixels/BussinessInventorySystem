package sample;

import animatefx.animation.*;
import com.jfoenix.controls.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.controlsfx.control.textfield.CustomTextField;
import org.json.JSONException;
import org.json.JSONObject;
import sample.Handlers.Checker;
import sample.Handlers.DatabaseHandler;
import sample.Objects.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    AnchorPane sales_anchorpane, dash_item_pane, purchase_anchorpane, pur_dash_item_pane, st_item_dash_holder, dash_pane;
    @FXML
    StackPane stack_dash_holder, stack_pur_dash_holder, friji_stackpane;
    @FXML
    StackPane tv_stackpane, wire_stackpane, tv_stand_stackpane, mashuka_stackpane, pasi_stackpane, socket_stackpane, deki_stackpane, remote_stackpane, redio_stackpane, spika_stackpane, fan_stackpane, taa_stackpane;
    @FXML
    StackPane pur_dash_stackpane, pur_tv_stackpane, pur_wire_stackpane, pur_tv_stand_stackpane, pur_mashuka_stackpane, pur_pasi_stackpane, pur_socket_stackpane, pur_deki_stackpane, pur_remote_stackpane, pur_redio_stackpane, pur_spika_stackpane, pur_fan_stackpane, pur_taa_stackpane, pur_friji_stackpane;
    @FXML
    AnchorPane st_tv_pane, st_wire_pane, st_tv_stand_pane, st_mashuka_pane, st_pasi_pane, st_socket_pane, st_deki_pane, st_remote_pane, st_redio_pane, st_spika_pane, st_fan_pane, st_taa_pane, st_friji_pane;
    @FXML
    public Label tv_label, tv_idadi, tv_no, wire_label, wire_idadi, wire_no, tv_stand_label, tv_stand_idadi, tv_stand_no, mashuka_label, mashuka_idadi, mashuka_no, pasi_label, pasi_idadi, pasi_no, socket_label, socket_idadi, socket_no, deki_label, deki_idadi,
            deki_no, remote_label, remote_idadi, remote_no, redio_label, redio_idadi, redio_no, spika_label, spika_idadi, spika_no, fan_label, fan_idadi, fan_no, taa_label, taa_idadi, taa_no, friji_label, friji_idadi, friji_no;

    @FXML
    public Label pur_tv_label, pur_tv_idadi, pur_tv_no, pur_wire_label, pur_wire_idadi, pur_wire_no, pur_tv_stand_label, pur_tv_stand_idadi, pur_tv_stand_no, pur_mashuka_label, pur_mashuka_idadi, pur_mashuka_no, pur_pasi_label, pur_pasi_idadi, pur_pasi_no, pur_socket_label, pur_socket_idadi, pur_socket_no, pur_deki_label, pur_deki_idadi,
            pur_deki_no, pur_remote_label, pur_remote_idadi, pur_remote_no, pur_redio_label, pur_redio_idadi, pur_redio_no, pur_spika_label, pur_spika_idadi, pur_spika_no, pur_fan_label, pur_fan_idadi, pur_fan_no, pur_taa_label, pur_taa_idadi, pur_taa_no, pur_friji_label, pur_friji_idadi, pur_friji_no;
    @FXML
    Label st_tv_label, st_tv_idadi, st_tv_no, st_wire_label, st_wire_idadi, st_wire_no, st_tv_stand_label, st_tv_stand_idadi, st_tv_stand_no, st_mashuka_label, st_mashuka_idadi, st_mashuka_no, st_pasi_label, st_pasi_idadi, st_pasi_no, st_socket_label, st_socket_idadi, st_socket_no, st_deki_label, st_deki_idadi,
            st_deki_no, st_remote_label, st_remote_idadi, st_remote_no, st_redio_label, st_redio_idadi, st_redio_no, st_spika_label, st_spika_idadi, st_spika_no, st_fan_label, st_fan_idadi, st_fan_no, st_taa_label, st_taa_idadi, st_taa_no, st_friji_label, st_friji_idadi, st_friji_no;
    @FXML
    ImageView tv_image, wire_image, tv_stand_image, mashuka_image, pasi_image, socket_image, deki_image, remote_image, redio_image, spika_image, fan_image, taa_image, friji_image;
    @FXML
    ImageView pur_tv_image, pur_wire_image, pur_tv_stand_image, pur_mashuka_image, pur_pasi_image, pur_socket_image, pur_deki_image, pur_remote_image, pur_redio_image, pur_spika_image, pur_fan_image, pur_taa_image, pur_friji_image;
    @FXML
    ImageView st_tv_image, st_wire_image, st_tv_stand_image, st_mashuka_image, st_pasi_image, st_socket_image, st_deki_image, st_remote_image, st_redio_image, st_spika_image, st_fan_image, st_taa_image, st_friji_image;
    @FXML
    TableColumn<Sale, String> productName_col;
    @FXML
    TableColumn<Sale, String> quantity_col;
    @FXML
    TableColumn<Sale, String> cost_col;
    @FXML
    TableColumn<Sale, String> action_col;
    @FXML
    TableColumn<Sale, String> date_col;
    @FXML
    TableView<Sale> sales_table;
    @FXML
    JFXButton exp_submit, store_btn;
    @FXML
    JFXTextField exp_name, exp_cost, store_quantity;
    @FXML
    CustomTextField salesfieldSearch, purfieldSearch, expfieldSearch;
    @FXML
    JFXTextArea exp_desc;
    @FXML
    JFXDatePicker exp_date;
    @FXML
    TableColumn<Expense, String> exp_name_col;
    @FXML
    TableColumn<Expense, String> exp_desc_col;
    @FXML
    TableColumn<Expense, String> exp_cost_col;
    @FXML
    TableColumn<Expense, String> exp_date_col;
    @FXML
    TableColumn<Expense, String> exp_action_col;
    @FXML
    TableView<Expense> expense_table;
    @FXML
    TableColumn<Purchase, String> purName_col;
    @FXML
    TableColumn<Purchase, String> purQuantity_col;
    @FXML
    TableColumn<Purchase, String> purCost_col;
    @FXML
    TableColumn<Purchase, String> purDate_col;
    @FXML
    TableColumn<Purchase, String> purAction_col;
    @FXML
    TableView<Purchase> purchase_table;
    @FXML
    JFXComboBox store_item_category, store_sub_category;
    @FXML
    Tab sales_tab, purchases_tab;
    @FXML
    Label st_av_quantity, sales_rows_txt, pur_rows_txt, exp_rows_txt, today_sales_txt, today_purchases_txt, today_expenses_txt;
    @FXML
    TabPane dash_tabPane;

    public static Label exp_rows_label;

    public static AnchorPane add_sale_pane, p_sales_anchorpane, p_dash_item_pane;

    public static StackPane p_item_dash_holder;

    public static String category_sale;
    public static String category_store;

    public static TableColumn<Sale, String> act_col;
    public static TableView<Sale> sale_table;

    public static TableColumn<Purchase, String> pur_act_col;
    public static TableColumn<Expense, String> exp_act_col;
    public static TableView<Purchase> purchases_table;
    public static TableView<Expense> expenses_table;

    String category_item_store;
    String store_item_string;

    public static Label pur_rows_label, sales_rows_label;

    public static String category_quantity;
    public static String store_category_quantity;

    public static String tab_identifier;

    JSONObject userObject;
    FXMLLoader fxmlLoader;

    ObservableList<Purchase> purchases = FXCollections.observableArrayList();

    int user_id;

    DatabaseHandler db = new DatabaseHandler();

    ObservableList<Expense> expenses = FXCollections.observableArrayList();

    ObservableList<String> tv = FXCollections.observableArrayList("HOMEBASE CHOGO", "HOMEBASE FLAT", "MR UK CHOGO", "MR UK FLAT", "STAR X", "RISING", "OCNG", "RITECH", "SUNDAR", "EVOL", "SING SAN");
    ObservableList<String> fan = FXCollections.observableArrayList("HOMEBASE", "SUWDA");
    ObservableList<String> wire = FXCollections.observableArrayList("AV WIRE");
    ObservableList<String> tv_stand = FXCollections.observableArrayList("HOMEBASE");
    ObservableList<String> mashuka = FXCollections.observableArrayList("TAUSI", "KARUI", "GIGA NZITO", "LOY BO", "BED KIVA", "TAUSI BOX");
    ObservableList<String> pasi = FXCollections.observableArrayList("PHILIPS", "SING SANG");
    ObservableList<String> socket = FXCollections.observableArrayList("TRONIC", "SUNDAR");
    ObservableList<String> deki = FXCollections.observableArrayList("UK", "SUNDAR", "REDI AT 7350");
    ObservableList<String> remote = FXCollections.observableArrayList("AZAM", "DEKI SINGSANG");
    ObservableList<String> redio = FXCollections.observableArrayList("SABUFA SP", "SABUFA ALING", "SABUFA ABODA", "SABUFA MR UK", "SABUFA SUNDAR");
    ObservableList<String> spika = FXCollections.observableArrayList("SPIKA YA GARI", "SPIKA KUBWA");
    ObservableList<String> solar_taa = FXCollections.observableArrayList("SOLAR TAA");
    ObservableList<String> friji = FXCollections.observableArrayList("FRIJI");

    ObservableList<String> tv_homebase_chogo = FXCollections.observableArrayList("IN 18", "IN 21");
    ObservableList<String> tv_homebase_flat = FXCollections.observableArrayList("IN 24", "IN 32", "IN 43", "IN 49", "IN 55");
    ObservableList<String> tv_mr_uk_chogo = FXCollections.observableArrayList("IN 18", "IN 21");
    ObservableList<String> tv_mr_uk_flat = FXCollections.observableArrayList("IN 16", "IN 18", "IN 29", "IN 32", "IN 43");
    ObservableList<String> tv_star_x = FXCollections.observableArrayList("IN 24", "IN 32");
    ObservableList<String> tv_rising = FXCollections.observableArrayList("IN 32");
    ObservableList<String> tv_ocng = FXCollections.observableArrayList("IN 32");
    ObservableList<String> tv_ritech = FXCollections.observableArrayList("IN 24", "IN 32");
    ObservableList<String> tv_sundar = FXCollections.observableArrayList("IN 15", "IN 17", "IN 19", "IN 24", "IN 32");
    ObservableList<String> tv_evol = FXCollections.observableArrayList("IN 32");
    ObservableList<String> tv_singsang = FXCollections.observableArrayList("IN 32");

    ObservableList<String> tv_stand_homebase = FXCollections.observableArrayList("IN 14 - 30", "IN 26 - 30");

    ObservableList<String> socket_tronic = FXCollections.observableArrayList("TR 7864", "TR 7866");
    ObservableList<String> socket_sundar = FXCollections.observableArrayList("SUN 41", "SUN 51", "SUN 61");

    ObservableList<String> deki_uk = FXCollections.observableArrayList("UK I", "UK II", "UK III");
    ObservableList<String> deki_sundar = FXCollections.observableArrayList("SUN 2260", "SUN 2261");

    ObservableList<String> redio_sabufa_sp = FXCollections.observableArrayList("SP 513", "SP 589", "SP 661", "SP 663", "SP 770", "SP 821", "SP 825", "SP 877", "SP 1002", "SP 8800");
    ObservableList<String> redio_sabufa_aling = FXCollections.observableArrayList("AL 101", "AL 3306", "AL 3030", "AL 8866", "9600E", "9600B");
    ObservableList<String> redio_sabufa_aboda = FXCollections.observableArrayList("AB X224", "AB 3234", "AB 3236", "AB 3239", "AB 3251", "AB 3809");
    ObservableList<String> redio_sabufa_mr_uk = FXCollections.observableArrayList("UK 01", "UK 02", "UK 05", "UK 3308");
    ObservableList<String> redio_sabufa_sundar = FXCollections.observableArrayList("SD 115", "SD 118", "SD 120", "SD 181", "SD 182", "SD 240", "SD 503", "SD 613", "SD 614", "SD 615", "SD 617", "SD 740", "SD 749", "SD 750", "SD 759", "SD 801", "SD 802", "SD 803");

    ObservableList<String> spika_spika_ya_gari = FXCollections.observableArrayList("TS A6995");
    ObservableList<String> spika_spika_kubwa = FXCollections.observableArrayList("UK 22", "UK 33", "UK 44", "UK 55", "UK 77");

    ObservableList<Stock> store_stocks = FXCollections.observableArrayList();
    ObservableList<Stock> stocks = FXCollections.observableArrayList();
    public static ObservableList<Stock> available_store_stocks = FXCollections.observableArrayList();
    public static ObservableList<Stock> available_stocks = FXCollections.observableArrayList();

    public static ObservableList<Sale> salesForTodaySum = FXCollections.observableArrayList();
    public static ObservableList<Purchase> purchasesForTodaySum = FXCollections.observableArrayList();
    public static ObservableList<Expense> expensesForTodaySum = FXCollections.observableArrayList();

    public static Label tv_, wire_, tv_stand_, mashuka_, pasi_, socket_, deki_, remote_, redio_, spika_, fan_, taa_, friji_;
    public static Label _tv_, _wire_, _tv_stand_, _mashuka_, _pasi_, _socket_, _deki_, _remote_, _redio_, _spika_, _fan_, _taa_, _friji_;

    int total = 0;
    String[] pName;

    public static String product_name;
    public static String quantity;
    public static String cost;
    public static String date;
    public static String desc;
    public static int id;

    public static Label sales_today, expenses_today, purchases_today;

    double sale_total = 0;
    double purchase_total = 0;
    double expense_total = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        store_stocks = db.getStoreStocks();
        stocks = db.getStocks();

        mouseEvents(tv_label, tv_idadi, tv_no, tv_image, tv_stackpane);
        mouseEvents(wire_label, wire_idadi, wire_no, wire_image, wire_stackpane);
        mouseEvents(tv_stand_label, tv_stand_idadi, tv_stand_no, tv_stand_image, tv_stand_stackpane);
        mouseEvents(mashuka_label, mashuka_idadi, mashuka_no, mashuka_image, mashuka_stackpane);
        mouseEvents(pasi_label, pasi_idadi, pasi_no, pasi_image, pasi_stackpane);
        mouseEvents(socket_label, socket_idadi, socket_no, socket_image, socket_stackpane);
        mouseEvents(deki_label, deki_idadi, deki_no, deki_image, deki_stackpane);
        mouseEvents(remote_label, remote_idadi, remote_no, remote_image, remote_stackpane);
        mouseEvents(redio_label, redio_idadi, redio_no, redio_image, redio_stackpane);
        mouseEvents(spika_label, spika_idadi, spika_no, spika_image, spika_stackpane);
        mouseEvents(fan_label, fan_idadi, fan_no, fan_image, fan_stackpane);
        mouseEvents(taa_label, taa_idadi, taa_no, taa_image, taa_stackpane);
        mouseEvents(friji_label, friji_idadi, friji_no, friji_image, friji_stackpane);

        mousePurEvents(pur_tv_label, pur_tv_image, pur_tv_stackpane);
        mousePurEvents(pur_wire_label, pur_wire_image, pur_wire_stackpane);
        mousePurEvents(pur_tv_stand_label, pur_tv_stand_image, pur_tv_stand_stackpane);
        mousePurEvents(pur_mashuka_label, pur_mashuka_image, pur_mashuka_stackpane);
        mousePurEvents(pur_pasi_label, pur_pasi_image, pur_pasi_stackpane);
        mousePurEvents(pur_socket_label, pur_socket_image, pur_socket_stackpane);
        mousePurEvents(pur_deki_label, pur_deki_image, pur_deki_stackpane);
        mousePurEvents(pur_remote_label, pur_remote_image, pur_remote_stackpane);
        mousePurEvents(pur_redio_label, pur_redio_image, pur_redio_stackpane);
        mousePurEvents(pur_spika_label, pur_spika_image, pur_spika_stackpane);
        mousePurEvents(pur_fan_label, pur_fan_image, pur_fan_stackpane);
        mousePurEvents(pur_taa_label, pur_taa_image, pur_taa_stackpane);
        mousePurEvents(pur_friji_label, pur_friji_image, pur_friji_stackpane);

        mouseEventsStore(st_tv_label, st_tv_idadi, st_tv_no, st_tv_image, st_tv_pane);
        mouseEventsStore(st_wire_label, st_wire_idadi, st_wire_no, st_wire_image, st_wire_pane);
        mouseEventsStore(st_tv_stand_label, st_tv_stand_idadi, st_tv_stand_no, st_tv_stand_image, st_tv_stand_pane);
        mouseEventsStore(st_mashuka_label, st_mashuka_idadi, st_mashuka_no, st_mashuka_image, st_mashuka_pane);
        mouseEventsStore(st_pasi_label, st_pasi_idadi, st_pasi_no, st_pasi_image, st_pasi_pane);
        mouseEventsStore(st_socket_label, st_socket_idadi, st_socket_no, st_socket_image, st_socket_pane);
        mouseEventsStore(st_deki_label, st_deki_idadi, st_deki_no, st_deki_image, st_deki_pane);
        mouseEventsStore(st_remote_label, st_remote_idadi, st_remote_no, st_remote_image, st_remote_pane);
        mouseEventsStore(st_redio_label, st_redio_idadi, st_redio_no, st_redio_image, st_redio_pane);
        mouseEventsStore(st_spika_label, st_spika_idadi, st_spika_no, st_spika_image, st_spika_pane);
        mouseEventsStore(st_fan_label, st_fan_idadi, st_fan_no, st_fan_image, st_fan_pane);
        mouseEventsStore(st_taa_label, st_taa_idadi, st_taa_no, st_taa_image, st_taa_pane);
        mouseEventsStore(st_friji_label, st_friji_idadi, st_friji_no, st_friji_image, st_friji_pane);

        tv_ = st_tv_no;
        wire_ = st_wire_no;
        tv_stand_ = st_tv_stand_no;
        mashuka_ = st_mashuka_no;
        pasi_ = st_pasi_no;
        socket_ = st_socket_no;
        deki_ = st_deki_no;
        remote_ = st_remote_no;
        redio_ = st_redio_no;
        spika_ = st_spika_no;
        fan_ = st_fan_no;
        taa_ = st_taa_no;
        friji_ = st_friji_no;

        _tv_= tv_no;
        _wire_ = wire_no;
        _tv_stand_ =  tv_stand_no;
        _mashuka_ = mashuka_no;
        _pasi_= pasi_no;
        _socket_= socket_no;
        _deki_= deki_no;
        _remote_= remote_no;
        _redio_= redio_no;
        _spika_= spika_no;
        _fan_= fan_no;
        _taa_= taa_no;
        _friji_=friji_no;

//        sizeListeners(tv_stackpane);
//        sizeListeners(wire_stackpane);
//        sizeListeners(tv_stand_stackpane);
//        sizeListeners(mashuka_stackpane);
//        sizeListeners(pasi_stackpane);
//        sizeListeners(socket_stackpane);
//        sizeListeners(deki_stackpane);
//        sizeListeners(remote_stackpane);
//        sizeListeners(redio_stackpane);
//        sizeListeners(spika_stackpane);
//        sizeListeners(fan_stackpane);
//        sizeListeners(taa_stackpane);
//        sizeListeners(friji_stackpane);

        act_col = action_col;

        sale_table = sales_table;

        pur_act_col = purAction_col;

        pur_rows_label = pur_rows_txt;

        sales_rows_label = sales_rows_txt;

        purchases_table = purchase_table;

        exp_act_col = exp_action_col;

        expenses_table = expense_table;

        exp_rows_label = exp_rows_txt;

//        exp_alert_txt.setText("");

//        exp_date.getEditor().setDisable(true);

        productName_col.setCellValueFactory(new PropertyValueFactory<>("product_name"));
        quantity_col.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        cost_col.setCellValueFactory(new PropertyValueFactory<>("cost"));
        date_col.setCellValueFactory(new PropertyValueFactory<>("date"));
        action_col.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

        purName_col.setCellValueFactory(new PropertyValueFactory<>("name"));
        purQuantity_col.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        purCost_col.setCellValueFactory(new PropertyValueFactory<>("amount"));
        purDate_col.setCellValueFactory(new PropertyValueFactory<>("date"));
        purAction_col.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

        exp_name_col.setCellValueFactory(new PropertyValueFactory<>("name"));
        exp_cost_col.setCellValueFactory(new PropertyValueFactory<>("amount"));
        exp_desc_col.setCellValueFactory(new PropertyValueFactory<>("description"));
        exp_date_col.setCellValueFactory(new PropertyValueFactory<>("date"));
        exp_action_col.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

        sales_table.setRowFactory(tableValue -> new TableRow<Sale>() {
            @Override
            public void updateItem(Sale item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null) {
                    setStyle("");
                } else {
                    setStyle("-fx-border-color: #E0E0E0; -fx-border-width: 0.7 0 0 0;");
                }
            }
        });

        pur_rows_txt.setText("");
        exp_rows_txt.setText("");
        sales_rows_txt.setText("");

//        salesForTodaySum = db.loadSales();
//        purchasesForTodaySum = db.loadPurchases();
//        expensesForTodaySum = db.loadExpenses();

        for(Sale sale: db.loadSales()){
            if(sale.getDate().equals(getCurrentDate())){
                sale_total = sale_total + Double.parseDouble(sale.getCost().replaceAll(",",""));
            }
        }

        today_sales_txt.setText(String.format("%,.0f", sale_total) + " Tshs");

        for(Expense expense: db.loadExpenses()){
            if(expense.getDate().equals(getCurrentDate())){
                expense_total = expense_total + Double.parseDouble(expense.getAmount().replaceAll(",",""));
            }
        }

        today_expenses_txt.setText(String.format("%,.0f", expense_total) + " Tshs");

        for(Purchase purchase: db.loadPurchases()){
            if(purchase.getDate().equals(getCurrentDate())){
                purchase_total = purchase_total + Double.parseDouble(purchase.getAmount().replaceAll(",",""));
            }
        }

        today_purchases_txt.setText(String.format("%,.0f", purchase_total) + " Tshs");

        sales_today = today_sales_txt;
        expenses_today = today_expenses_txt;
        purchases_today = today_purchases_txt;

        fillSales();
//        sales_table.setItems(sales);

        initializeExpenses();

        fillPurchases();

    }

    public String getCurrentDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();

        return dtf.format(now);
    }

    public String getCurrentDateNew(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDateTime now = LocalDateTime.now();

        return dtf.format(now);
    }

    public void sizeListeners(StackPane pane) {
        dash_tabPane.heightProperty().addListener(
                (ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
//                    if(oldValue.doubleValue() == newValue.doubleValue()){
////                        friji_stackpane.setPrefHeight(friji_stackpane.getHeight()-2);
//                    }else if(oldValue.doubleValue() > newValue.doubleValue())

                    if (oldValue.doubleValue() > newValue.doubleValue()) {
                        pane.setPrefHeight(newValue.doubleValue() / 10);
                        pane.setLayoutY(pane.getLayoutY() - pane.getLayoutY() / newValue.doubleValue());
                    } else {
                        pane.setPrefHeight(newValue.doubleValue() / 10);
                        pane.setLayoutY(pane.getLayoutY() + pane.getLayoutY() / newValue.doubleValue());
                    }

                }
        );
    }

    public void addStock() {

        if (store_sub_category.isVisible()) {
            if (store_sub_category.getValue().toString().isEmpty() || store_quantity.getText().isEmpty() || store_item_category.getValue().toString().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Empty Field");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all fields!");
                alert.showAndWait();
            } else {

                int quantity_txt = Integer.parseInt(store_quantity.getText());

                String[] cat_quantity = st_av_quantity.getText().split("=");

                DatabaseHandler db = new DatabaseHandler();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Add to store");
                alert.setHeaderText(null);

                if (quantity_txt > Integer.parseInt(cat_quantity[1].replaceAll(" ", ""))) {
                    alert.setContentText("No enough " + category_item_store.toUpperCase() + "/" + store_item_category.getValue().toString() + "/" + store_sub_category.getValue().toString() + " in store!");
                    alert.showAndWait();
                } else {
                    double cost = 0;
                    String product_name = "";
                    for (Product product : db.loadProductList()) {
                        if (product.getProduct_name().equals(category_item_store.toUpperCase() + "/" + store_item_category.getValue().toString() + "/" + store_sub_category.getValue().toString())) {
                            product_name = product.getProduct_name();

                            System.out.println("This is the id: " + product_name);
                        }
                    }

                    System.out.println("This is the id: " + product_name);

                    if (sales_tab.isSelected()) {
                        try {
                            boolean result = db.addStock(product_name, quantity_txt);

                            DashboardController dc = new DashboardController();
                            if (result) {
                                store_quantity.setText("");
                                st_av_quantity.setText("Available quantity = " + (Integer.parseInt(cat_quantity[1].replaceAll(" ", "")) - quantity_txt));
                                refreshStoreItemPane(category_item_store, quantity_txt);
//                                dc.fillSales();
                                alert.setContentText("Stocks successful added!");
                                alert.showAndWait();
                            } else {
                                alert.setContentText("Unsuccessful added!");
                                alert.showAndWait();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            boolean result = db.addStock(product_name, quantity_txt);

                            DashboardController dc = new DashboardController();
                            if (result) {
                                store_quantity.setText("");
                                st_av_quantity.setText("Available quantity = " + (Integer.parseInt(cat_quantity[1].replaceAll(" ", "")) - quantity_txt));
                                refreshStoreItemPane(category_item_store, quantity_txt);
                                refreshItemStockPane(category_item_store, quantity_txt);
//                                dc.fillPurchases();
                                alert.setContentText("Stocks successful added!");
                                alert.showAndWait();
                            } else {
                                alert.setContentText("Unsuccessful added!");
                                alert.showAndWait();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        } else {
            if (store_quantity.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Empty Field");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all fields!");
                alert.showAndWait();
            } else {

                int quantity_txt = Integer.parseInt(store_quantity.getText());

                String[] cat_quantity = st_av_quantity.getText().split("=");

                DatabaseHandler db = new DatabaseHandler();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Add to store");
                alert.setHeaderText(null);

                if (quantity_txt > Integer.parseInt(cat_quantity[1].replaceAll(" ", ""))) {
                    alert.setContentText("No enough " + category_item_store.toUpperCase() + "/" + store_item_category.getValue().toString() + " in store!");
                    alert.showAndWait();
                } else {
                    double cost = 0;
                    String product_name = "";
                    for (Product product : db.loadProductList()) {
//                        System.out.println(DashboardController.category_sale.toUpperCase() + "/" + store_item_category.getValue().toString());
                        System.out.println(product.getProduct_name());
                        if (category_item_store.toUpperCase().equals(store_item_category.getValue().toString())) {
                            if (product.getProduct_name().equals(DashboardController.category_sale.toUpperCase())) {
                                product_name = product.getProduct_name();
                                System.out.println("This is the id: " + product_name);
                                break;
                            }
                        } else {
                            if (product.getProduct_name().equals(category_item_store.toUpperCase() + "/" + store_item_category.getValue().toString())) {
                                product_name = product.getProduct_name();
                                System.out.println("This is the id: " + product_name);
                                break;
                            }
                        }
                    }

                    System.out.println("This is the outside id: " + product_name);

                    if (sales_tab.isSelected()) {
                        try {
                            boolean result = db.addStock(product_name, quantity_txt);

                            DashboardController dc = new DashboardController();
                            if (result) {
                                store_quantity.setText("");
                                st_av_quantity.setText("Available quantity = " + (Integer.parseInt(cat_quantity[1].replaceAll(" ", "")) - quantity_txt));
                                refreshStoreItemPane(category_item_store, quantity_txt);
//                                dc.fillSales();
                                alert.setContentText("Stocks successful added!");
                                alert.showAndWait();
                            } else {
                                alert.setContentText("Unsuccessful added!");
                                alert.showAndWait();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            boolean result = db.addStock(product_name, quantity_txt);

                            DashboardController dc = new DashboardController();
                            if (result) {
                                store_quantity.setText("");
                                st_av_quantity.setText("Available quantity = " + (Integer.parseInt(cat_quantity[1].replaceAll(" ", "")) - quantity_txt));
                                refreshStoreItemPane(category_item_store, quantity_txt);
                                refreshItemStockPane(category_item_store, quantity_txt);
//                                dc.fillPurchases();
                                alert.setContentText("Stocks successful added!");
                                alert.showAndWait();
                            } else {
                                alert.setContentText("Unsuccessful added!");
                                alert.showAndWait();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public void editSale(int id) {
        try {
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/sale_edit.fxml"));
            stage.setTitle("Business Inventory System");
            SaleEditController saleEditController = (SaleEditController) fxmlLoader.getController();
            Scene editPurchaseScene = new Scene(fxmlLoader.load(), 700, 580);
            stage.setScene(editPurchaseScene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void getUserDetails(JSONObject userObject, FXMLLoader fxmlLoader){
//        this.userObject = userObject;
//        this.fxmlLoader = fxmlLoader;
//        try {
//            System.out.println("The role is"+userObject.getString("role"));
//        }catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }

    public void expSubClick() {
        if (exp_name.getText().isEmpty() && exp_cost.getText().isEmpty() && exp_desc.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Empty Field");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all fields!");
            alert.showAndWait();
        } else {
            user_id = 3;
//                LocalDate date = exp_date.getValue();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Expense Status");
            alert.setHeaderText(null);
            if (Checker.isStringInt(exp_cost.getText().replaceAll(",", ""))) {
                String[] expense_string = expenses_today.getText().split(" ");
                if(exp_date.getValue().toString().equals(getCurrentDate())){
                    expenses_today.setText(String.format("%,.0f", Double.parseDouble(expense_string[0].replaceAll(",",""))+Double.parseDouble(exp_cost.getText().replaceAll(",",""))) + " Tshs");
                }
                if (db.addExpense(exp_name.getText(), Double.parseDouble(exp_cost.getText().replaceAll(",", "")), exp_desc.getText(), exp_date.getValue(), user_id)) {
                    exp_name.setText("");
                    exp_cost.setText("");
                    exp_desc.setText("");
                    exp_date.setValue(null);
                    initializeExpenses();
                    alert.setContentText("successfully added!");
                    alert.showAndWait();
                } else {
                    alert.setContentText("Unsuccessfully added!");
                    alert.showAndWait();
                }
            } else {
                alert.setContentText("Please check the amount or quantity!");
                alert.showAndWait();
            }


        }
    }

    public void mouseEvents(Label label1, Label label2, Label label3, ImageView imageView, StackPane pane) {

        Effect effect = tv_stackpane.getEffect();

        refreshItemPane(label1, label3);

        label1.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
//                pane.setStyle("-fx-background-color: #975bfe; -fx-background-radius: 4px");
                pane.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 4px");
                pane.setScaleX(1.05);
                pane.setScaleY(1.05);
                pane.setEffect(addPaneEffect());
                labelMouseEntered(label1);
                labelMouseEntered(label2);
                labelMouseEntered(label3);
                imageView.setScaleY(1.1);
                imageView.setScaleX(1.1);
            }
        });

        label2.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
//                pane.setStyle("-fx-background-color: #975bfe; -fx-background-radius: 4px");
//                pane.setStyle("-fx-background-radius: 4px");
                pane.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 4px");
                pane.setScaleX(1.05);
                pane.setScaleY(1.05);
                pane.setEffect(addPaneEffect());
                labelMouseEntered(label1);
                labelMouseEntered(label2);
                labelMouseEntered(label3);
                imageView.setScaleY(1.1);
                imageView.setScaleX(1.1);
            }
        });

        label3.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
//                pane.setStyle("-fx-background-color: #975bfe; -fx-background-radius: 4px");
//                pane.setStyle("-fx-background-radius: 4px");
                pane.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 4px");
                pane.setScaleX(1.05);
                pane.setScaleY(1.05);
                pane.setEffect(addPaneEffect());
                labelMouseEntered(label1);
                labelMouseEntered(label2);
                labelMouseEntered(label3);
                imageView.setScaleY(1.1);
                imageView.setScaleX(1.1);

            }
        });

        imageView.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
//                pane.setStyle("-fx-background-color: #975bfe; -fx-background-radius: 4px");
//                pane.setStyle("-fx-background-radius: 4px");
                pane.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 4px");
                pane.setScaleX(1.05);
                pane.setScaleY(1.05);
                labelMouseEntered(label1);
                labelMouseEntered(label2);
                labelMouseEntered(label3);
                imageView.setScaleY(1.1);
                imageView.setScaleX(1.1);
            }
        });

        pane.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
//                pane.setStyle("-fx-background-color: #975bfe; -fx-background-radius: 4px");
//                pane.setStyle("-fx-background-radius: 4px");
                pane.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 4px");
                pane.setScaleX(1.05);
                pane.setScaleY(1.05);
                pane.setEffect(addPaneEffect());
                labelMouseEntered(label1);
                labelMouseEntered(label2);
                labelMouseEntered(label3);
                imageView.setScaleY(1.1);
                imageView.setScaleX(1.1);
            }
        });

        pane.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
//                label1.setTextFill(color_tv_label);
//                label2.setTextFill(color_tv_idadi);
//                label3.setTextFill(color_tv_no);
                label1.setScaleX(1);
                label2.setScaleX(1);
                label3.setScaleX(1);
                label1.setScaleY(1);
                label2.setScaleY(1);
                label3.setScaleY(1);
                pane.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 4px");
                pane.setScaleX(1);
                pane.setScaleY(1);
                pane.setEffect(effect);
                imageView.setScaleY(1);

                imageView.setScaleX(1);
            }
        });

        pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
//                    act_col = action_col;

                    String[] stock_name;
                    for (Stock stocks : db.getStocks()) {
                        stock_name = stocks.getProduct_name().split("/");
//                        System.out.println(stocks.getProduct_name());
                        if (label1.getText().toUpperCase().equals(stock_name[0])) {
//                            System.out.println(stocks.getProduct_name() + " it's total is: " + total);
                            available_stocks.add(new Stock(stocks.getId(), stocks.getProduct_name(), stocks.getProduct_id(), stocks.getAvailable_quantity()));
                        }
                    }

                    if (sales_tab.isSelected()) {
                        p_sales_anchorpane = sales_anchorpane;
                        p_dash_item_pane = dash_item_pane;
                        p_item_dash_holder = stack_dash_holder;
                        tab_identifier = "sales";
                        BounceOut out = new BounceOut(dash_item_pane);
                        category_sale = label1.getText();
                        category_quantity = label3.getText();
                        add_sale_pane = FXMLLoader.load(getClass().getResource("FXML/add_sales.fxml"));
                        BounceIn in = new BounceIn(add_sale_pane);
                        out.setOnFinished(event1 -> {
                            sales_anchorpane.setTopAnchor(stack_dash_holder, 75.0);
                            stack_dash_holder.getChildren().setAll(add_sale_pane);
                            in.play();
                        });
                        out.play();
                    } else {
                        p_sales_anchorpane = purchase_anchorpane;
                        p_dash_item_pane = pur_dash_item_pane;
                        p_item_dash_holder = stack_pur_dash_holder;
                        tab_identifier = "purchases";
                        BounceOut out = new BounceOut(pur_dash_item_pane);
                        category_sale = label1.getText();
                        category_quantity = label3.getText();
                        add_sale_pane = FXMLLoader.load(getClass().getResource("FXML/add_sales.fxml"));
                        BounceIn in = new BounceIn(add_sale_pane);
                        out.setOnFinished(event1 -> {
                            purchase_anchorpane.setTopAnchor(stack_pur_dash_holder, 75.0);
                            stack_pur_dash_holder.getChildren().setAll(add_sale_pane);
                            in.play();
                        });
                        out.play();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void mousePurEvents(Label label1, ImageView imageView, StackPane pane) {

        Effect effect = tv_stackpane.getEffect();

        label1.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
//                pane.setStyle("-fx-background-color: #975bfe; -fx-background-radius: 4px");
                pane.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 4px");
                pane.setScaleX(1.05);
                pane.setScaleY(1.05);
                pane.setEffect(addPaneEffect());
                labelMouseEntered(label1);
                imageView.setScaleY(1.1);
                imageView.setScaleX(1.1);
            }
        });

        imageView.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
//                pane.setStyle("-fx-background-color: #975bfe; -fx-background-radius: 4px");
//                pane.setStyle("-fx-background-radius: 4px");
                pane.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 4px");
                pane.setScaleX(1.05);
                pane.setScaleY(1.05);
                labelMouseEntered(label1);
                imageView.setScaleY(1.1);
                imageView.setScaleX(1.1);
            }
        });

        pane.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
//                pane.setStyle("-fx-background-color: #975bfe; -fx-background-radius: 4px");
//                pane.setStyle("-fx-background-radius: 4px");
                pane.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 4px");
                pane.setScaleX(1.05);
                pane.setScaleY(1.05);
                pane.setEffect(addPaneEffect());
                labelMouseEntered(label1);
                imageView.setScaleY(1.1);
                imageView.setScaleX(1.1);
            }
        });

        pane.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
//                label1.setTextFill(color_tv_label);
//                label2.setTextFill(color_tv_idadi);
//                label3.setTextFill(color_tv_no);
                label1.setScaleX(1);
                label1.setScaleY(1);
                pane.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 4px");
                pane.setScaleX(1);
                pane.setScaleY(1);
                pane.setEffect(effect);
                imageView.setScaleY(1);

                imageView.setScaleX(1);
            }
        });

        pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
//                    act_col = action_col;

                    String[] stock_name;
                    for (Stock stocks : db.getStocks()) {
                        stock_name = stocks.getProduct_name().split("/");
//                        System.out.println(stocks.getProduct_name());
                        if (label1.getText().toUpperCase().equals(stock_name[0])) {
//                            System.out.println(stocks.getProduct_name() + " it's total is: " + total);
                            available_stocks.add(new Stock(stocks.getId(), stocks.getProduct_name(), stocks.getProduct_id(), stocks.getAvailable_quantity()));
                        }
                    }

                    if (sales_tab.isSelected()) {
                        p_sales_anchorpane = sales_anchorpane;
                        p_dash_item_pane = dash_item_pane;
                        p_item_dash_holder = stack_dash_holder;
                        tab_identifier = "sales";
                        BounceOut out = new BounceOut(dash_item_pane);
                        category_sale = label1.getText();
                        add_sale_pane = FXMLLoader.load(getClass().getResource("FXML/add_sales.fxml"));
                        BounceIn in = new BounceIn(add_sale_pane);
                        out.setOnFinished(event1 -> {
                            sales_anchorpane.setTopAnchor(stack_dash_holder, 75.0);
                            stack_dash_holder.getChildren().setAll(add_sale_pane);
                            in.play();
                        });
                        out.play();
                    } else {
                        p_sales_anchorpane = purchase_anchorpane;
                        p_dash_item_pane = pur_dash_item_pane;
                        p_item_dash_holder = stack_pur_dash_holder;
                        tab_identifier = "purchases";
                        BounceOut out = new BounceOut(pur_dash_item_pane);
                        category_sale = label1.getText();
                        add_sale_pane = FXMLLoader.load(getClass().getResource("FXML/add_sales.fxml"));
                        BounceIn in = new BounceIn(add_sale_pane);
                        out.setOnFinished(event1 -> {
                            purchase_anchorpane.setTopAnchor(stack_pur_dash_holder, 75.0);
                            stack_pur_dash_holder.getChildren().setAll(add_sale_pane);
                            in.play();
                        });
                        out.play();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void refreshItemPane(Label label1, Label label3) {
        int total = 0;
        String[] stock_name;

        for (Stock stocks : db.getStocks()) {
            stock_name = stocks.getProduct_name().split("/");
//            System.out.println(stock_name[0]);
            if (label1.getText().toUpperCase().equals(stock_name[0])) {
                total = total + stocks.getAvailable_quantity();
//                System.out.println(stocks.getProduct_name() + " it's total is: " + total);
            }
        }

        label3.setText(String.valueOf(total));
    }

    public void refreshItemPane(String item, int i){
        if(item.toUpperCase().equals("TV")){
            _tv_.setText(String.valueOf(Integer.parseInt(_tv_.getText()) - i));
        }else if(item.toUpperCase().equals("WIRE")){
            _wire_.setText(String.valueOf(Integer.parseInt(_wire_.getText()) - i));
        }else if(item.toUpperCase().equals("TV-STAND")){
            _tv_stand_.setText(String.valueOf(Integer.parseInt(_tv_stand_.getText()) - i));
        }else if(item.toUpperCase().equals("MASHUKA")){
            _mashuka_.setText(String.valueOf(Integer.parseInt(_mashuka_.getText()) - i));
        }else if(item.toUpperCase().equals("PASI")){
            _pasi_.setText(String.valueOf(Integer.parseInt(_pasi_.getText()) - i));
        }else if(item.toUpperCase().equals("SOCKET")){
            _socket_.setText(String.valueOf(Integer.parseInt(_socket_.getText()) - i));
        }else if(item.toUpperCase().equals("DEKI")){
            _deki_.setText(String.valueOf(Integer.parseInt(_deki_.getText()) - i));
        }else if(item.toUpperCase().equals("REMOTE")){
            _remote_.setText(String.valueOf(Integer.parseInt(_remote_.getText()) - i));
        }else if(item.toUpperCase().equals("REDIO")){
            _redio_.setText(String.valueOf(Integer.parseInt(_redio_.getText()) - i));
        }else if(item.toUpperCase().equals("SPIKA")){
            _spika_.setText(String.valueOf(Integer.parseInt(_spika_.getText()) - i));
        }else if(item.toUpperCase().equals("FAN")){
            _fan_.setText(String.valueOf(Integer.parseInt(_fan_.getText()) - i));
        }else if(item.toUpperCase().equals("SOLAR TAA")){
            _taa_.setText(String.valueOf(Integer.parseInt(_taa_.getText()) - i));
        }else if(item.toUpperCase().equals("FRIJI")){
            _friji_.setText(String.valueOf(Integer.parseInt(_friji_.getText()) - i));
        }
    }

    public void refreshItemStockPane(String item, int i) {
        if (item.toUpperCase().equals("TV")) {
            _tv_.setText(String.valueOf(Integer.parseInt(_tv_.getText()) + i));
        } else if (item.toUpperCase().equals("WIRE")) {
            _wire_.setText(String.valueOf(Integer.parseInt(_wire_.getText()) + i));
        } else if (item.toUpperCase().equals("TV-STAND")) {
            _tv_stand_.setText(String.valueOf(Integer.parseInt(_tv_stand_.getText()) + i));
        } else if (item.toUpperCase().equals("MASHUKA")) {
            _mashuka_.setText(String.valueOf(Integer.parseInt(_mashuka_.getText()) + i));
        } else if (item.toUpperCase().equals("PASI")) {
            _pasi_.setText(String.valueOf(Integer.parseInt(_pasi_.getText()) + i));
        } else if (item.toUpperCase().equals("SOCKET")) {
            _socket_.setText(String.valueOf(Integer.parseInt(_socket_.getText()) + i));
        } else if (item.toUpperCase().equals("DEKI")) {
            _deki_.setText(String.valueOf(Integer.parseInt(_deki_.getText()) + i));
        } else if (item.toUpperCase().equals("REMOTE")) {
            _remote_.setText(String.valueOf(Integer.parseInt(_remote_.getText()) + i));
        } else if (item.toUpperCase().equals("REDIO")) {
            _redio_.setText(String.valueOf(Integer.parseInt(_redio_.getText()) + i));
        } else if (item.toUpperCase().equals("SPIKA")) {
            _spika_.setText(String.valueOf(Integer.parseInt(_spika_.getText()) + i));
        } else if (item.toUpperCase().equals("FAN")) {
            _fan_.setText(String.valueOf(Integer.parseInt(_fan_.getText()) + i));
        } else if (item.toUpperCase().equals("SOLAR TAA")) {
            _taa_.setText(String.valueOf(Integer.parseInt(_taa_.getText()) + i));
        } else if (item.toUpperCase().equals("FRIJI")) {
            _friji_.setText(String.valueOf(Integer.parseInt(_friji_.getText()) + i));
        }
    }

    public void refreshStoreItemPane(Label label1, Label label3) {
        int total = 0;
        String[] stock_name;

        for (Stock stocks : db.getStoreStocks()) {
            stock_name = stocks.getProduct_name().split("/");
//            System.out.println(stock_name[0]);
            if (label1.getText().toUpperCase().equals(stock_name[0])) {
                total = total + stocks.getAvailable_quantity();
//                System.out.println(stocks.getProduct_name() + " it's total is: " + total);
            }
        }

        label3.setText(String.valueOf(total));
    }

    public void refreshAddStoreItemPane(String item, int i){
        if(item.toUpperCase().equals("TV")){
            tv_.setText(String.valueOf(Integer.parseInt(tv_.getText()) + i));
        }else if(item.toUpperCase().equals("WIRE")){
            wire_.setText(String.valueOf(Integer.parseInt(wire_.getText()) + i));
        }else if(item.toUpperCase().equals("TV-STAND")){
            tv_stand_.setText(String.valueOf(Integer.parseInt(tv_stand_.getText()) + i));
        }else if(item.toUpperCase().equals("MASHUKA")){
            mashuka_.setText(String.valueOf(Integer.parseInt(mashuka_.getText()) + i));
        }else if(item.toUpperCase().equals("PASI")){
            pasi_.setText(String.valueOf(Integer.parseInt(pasi_.getText()) + i));
        }else if(item.toUpperCase().equals("SOCKET")){
            socket_.setText(String.valueOf(Integer.parseInt(socket_.getText()) + i));
        }else if(item.toUpperCase().equals("DEKI")){
            deki_.setText(String.valueOf(Integer.parseInt(deki_.getText()) + i));
        }else if(item.toUpperCase().equals("REMOTE")){
            remote_.setText(String.valueOf(Integer.parseInt(remote_.getText()) + i));
        }else if(item.toUpperCase().equals("REDIO")){
            redio_.setText(String.valueOf(Integer.parseInt(redio_.getText()) + i));
        }else if(item.toUpperCase().equals("SPIKA")){
            spika_.setText(String.valueOf(Integer.parseInt(spika_.getText()) + i));
        }else if(item.toUpperCase().equals("FAN")){
            fan_.setText(String.valueOf(Integer.parseInt(fan_.getText()) + i));
        }else if(item.toUpperCase().equals("SOLAR TAA")){
            taa_.setText(String.valueOf(Integer.parseInt(taa_.getText()) + i));
        }else if(item.toUpperCase().equals("FRIJI")){
            friji_.setText(String.valueOf(Integer.parseInt(friji_.getText()) + i));
        }
    }

    public void refreshMinusStoreItemPane(String item, int i){
        if(item.toUpperCase().equals("TV")){
            tv_.setText(String.valueOf(Integer.parseInt(tv_.getText()) + i));
        }else if(item.toUpperCase().equals("WIRE")){
            wire_.setText(String.valueOf(Integer.parseInt(wire_.getText()) + i));
        }else if(item.toUpperCase().equals("TV-STAND")){
            tv_stand_.setText(String.valueOf(Integer.parseInt(tv_stand_.getText()) + i));
        }else if(item.toUpperCase().equals("MASHUKA")){
            mashuka_.setText(String.valueOf(Integer.parseInt(mashuka_.getText()) + i));
        }else if(item.toUpperCase().equals("PASI")){
            pasi_.setText(String.valueOf(Integer.parseInt(pasi_.getText()) + i));
        }else if(item.toUpperCase().equals("SOCKET")){
            socket_.setText(String.valueOf(Integer.parseInt(socket_.getText()) + i));
        }else if(item.toUpperCase().equals("DEKI")){
            deki_.setText(String.valueOf(Integer.parseInt(deki_.getText()) + i));
        }else if(item.toUpperCase().equals("REMOTE")){
            remote_.setText(String.valueOf(Integer.parseInt(remote_.getText()) + i));
        }else if(item.toUpperCase().equals("REDIO")){
            redio_.setText(String.valueOf(Integer.parseInt(redio_.getText()) + i));
        }else if(item.toUpperCase().equals("SPIKA")){
            spika_.setText(String.valueOf(Integer.parseInt(spika_.getText()) + i));
        }else if(item.toUpperCase().equals("FAN")){
            fan_.setText(String.valueOf(Integer.parseInt(fan_.getText()) + i));
        }else if(item.toUpperCase().equals("SOLAR TAA")){
            taa_.setText(String.valueOf(Integer.parseInt(taa_.getText()) + i));
        }else if(item.toUpperCase().equals("FRIJI")){
            friji_.setText(String.valueOf(Integer.parseInt(friji_.getText()) + i));
        }
    }

    public void refreshStoreItemPane(String item, int i) {

        if(item.toUpperCase().equals("TV")){
            st_tv_no.setText(String.valueOf(Integer.parseInt(st_tv_no.getText()) - i));
        }else if(item.toUpperCase().equals("WIRE")){
            st_wire_no.setText(String.valueOf(Integer.parseInt(st_wire_no.getText()) - i));
        }else if(item.toUpperCase().equals("TV-STAND")){
            st_tv_stand_no.setText(String.valueOf(Integer.parseInt(st_tv_stand_no.getText()) - i));
        }else if(item.toUpperCase().equals("MASHUKA")){
            st_mashuka_no.setText(String.valueOf(Integer.parseInt(st_mashuka_no.getText()) - i));
        }else if(item.toUpperCase().equals("PASI")){
            st_pasi_no.setText(String.valueOf(Integer.parseInt(st_pasi_no.getText()) - i));
        }else if(item.toUpperCase().equals("SOCKET")){
            st_socket_no.setText(String.valueOf(Integer.parseInt(st_socket_no.getText()) - i));
        }else if(item.toUpperCase().equals("DEKI")){
            st_deki_no.setText(String.valueOf(Integer.parseInt(st_deki_no.getText()) - i));
        }else if(item.toUpperCase().equals("REMOTE")){
            st_remote_no.setText(String.valueOf(Integer.parseInt(st_remote_no.getText()) - i));
        }else if(item.toUpperCase().equals("REDIO")){
            st_redio_no.setText(String.valueOf(Integer.parseInt(st_redio_no.getText()) - i));
        }else if(item.toUpperCase().equals("SPIKA")){
            st_spika_no.setText(String.valueOf(Integer.parseInt(st_spika_no.getText()) - i));
        }else if(item.toUpperCase().equals("FAN")){
            st_fan_no.setText(String.valueOf(Integer.parseInt(st_fan_no.getText()) - i));
        }else if(item.toUpperCase().equals("SOLAR TAA")){
            st_taa_no.setText(String.valueOf(Integer.parseInt(st_taa_no.getText()) - i));
        }else if(item.toUpperCase().equals("FRIJI")){
            st_friji_no.setText(String.valueOf(Integer.parseInt(st_friji_no.getText()) - i));
        }
    }

    public void mouseEventsStore(Label label1, Label label2, Label label3, ImageView imageView, AnchorPane pane) {

        Effect effect = tv_stackpane.getEffect();

        refreshStoreItemPane(label1, label3);

        label1.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
//                pane.setStyle("-fx-background-color: #975bfe; -fx-background-radius: 4px");
                pane.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 4px");
                pane.setScaleX(1.05);
                pane.setScaleY(1.05);
                pane.setEffect(addPaneEffect());
                labelMouseEntered(label1);
                labelMouseEntered(label2);
                labelMouseEntered(label3);
                imageView.setScaleY(1.1);
                imageView.setScaleX(1.1);
            }
        });

        label2.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
//                pane.setStyle("-fx-background-color: #975bfe; -fx-background-radius: 4px");
//                pane.setStyle("-fx-background-radius: 4px");
                pane.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 4px");
                pane.setScaleX(1.05);
                pane.setScaleY(1.05);
                pane.setEffect(addPaneEffect());
                labelMouseEntered(label1);
                labelMouseEntered(label2);
                labelMouseEntered(label3);
                imageView.setScaleY(1.1);
                imageView.setScaleX(1.1);
            }
        });

        label3.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
//                pane.setStyle("-fx-background-color: #975bfe; -fx-background-radius: 4px");
//                pane.setStyle("-fx-background-radius: 4px");
                pane.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 4px");
                pane.setScaleX(1.05);
                pane.setScaleY(1.05);
                pane.setEffect(addPaneEffect());
                labelMouseEntered(label1);
                labelMouseEntered(label2);
                labelMouseEntered(label3);
                imageView.setScaleY(1.1);
                imageView.setScaleX(1.1);

            }
        });

        imageView.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
//                pane.setStyle("-fx-background-color: #975bfe; -fx-background-radius: 4px");
//                pane.setStyle("-fx-background-radius: 4px");
                pane.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 4px");
                pane.setScaleX(1.05);
                pane.setScaleY(1.05);
                labelMouseEntered(label1);
                labelMouseEntered(label2);
                labelMouseEntered(label3);
                imageView.setScaleY(1.1);
                imageView.setScaleX(1.1);
            }
        });

        pane.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
//                pane.setStyle("-fx-background-color: #975bfe; -fx-background-radius: 4px");
//                pane.setStyle("-fx-background-radius: 4px");
                pane.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 4px");
                pane.setScaleX(1.05);
                pane.setScaleY(1.05);
                pane.setEffect(addPaneEffect());
                labelMouseEntered(label1);
                labelMouseEntered(label2);
                labelMouseEntered(label3);
                imageView.setScaleY(1.1);
                imageView.setScaleX(1.1);
            }
        });

        pane.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
//                label1.setTextFill(color_tv_label);
//                label2.setTextFill(color_tv_idadi);
//                label3.setTextFill(color_tv_no);
                label1.setScaleX(1);
                label2.setScaleX(1);
                label3.setScaleX(1);
                label1.setScaleY(1);
                label2.setScaleY(1);
                label3.setScaleY(1);
                pane.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 4px");
                pane.setScaleX(1);
                pane.setScaleY(1);
                pane.setEffect(effect);
                imageView.setScaleY(1);

                imageView.setScaleX(1);
            }
        });

        pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                total = 0;

                available_store_stocks = FXCollections.observableArrayList();

                String[] stock_name;
                for (Stock stocks : db.getStoreStocks()) {
                    stock_name = stocks.getProduct_name().split("/");
//                        System.out.println(stocks.getProduct_name());
                    if (label1.getText().toUpperCase().equals(stock_name[0])) {
//                        System.out.println(stocks.getProduct_name() + " it's total is: " + total);
                        available_store_stocks.add(new Stock(stocks.getId(), stocks.getProduct_name(), stocks.getProduct_id(), stocks.getAvailable_quantity()));
                    }
                }

                System.out.println(available_store_stocks.size());
                category_item_store = label1.getText();
                store_sub_category.setItems(null);
                store_item_category.setLayoutY(160);
//                System.out.println(category_item_store);
                if (category_item_store.toLowerCase().equals("tv")) {
                    store_item_category.setItems(tv);
                    store_item_category.setValue(tv.get(0));
                    store_sub_category.setItems(tv_homebase_chogo);
                    store_sub_category.setVisible(true);

                    total = 0;

                    for (Stock stock : available_store_stocks) {
                        pName = stock.getProduct_name().split("/");
//                        System.out.println("This is for the stock product name: " + stock.getProduct_name());
//                        System.out.println("This is for the selected product name: " + label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString());
//                System.out.println(pName[0]);
//                System.out.println(pName[1]);
                        if ((pName[0] + "/" + pName[1]).equals(label1.getText().toUpperCase().toUpperCase() + "/" + store_item_category.getValue().toString())) {
                            total = total + stock.getAvailable_quantity();
                        }
                    }

                    st_av_quantity.setText("Available quantity = " + total);

                    store_sub_category.valueProperty().addListener(new ChangeListener() {
                        @Override
                        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                            total = 0;
                            for (Stock stock : DashboardController.available_store_stocks) {
                                pName = stock.getProduct_name().split("/");
                                if (stock.getProduct_name().equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString() + "/" + store_sub_category.getValue().toString())) {
                                    total = total + stock.getAvailable_quantity();
                                }
                            }

                            st_av_quantity.setText("Available quantity = " + total);

                            store_category_quantity = String.valueOf(total);
                        }
                    });

                    store_item_category.valueProperty().addListener(new ChangeListener() {
                        @Override
                        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                            if (store_item_category.getValue().toString().equals("HOMEBASE CHOGO")) {
                                store_sub_category.setItems(tv_homebase_chogo);
//                                System.out.println(store_sub_category.getValue().toString());

                                total = 0;
                                for (Stock stock : available_store_stocks) {
                                    pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
//                            System.out.println("This is a new one: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString());
                                    if ((pName[0] + "/" + pName[1]).equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString())) {
                                        total = total + stock.getAvailable_quantity();
                                    }
                                }

                                store_sub_category.valueProperty().addListener(new ChangeListener() {
                                    @Override
                                    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                                        total = 0;
                                        for (Stock stock : available_store_stocks) {
                                            pName = stock.getProduct_name().split("/");
                                            if (stock.getProduct_name().equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString() + "/" + store_sub_category.getValue().toString())) {
                                                total = total + stock.getAvailable_quantity();
                                            }
                                        }

                                        st_av_quantity.setText("Available quantity = " + total);

                                        store_category_quantity = String.valueOf(total);
                                    }
                                });

                                st_av_quantity.setText("Available quantity = " + total);
                            } else if (store_item_category.getValue().toString().equals("SING SAN")) {
                                store_sub_category.setItems(tv_singsang);

                                total = 0;
                                for (Stock stock : available_store_stocks) {
                                    pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
//                            System.out.println("This is a new one: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString());
                                    if ((pName[0] + "/" + pName[1]).equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString())) {
                                        total = total + stock.getAvailable_quantity();
                                    }
                                }

                                st_av_quantity.setText("Available quantity = " + total);

                                store_sub_category.valueProperty().addListener(new ChangeListener() {
                                    @Override
                                    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                                        total = 0;
                                        for (Stock stock : available_store_stocks) {
                                            pName = stock.getProduct_name().split("/");
                                            if (stock.getProduct_name().equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString() + "/" + store_sub_category.getValue().toString())) {
                                                total = total + stock.getAvailable_quantity();
                                            }
                                        }

                                        st_av_quantity.setText("Available quantity = " + total);

                                        store_category_quantity = String.valueOf(total);
                                    }
                                });

                                st_av_quantity.setText("Available quantity = " + total);
//                                System.out.println(store_sub_category.getValue().toString());
                            } else if (store_item_category.getValue().toString().equals("HOMEBASE FLAT")) {
                                store_sub_category.setItems(tv_homebase_flat);

                                total = 0;
                                for (Stock stock : available_store_stocks) {
                                    pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
//                            System.out.println("This is a new one: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString());
                                    if ((pName[0] + "/" + pName[1]).equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString())) {
                                        total = total + stock.getAvailable_quantity();
                                    }
                                }

                                store_sub_category.valueProperty().addListener(new ChangeListener() {
                                    @Override
                                    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                                        total = 0;
                                        for (Stock stock : available_store_stocks) {
                                            pName = stock.getProduct_name().split("/");
                                            if (stock.getProduct_name().equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString() + "/" + store_sub_category.getValue().toString())) {
                                                total = total + stock.getAvailable_quantity();
                                            }
                                        }

                                        st_av_quantity.setText("Available quantity = " + total);

                                        store_category_quantity = String.valueOf(total);
                                    }
                                });

                                st_av_quantity.setText("Available quantity = " + total);
                            } else if (store_item_category.getValue().toString().equals("MR UK CHOGO")) {
                                store_sub_category.setItems(tv_mr_uk_chogo);

                                total = 0;
                                for (Stock stock : available_store_stocks) {
                                    pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
//                            System.out.println("This is a new one: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString());
                                    if ((pName[0] + "/" + pName[1]).equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString())) {
                                        total = total + stock.getAvailable_quantity();
                                    }
                                }

                                store_sub_category.valueProperty().addListener(new ChangeListener() {
                                    @Override
                                    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                                        total = 0;
                                        for (Stock stock : available_store_stocks) {
                                            pName = stock.getProduct_name().split("/");
                                            if (stock.getProduct_name().equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString() + "/" + store_sub_category.getValue().toString())) {
                                                total = total + stock.getAvailable_quantity();
                                            }
                                        }

                                        st_av_quantity.setText("Available quantity = " + total);

                                        store_category_quantity = String.valueOf(total);
                                    }
                                });

                                st_av_quantity.setText("Available quantity = " + total);
                            } else if (store_item_category.getValue().toString().equals("MR UK FLAT")) {
                                store_sub_category.setItems(tv_mr_uk_flat);

                                total = 0;
                                for (Stock stock : available_store_stocks) {
                                    pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
//                            System.out.println("This is a new one: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString());
                                    if ((pName[0] + "/" + pName[1]).equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString())) {
                                        total = total + stock.getAvailable_quantity();
                                    }
                                }

                                store_sub_category.valueProperty().addListener(new ChangeListener() {
                                    @Override
                                    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                                        total = 0;
                                        for (Stock stock : available_store_stocks) {
                                            pName = stock.getProduct_name().split("/");
                                            if (stock.getProduct_name().equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString() + "/" + store_sub_category.getValue().toString())) {
                                                total = total + stock.getAvailable_quantity();
                                            }
                                        }

                                        st_av_quantity.setText("Available quantity = " + total);

                                        store_category_quantity = String.valueOf(total);
                                    }
                                });

                                st_av_quantity.setText("Available quantity = " + total);

                            } else if (store_item_category.getValue().toString().equals("STAR X")) {
                                store_sub_category.setItems(tv_star_x);

                                total = 0;
                                for (Stock stock : available_store_stocks) {
                                    pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
//                            System.out.println("This is a new one: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString());
                                    if ((pName[0] + "/" + pName[1]).equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString())) {
                                        total = total + stock.getAvailable_quantity();
                                    }
                                }

                                store_sub_category.valueProperty().addListener(new ChangeListener() {
                                    @Override
                                    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                                        total = 0;
                                        for (Stock stock : available_store_stocks) {
                                            pName = stock.getProduct_name().split("/");
                                            if (stock.getProduct_name().equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString() + "/" + store_sub_category.getValue().toString())) {
                                                total = total + stock.getAvailable_quantity();
                                            }
                                        }

                                        st_av_quantity.setText("Available quantity = " + total);

                                        store_category_quantity = String.valueOf(total);
                                    }
                                });

                                st_av_quantity.setText("Available quantity = " + total);

                            } else if (store_item_category.getValue().toString().equals("RISING")) {
                                store_sub_category.setItems(tv_rising);

                                total = 0;
                                for (Stock stock : available_store_stocks) {
                                    pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
//                            System.out.println("This is a new one: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString());
                                    if ((pName[0] + "/" + pName[1]).equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString())) {
                                        total = total + stock.getAvailable_quantity();
                                    }
                                }

                                store_sub_category.valueProperty().addListener(new ChangeListener() {
                                    @Override
                                    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                                        total = 0;
                                        for (Stock stock : available_store_stocks) {
                                            pName = stock.getProduct_name().split("/");
                                            if (stock.getProduct_name().equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString() + "/" + store_sub_category.getValue().toString())) {
                                                total = total + stock.getAvailable_quantity();
                                            }
                                        }

                                        st_av_quantity.setText("Available quantity = " + total);

                                        store_category_quantity = String.valueOf(total);
                                    }
                                });

                                st_av_quantity.setText("Available quantity = " + total);

                            } else if (store_item_category.getValue().toString().equals("OCNG")) {
                                store_sub_category.setItems(tv_ocng);

                                total = 0;
                                for (Stock stock : available_store_stocks) {
                                    pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
//                            System.out.println("This is a new one: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString());
                                    if ((pName[0] + "/" + pName[1]).equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString())) {
                                        total = total + stock.getAvailable_quantity();
                                    }
                                }

                                store_sub_category.valueProperty().addListener(new ChangeListener() {
                                    @Override
                                    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                                        total = 0;
                                        for (Stock stock : DashboardController.available_store_stocks) {
                                            pName = stock.getProduct_name().split("/");
                                            if (stock.getProduct_name().equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString() + "/" + store_sub_category.getValue().toString())) {
                                                total = total + stock.getAvailable_quantity();
                                            }
                                        }

                                        st_av_quantity.setText("Available quantity = " + total);

                                        store_category_quantity = String.valueOf(total);
                                    }
                                });

                                st_av_quantity.setText("Available quantity = " + total);

                            } else if (store_item_category.getValue().toString().equals("RITECH")) {
                                store_sub_category.setItems(tv_ritech);

                                total = 0;
                                for (Stock stock : available_store_stocks) {
                                    pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
//                            System.out.println("This is a new one: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString());
                                    if ((pName[0] + "/" + pName[1]).equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString())) {
                                        total = total + stock.getAvailable_quantity();
                                    }
                                }

                                store_sub_category.valueProperty().addListener(new ChangeListener() {
                                    @Override
                                    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                                        total = 0;
                                        for (Stock stock : DashboardController.available_store_stocks) {
                                            pName = stock.getProduct_name().split("/");
                                            if (stock.getProduct_name().equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString() + "/" + store_sub_category.getValue().toString())) {
                                                total = total + stock.getAvailable_quantity();
                                            }
                                        }

                                        st_av_quantity.setText("Available quantity = " + total);

                                        store_category_quantity = String.valueOf(total);
                                    }
                                });

                                st_av_quantity.setText("Available quantity = " + total);

                            } else if (store_item_category.getValue().toString().equals("SUNDAR")) {
                                store_sub_category.setItems(tv_sundar);

                                total = 0;
                                for (Stock stock : available_store_stocks) {
                                    pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
//                            System.out.println("This is a new one: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString());
                                    if ((pName[0] + "/" + pName[1]).equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString())) {
                                        total = total + stock.getAvailable_quantity();
                                    }
                                }

                                store_sub_category.valueProperty().addListener(new ChangeListener() {
                                    @Override
                                    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                                        total = 0;
                                        for (Stock stock : DashboardController.available_store_stocks) {
                                            pName = stock.getProduct_name().split("/");
                                            if (stock.getProduct_name().equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString() + "/" + store_sub_category.getValue().toString())) {
                                                total = total + stock.getAvailable_quantity();
                                            }
                                        }

                                        st_av_quantity.setText("Available quantity = " + total);

                                        store_category_quantity = String.valueOf(total);
                                    }
                                });

                                st_av_quantity.setText("Available quantity = " + total);

                            } else if (store_item_category.getValue().toString().equals("EVOL")) {
                                store_sub_category.setItems(tv_evol);

                                total = 0;
                                for (Stock stock : available_store_stocks) {
                                    pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
//                            System.out.println("This is a new one: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString());
                                    if ((pName[0] + "/" + pName[1]).equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString())) {
                                        total = total + stock.getAvailable_quantity();
                                    }
                                }

                                store_sub_category.valueProperty().addListener(new ChangeListener() {
                                    @Override
                                    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                                        total = 0;
                                        for (Stock stock : DashboardController.available_store_stocks) {
                                            pName = stock.getProduct_name().split("/");
                                            if (stock.getProduct_name().equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString() + "/" + store_sub_category.getValue().toString())) {
                                                total = total + stock.getAvailable_quantity();
                                            }
                                        }

                                        st_av_quantity.setText("Available quantity = " + total);

                                        store_category_quantity = String.valueOf(total);
                                    }
                                });

                                st_av_quantity.setText("Available quantity = " + total);

                            }
                        }
                    });
                } else if (category_item_store.toLowerCase().equals("wire")) {
                    store_sub_category.setVisible(false);
                    store_item_category.setLayoutY(250);
                    store_item_category.setItems(wire);
                    store_item_category.setValue(wire.get(0));

                    total = 0;

                    for (Stock stock : available_store_stocks) {
                        pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
//                            System.out.println("This is a new one: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString());
                        if ((pName[0] + "/" + pName[1]).equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString())) {
                            total = total + stock.getAvailable_quantity();
                        }
                    }

                    st_av_quantity.setText("Available quantity = " + total);

                    store_category_quantity = String.valueOf(total);

                } else if (category_item_store.toLowerCase().equals("tv-stand")) {
                    store_sub_category.setVisible(false);
                    store_item_category.setLayoutY(250);
                    store_item_category.setItems(tv_stand);
                    store_item_category.setValue(tv_stand.get(0));

                    total = 0;

                    for (Stock stock : available_store_stocks) {
                        pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
//                            System.out.println("This is a new one: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString());
                        if ((pName[0] + "/" + pName[1]).equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString())) {
                            total = total + stock.getAvailable_quantity();
                        }
                    }

                    st_av_quantity.setText("Available quantity = " + total);

                    store_category_quantity = String.valueOf(total);
                } else if (category_item_store.toLowerCase().equals("mashuka")) {
                    store_sub_category.setVisible(false);
                    store_item_category.setLayoutY(250);
                    store_item_category.setItems(mashuka);
                    store_item_category.setValue(mashuka.get(0));

                    total = 0;

                    for (Stock stock : available_store_stocks) {
                        pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
                        if ((pName[0] + "/" + pName[1]).equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString())) {
                            total = total + stock.getAvailable_quantity();
                            System.out.println("This is the total: " + total);
                        }
                    }

                    store_item_category.valueProperty().addListener(new ChangeListener() {
                        @Override
                        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                            total = 0;

                            for (Stock stock : available_store_stocks) {
                                pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
                                if ((pName[0] + "/" + pName[1]).equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString())) {
                                    total = total + stock.getAvailable_quantity();
                                    System.out.println("This is the total: " + total);
                                }
                            }

                            st_av_quantity.setText("Available quantity = " + total);

                            store_category_quantity = String.valueOf(total);
                        }
                    });

                    st_av_quantity.setText("Available quantity = " + total);

                    st_av_quantity.setText("Available quantity = " + total);

                } else if (category_item_store.toLowerCase().equals("pasi")) {
                    store_sub_category.setVisible(false);
                    store_item_category.setLayoutY(250);
                    store_item_category.setItems(pasi);
                    store_item_category.setValue(pasi.get(0));

                    total = 0;

                    for (Stock stock : available_store_stocks) {
                        pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
                        if ((pName[0] + "/" + pName[1]).equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString())) {
                            total = total + stock.getAvailable_quantity();
                            System.out.println("This is the total: " + total);
                        }
                    }

                    store_item_category.valueProperty().addListener(new ChangeListener() {
                        @Override
                        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                            total = 0;

                            for (Stock stock : available_store_stocks) {
                                pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
                                if ((pName[0] + "/" + pName[1]).equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString())) {
                                    total = total + stock.getAvailable_quantity();
                                    System.out.println("This is the total: " + label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString());
                                }
                            }

                            st_av_quantity.setText("Available quantity = " + total);

                            store_category_quantity = String.valueOf(total);
                        }
                    });

                    st_av_quantity.setText("Available quantity = " + total);

                    st_av_quantity.setText("Available quantity = " + total);
                } else if (category_item_store.toLowerCase().equals("socket")) {
                    store_item_category.setItems(socket);
                    store_item_category.setValue(socket.get(0));
                    store_sub_category.setItems(socket_tronic);

                    store_sub_category.setVisible(true);

                    total = 0;

                    for (Stock stock : available_store_stocks) {
                        pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
//                            System.out.println("This is a new one: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString());
                        if ((pName[0] + "/" + pName[1]).equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString())) {
                            total = total + stock.getAvailable_quantity();
                        }
                    }

                    st_av_quantity.setText("Available quantity = " + total);

                    store_sub_category.valueProperty().addListener(new ChangeListener() {
                        @Override
                        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                            total = 0;
                            for (Stock stock : DashboardController.available_store_stocks) {
                                pName = stock.getProduct_name().split("/");
                                if (stock.getProduct_name().equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString() + "/" + store_sub_category.getValue().toString())) {
                                    total = total + stock.getAvailable_quantity();
                                }
                            }

                            st_av_quantity.setText("Available quantity = " + total);

                            store_category_quantity = String.valueOf(total);
                        }
                    });

                    store_item_category.valueProperty().addListener(new ChangeListener() {
                        @Override
                        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                            if (store_item_category.getValue().toString().equals("TRONIC")) {
                                store_sub_category.setItems(socket_tronic);

                                total = 0;
                                for (Stock stock : available_store_stocks) {
                                    pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
//                            System.out.println("This is a new one: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString());
                                    if ((pName[0] + "/" + pName[1]).equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString())) {
                                        total = total + stock.getAvailable_quantity();
                                    }
                                }

                                store_sub_category.valueProperty().addListener(new ChangeListener() {
                                    @Override
                                    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                                        total = 0;
                                        for (Stock stock : DashboardController.available_store_stocks) {
                                            pName = stock.getProduct_name().split("/");
                                            if (stock.getProduct_name().equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString() + "/" + store_sub_category.getValue().toString())) {
                                                total = total + stock.getAvailable_quantity();
                                            }
                                        }

                                        st_av_quantity.setText("Available quantity = " + total);

                                        store_category_quantity = String.valueOf(total);
                                    }
                                });

                                st_av_quantity.setText("Available quantity = " + total);
                            } else if (store_item_category.getValue().toString().equals("SUNDAR")) {
                                store_sub_category.setItems(socket_sundar);

                                total = 0;
                                for (Stock stock : available_store_stocks) {
                                    pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
//                            System.out.println("This is a new one: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString());
                                    if ((pName[0] + "/" + pName[1]).equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString())) {
                                        total = total + stock.getAvailable_quantity();
                                    }
                                }

                                store_sub_category.valueProperty().addListener(new ChangeListener() {
                                    @Override
                                    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                                        total = 0;
                                        for (Stock stock : DashboardController.available_store_stocks) {
                                            pName = stock.getProduct_name().split("/");
                                            if (stock.getProduct_name().equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString() + "/" + store_sub_category.getValue().toString())) {
                                                total = total + stock.getAvailable_quantity();
                                            }
                                        }

                                        st_av_quantity.setText("Available quantity = " + total);

                                        store_category_quantity = String.valueOf(total);
                                    }
                                });

                                st_av_quantity.setText("Available quantity = " + total);
                            }
                        }
                    });

                } else if (category_item_store.toLowerCase().equals("deki")) {
                    store_item_category.setItems(deki);
                    store_item_category.setValue(deki.get(0));
                    store_sub_category.setItems(deki_uk);

                    store_sub_category.setVisible(true);

                    total = 0;

                    for (Stock stock : available_store_stocks) {
                        pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
//                            System.out.println("This is a new one: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString());
                        if ((pName[0] + "/" + pName[1]).equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString())) {
                            total = total + stock.getAvailable_quantity();
                        }
                    }

                    st_av_quantity.setText("Available quantity = " + total);

                    store_sub_category.valueProperty().addListener(new ChangeListener() {
                        @Override
                        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                            total = 0;
                            for (Stock stock : DashboardController.available_store_stocks) {
                                pName = stock.getProduct_name().split("/");
                                if (stock.getProduct_name().equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString() + "/" + store_sub_category.getValue().toString())) {
                                    total = total + stock.getAvailable_quantity();
                                }
                            }

                            st_av_quantity.setText("Available quantity = " + total);

                            store_category_quantity = String.valueOf(total);
                        }
                    });

                    store_item_category.valueProperty().addListener(new ChangeListener() {
                        @Override
                        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                            if (store_item_category.getValue().toString().equals("UK")) {
                                store_sub_category.setItems(deki_uk);

                                total = 0;
                                for (Stock stock : available_store_stocks) {
                                    pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
//                            System.out.println("This is a new one: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString());
                                    if ((pName[0] + "/" + pName[1]).equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString())) {
                                        total = total + stock.getAvailable_quantity();
                                    }
                                }

                                store_sub_category.valueProperty().addListener(new ChangeListener() {
                                    @Override
                                    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                                        total = 0;
                                        for (Stock stock : DashboardController.available_store_stocks) {
                                            pName = stock.getProduct_name().split("/");
                                            if (stock.getProduct_name().equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString() + "/" + store_sub_category.getValue().toString())) {
                                                total = total + stock.getAvailable_quantity();
                                            }
                                        }

                                        st_av_quantity.setText("Available quantity = " + total);

                                        store_category_quantity = String.valueOf(total);
                                    }
                                });

                                st_av_quantity.setText("Available quantity = " + total);
                            } else if (store_item_category.getValue().toString().equals("SUNDAR")) {
                                store_sub_category.setItems(deki_sundar);

                                total = 0;
                                for (Stock stock : available_store_stocks) {
                                    pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
//                            System.out.println("This is a new one: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString());
                                    if ((pName[0] + "/" + pName[1]).equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString())) {
                                        total = total + stock.getAvailable_quantity();
                                    }
                                }

                                store_sub_category.valueProperty().addListener(new ChangeListener() {
                                    @Override
                                    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                                        total = 0;
                                        for (Stock stock : DashboardController.available_store_stocks) {
                                            pName = stock.getProduct_name().split("/");
                                            if (stock.getProduct_name().equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString() + "/" + store_sub_category.getValue().toString())) {
                                                total = total + stock.getAvailable_quantity();
                                            }
                                        }

                                        st_av_quantity.setText("Available quantity = " + total);

                                        store_category_quantity = String.valueOf(total);
                                    }
                                });

                                st_av_quantity.setText("Available quantity = " + total);
                            } else if (store_item_category.getValue().toString().equals("REDI AT 7350")) {
                                store_sub_category.setVisible(false);
                                store_item_category.setLayoutY(250);

                                total = 0;

                                for (Stock stock : available_store_stocks) {
                                    pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
                                    if ((pName[0] + "/" + pName[1]).equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString())) {
                                        total = total + stock.getAvailable_quantity();
                                        System.out.println("This is the total: " + total);
                                    }
                                }

                                st_av_quantity.setText("Available quantity = " + total);

                                store_category_quantity = String.valueOf(total);
                            }
                        }
                    });

                } else if (category_item_store.toLowerCase().equals("remote")) {
                    store_sub_category.setVisible(false);
                    store_item_category.setLayoutY(250);
                    store_item_category.setItems(remote);
                    store_item_category.setValue(remote.get(0));

                    total = 0;

                    for (Stock stock : available_store_stocks) {
                        pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
                        if ((pName[0] + "/" + pName[1]).equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString())) {
                            total = total + stock.getAvailable_quantity();
                            System.out.println("This is the total: " + total);
                        }
                    }

                    store_item_category.valueProperty().addListener(new ChangeListener() {
                        @Override
                        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                            total = 0;

                            for (Stock stock : available_store_stocks) {
                                pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
                                if ((pName[0] + "/" + pName[1]).equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString())) {
                                    total = total + stock.getAvailable_quantity();
                                    System.out.println("This is the total: " + total);
                                }
                            }

                            st_av_quantity.setText("Available quantity = " + total);

                            store_category_quantity = String.valueOf(total);
                        }
                    });

                    st_av_quantity.setText("Available quantity = " + total);

                } else if (category_item_store.toLowerCase().equals("redio")) {
                    store_item_category.setItems(redio);
                    store_item_category.setValue(redio.get(0));
                    store_sub_category.setItems(redio_sabufa_sp);

                    store_sub_category.setVisible(true);

                    total = 0;

                    for (Stock stock : available_store_stocks) {
                        pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
//                            System.out.println("This is a new one: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString());
                        if ((pName[0] + "/" + pName[1]).equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString())) {
                            total = total + stock.getAvailable_quantity();
                        }
                    }

                    st_av_quantity.setText("Available quantity = " + total);

                    store_sub_category.valueProperty().addListener(new ChangeListener() {
                        @Override
                        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                            total = 0;
                            for (Stock stock : DashboardController.available_store_stocks) {
                                pName = stock.getProduct_name().split("/");
                                if (stock.getProduct_name().equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString() + "/" + store_sub_category.getValue().toString())) {
                                    total = total + stock.getAvailable_quantity();
                                }
                            }

                            st_av_quantity.setText("Available quantity = " + total);

                            store_category_quantity = String.valueOf(total);
                        }
                    });

                    store_item_category.valueProperty().addListener(new ChangeListener() {
                        @Override
                        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                            if (store_item_category.getValue().toString().equals("SABUFA SP")) {
                                store_sub_category.setItems(redio_sabufa_sp);

                                total = 0;
                                for (Stock stock : available_store_stocks) {
                                    pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
//                            System.out.println("This is a new one: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString());
                                    if ((pName[0] + "/" + pName[1]).equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString())) {
                                        total = total + stock.getAvailable_quantity();
                                    }
                                }

                                store_sub_category.valueProperty().addListener(new ChangeListener() {
                                    @Override
                                    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                                        total = 0;
                                        for (Stock stock : DashboardController.available_store_stocks) {
                                            pName = stock.getProduct_name().split("/");
                                            if (stock.getProduct_name().equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString() + "/" + store_sub_category.getValue().toString())) {
                                                total = total + stock.getAvailable_quantity();
                                            }
                                        }

                                        st_av_quantity.setText("Available quantity = " + total);

                                        store_category_quantity = String.valueOf(total);
                                    }
                                });

                                st_av_quantity.setText("Available quantity = " + total);
                            } else if (store_item_category.getValue().toString().equals("SABUFA ALING")) {
                                store_sub_category.setItems(redio_sabufa_aling);

                                total = 0;
                                for (Stock stock : available_store_stocks) {
                                    pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
//                            System.out.println("This is a new one: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString());
                                    if ((pName[0] + "/" + pName[1]).equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString())) {
                                        total = total + stock.getAvailable_quantity();
                                    }
                                }

                                store_sub_category.valueProperty().addListener(new ChangeListener() {
                                    @Override
                                    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                                        total = 0;
                                        for (Stock stock : DashboardController.available_store_stocks) {
                                            pName = stock.getProduct_name().split("/");
                                            if (stock.getProduct_name().equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString() + "/" + store_sub_category.getValue().toString())) {
                                                total = total + stock.getAvailable_quantity();
                                            }
                                        }

                                        st_av_quantity.setText("Available quantity = " + total);

                                        store_category_quantity = String.valueOf(total);
                                    }
                                });

                                st_av_quantity.setText("Available quantity = " + total);
                            } else if (store_item_category.getValue().toString().equals("SABUFA ABODA")) {
                                store_sub_category.setItems(redio_sabufa_aboda);

                                total = 0;
                                for (Stock stock : available_store_stocks) {
                                    pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
//                            System.out.println("This is a new one: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString());
                                    if ((pName[0] + "/" + pName[1]).equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString())) {
                                        total = total + stock.getAvailable_quantity();
                                    }
                                }

                                store_sub_category.valueProperty().addListener(new ChangeListener() {
                                    @Override
                                    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                                        total = 0;
                                        for (Stock stock : DashboardController.available_store_stocks) {
                                            pName = stock.getProduct_name().split("/");
                                            if (stock.getProduct_name().equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString() + "/" + store_sub_category.getValue().toString())) {
                                                total = total + stock.getAvailable_quantity();
                                            }
                                        }

                                        st_av_quantity.setText("Available quantity = " + total);

                                        store_category_quantity = String.valueOf(total);
                                    }
                                });

                                st_av_quantity.setText("Available quantity = " + total);
                            } else if (store_item_category.getValue().toString().equals("SABUFA MR UK")) {
                                store_sub_category.setItems(redio_sabufa_mr_uk);

                                total = 0;
                                for (Stock stock : available_store_stocks) {
                                    pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
//                            System.out.println("This is a new one: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString());
                                    if ((pName[0] + "/" + pName[1]).equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString())) {
                                        total = total + stock.getAvailable_quantity();
                                    }
                                }

                                store_sub_category.valueProperty().addListener(new ChangeListener() {
                                    @Override
                                    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                                        total = 0;
                                        for (Stock stock : DashboardController.available_store_stocks) {
                                            pName = stock.getProduct_name().split("/");
                                            if (stock.getProduct_name().equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString() + "/" + store_sub_category.getValue().toString())) {
                                                total = total + stock.getAvailable_quantity();
                                            }
                                        }

                                        st_av_quantity.setText("Available quantity = " + total);

                                        store_category_quantity = String.valueOf(total);
                                    }
                                });

                                st_av_quantity.setText("Available quantity = " + total);
                            } else if (store_item_category.getValue().toString().equals("SABUFA SUNDAR")) {
                                store_sub_category.setItems(redio_sabufa_sundar);

                                total = 0;
                                for (Stock stock : available_store_stocks) {
                                    pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
//                            System.out.println("This is a new one: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString());
                                    if ((pName[0] + "/" + pName[1]).equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString())) {
                                        total = total + stock.getAvailable_quantity();
                                    }
                                }

                                store_sub_category.valueProperty().addListener(new ChangeListener() {
                                    @Override
                                    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                                        total = 0;
                                        for (Stock stock : DashboardController.available_store_stocks) {
                                            pName = stock.getProduct_name().split("/");
                                            if (stock.getProduct_name().equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString() + "/" + store_sub_category.getValue().toString())) {
                                                total = total + stock.getAvailable_quantity();
                                            }
                                        }

                                        st_av_quantity.setText("Available quantity = " + total);

                                        store_category_quantity = String.valueOf(total);
                                    }
                                });

                                st_av_quantity.setText("Available quantity = " + total);
                            }
                        }
                    });

                } else if (category_item_store.toLowerCase().equals("spika")) {
                    store_item_category.setItems(spika);
                    store_item_category.setValue(spika.get(0));
                    store_sub_category.setItems(spika_spika_ya_gari);

                    store_sub_category.setVisible(true);

                    total = 0;

                    for (Stock stock : available_store_stocks) {
                        pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
//                            System.out.println("This is a new one: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString());
                        if ((pName[0] + "/" + pName[1]).equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString())) {
                            total = total + stock.getAvailable_quantity();
                        }
                    }

                    st_av_quantity.setText("Available quantity = " + total);

                    store_sub_category.valueProperty().addListener(new ChangeListener() {
                        @Override
                        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                            total = 0;
                            for (Stock stock : DashboardController.available_store_stocks) {
                                pName = stock.getProduct_name().split("/");
                                if (stock.getProduct_name().equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString() + "/" + store_sub_category.getValue().toString())) {
                                    total = total + stock.getAvailable_quantity();
                                }
                            }

                            st_av_quantity.setText("Available quantity = " + total);

                            store_category_quantity = String.valueOf(total);
                        }
                    });

                    store_item_category.valueProperty().addListener(new ChangeListener() {
                        @Override
                        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                            if (store_item_category.getValue().toString().equals("SPIKA YA GARI")) {
                                store_sub_category.setItems(spika_spika_ya_gari);

                                total = 0;
                                for (Stock stock : available_store_stocks) {
                                    pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
//                            System.out.println("This is a new one: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString());
                                    if ((pName[0] + "/" + pName[1]).equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString())) {
                                        total = total + stock.getAvailable_quantity();
                                    }
                                }

                                store_sub_category.valueProperty().addListener(new ChangeListener() {
                                    @Override
                                    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                                        total = 0;
                                        for (Stock stock : DashboardController.available_store_stocks) {
                                            pName = stock.getProduct_name().split("/");
                                            if (stock.getProduct_name().equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString() + "/" + store_sub_category.getValue().toString())) {
                                                total = total + stock.getAvailable_quantity();
                                            }
                                        }

                                        st_av_quantity.setText("Available quantity = " + total);

                                        store_category_quantity = String.valueOf(total);
                                    }
                                });

                                st_av_quantity.setText("Available quantity = " + total);
                            } else if (store_item_category.getValue().toString().equals("SPIKA KUBWA")) {
                                store_sub_category.setItems(spika_spika_kubwa);

                                total = 0;
                                for (Stock stock : available_store_stocks) {
                                    pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
//                            System.out.println("This is a new one: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString());
                                    if ((pName[0] + "/" + pName[1]).equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString())) {
                                        total = total + stock.getAvailable_quantity();
                                    }
                                }

                                store_sub_category.valueProperty().addListener(new ChangeListener() {
                                    @Override
                                    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                                        total = 0;
                                        for (Stock stock : DashboardController.available_store_stocks) {
                                            pName = stock.getProduct_name().split("/");
                                            if (stock.getProduct_name().equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString() + "/" + store_sub_category.getValue().toString())) {
                                                total = total + stock.getAvailable_quantity();
                                            }
                                        }

                                        st_av_quantity.setText("Available quantity = " + total);

                                        store_category_quantity = String.valueOf(total);
                                    }
                                });

                                st_av_quantity.setText("Available quantity = " + total);
                            }
                        }
                    });

                } else if (category_item_store.toLowerCase().equals("fan")) {
                    store_sub_category.setVisible(false);
                    store_item_category.setLayoutY(250);
                    store_item_category.setItems(fan);
                    store_item_category.setValue(fan.get(0));

                    total = 0;

                    for (Stock stock : available_store_stocks) {
                        pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
                        if ((pName[0] + "/" + pName[1]).equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString())) {
                            total = total + stock.getAvailable_quantity();
                            System.out.println("This is the total: " + total);
                        }
                    }

                    store_item_category.valueProperty().addListener(new ChangeListener() {
                        @Override
                        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                            total = 0;

                            for (Stock stock : available_store_stocks) {
                                pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
                                if ((pName[0] + "/" + pName[1]).equals(label1.getText().toUpperCase() + "/" + store_item_category.getValue().toString())) {
                                    total = total + stock.getAvailable_quantity();
                                    System.out.println("This is the total: " + total);
                                }
                            }

                            st_av_quantity.setText("Available quantity = " + total);

                            store_category_quantity = String.valueOf(total);
                        }
                    });

                    st_av_quantity.setText("Available quantity = " + total);

                } else if (category_item_store.toLowerCase().equals("solar taa")) {
                    store_sub_category.setVisible(false);
                    store_item_category.setLayoutY(250);
                    store_item_category.setItems(solar_taa);
                    store_item_category.setValue(solar_taa.get(0));

                    total = 0;

                    for (Stock stock : available_store_stocks) {
                        pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
//                            System.out.println("This is a new one: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString());
                        if ((pName[0]).equals(label1.getText().toUpperCase())) {
                            total = total + stock.getAvailable_quantity();
                        }
                    }

                    st_av_quantity.setText("Available quantity = " + total);

                    store_category_quantity = String.valueOf(total);

                } else if (category_item_store.toLowerCase().equals("friji")) {
                    store_sub_category.setVisible(false);
                    store_item_category.setLayoutY(250);
                    store_item_category.setItems(friji);
                    store_item_category.setValue(friji.get(0));

                    total = 0;

                    System.out.println("this is the name: " + label1.getText().toUpperCase());
                    System.out.println("this is the total: " + total);

                    for (Stock stock : available_store_stocks) {
                        pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
//                            System.out.println("This is a new one: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString());
                        if ((pName[0]).equals(label1.getText().toUpperCase())) {
                            total = total + stock.getAvailable_quantity();
                            System.out.println("this is the grand total: " + total);
                        }
                    }

                    st_av_quantity.setText("Available quantity = " + total);

                    store_category_quantity = String.valueOf(total);

                }
            }
        });
    }

    private DropShadow addPaneEffect() {

        DropShadow ds = new DropShadow();
        ds.setSpread(0);
        ds.setWidth(12);
        ds.setHeight(12);
        ds.setRadius(3.5);
        ds.setOffsetY(2.0);
        ds.setOffsetX(1.0);
        ds.setColor(Color.web("#000000", 0.5));

        return ds;

    }

    public void labelMouseEntered(Label label) {
//        label.setTextFill(Color.WHITE);
        label.setScaleY(1.1);
        label.setScaleX(1.1);
    }

    public void fillPurchases() {

        DatabaseHandler db = new DatabaseHandler();

        setUpPurchases();

        purchases_table.setItems(null);

        Task<ObservableList<Purchase>> loadDataTask = new Task<ObservableList<Purchase>>() {
            @Override
            protected ObservableList<Purchase> call() throws Exception {
                // load data and populate list ...
                purchases = db.loadPurchases();
                return purchases;
            }
        };

        loadDataTask.setOnSucceeded(e ->
        {
            if (loadDataTask.getValue().size() > 0) {
                purchases_table.setItems(loadDataTask.getValue());
                System.out.println("this is for loading purchases1: " + loadDataTask.getValue().size());
            } else {
                purchases_table.setItems(null);
                System.out.println("this is for loading purchases2: " + loadDataTask.getValue().size());
            }
        });
        loadDataTask.setOnFailed(e -> {/*Handle Errors */});

        ProgressIndicator progressIndicator = new ProgressIndicator();
        progressIndicator.setMaxWidth(50);
        progressIndicator.setMaxHeight(50);
        progressIndicator.setStyle("-fx-progress-color: #558C89;");
        purchases_table.setPlaceholder(progressIndicator);

        Thread loadDataThread = new Thread(loadDataTask);
        loadDataThread.start();


        pur_rows_label.setText(String.valueOf(db.loadPurchases().size()));
    }

    public void setUpPurchases() {
        Callback<TableColumn<Purchase, String>, TableCell<Purchase, String>> cellFactory =
                new Callback<TableColumn<Purchase, String>, TableCell<Purchase, String>>() {
                    @Override
                    public TableCell<Purchase, String> call(TableColumn<Purchase, String> param) {
                        final TableCell<Purchase, String> cell = new TableCell<Purchase, String>() {
                            @Override
                            protected void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    Purchase purchase = getTableView().getItems().get(getIndex());

                                    ImageView viewImage = new ImageView(new Image(getClass().getResource("Images/info.png").toString(), true));
                                    viewImage.setFitHeight(29.5);
                                    viewImage.setFitWidth(29.5);

                                    ImageView deleteImage = new ImageView(new Image(getClass().getResource("Images/delete.png").toString(), true));
                                    deleteImage.setFitHeight(24);
                                    deleteImage.setFitWidth(24);

                                    ImageView editImage = new ImageView(new Image(getClass().getResource("Images/edt.png").toString(), true));
                                    editImage.setFitHeight(24);
                                    editImage.setFitWidth(24);

                                    DropShadow ds = new DropShadow();
                                    ds.setSpread(0);
                                    ds.setWidth(8);
                                    ds.setHeight(8);
                                    ds.setRadius(3.5);
                                    ds.setOffsetY(1.0);
                                    ds.setOffsetX(0.0);
                                    ds.setColor(Color.web("#000000", 0.4));

                                    viewImage.setEffect(ds);
                                    editImage.setEffect(ds);
                                    deleteImage.setEffect(ds);

                                    viewImage.setOnMouseEntered(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent event) {
                                            viewImage.setScaleY(1.3);
                                            viewImage.setScaleX(1.3);
                                        }
                                    });

                                    viewImage.setOnMouseExited(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent event) {
                                            viewImage.setScaleX(1);
                                            viewImage.setScaleY(1);
                                        }
                                    });

                                    deleteImage.setOnMouseEntered(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent event) {
                                            deleteImage.setScaleY(1.3);
                                            deleteImage.setScaleX(1.3);
                                        }
                                    });

                                    deleteImage.setOnMouseExited(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent event) {
                                            deleteImage.setScaleX(1);
                                            deleteImage.setScaleY(1);
                                        }
                                    });

                                    editImage.setOnMouseEntered(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent event) {
                                            editImage.setScaleY(1.3);
                                            editImage.setScaleX(1.3);
                                        }
                                    });

                                    editImage.setOnMouseExited(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent event) {
                                            editImage.setScaleX(1);
                                            editImage.setScaleY(1);
                                        }
                                    });

                                    editImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent event) {
                                            try {
                                                if(LoginController.userObject.getString("role").equals("Main Admin")||LoginController.userObject.getString("role").equals("Admin")){
                                                    product_name = purchase.getName();
                                                    cost = purchase.getAmount();
                                                    quantity = purchase.getQuantity();
                                                    id = purchase.getId();
                                                    date = purchase.getDate();
                                                    editPurchase(purchase.getId());
                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });

                                    deleteImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent event) {
                                            Alert alert = new Alert(Alert.AlertType.WARNING);
                                            alert.setTitle("Warning Dialog");
                                            alert.setHeaderText(null);
                                            alert.setContentText("Are you sure you want to delete the sale?");
                                            Optional<ButtonType> result = alert.showAndWait();
                                            if (result.get() == ButtonType.OK) {
                                                DatabaseHandler db = new DatabaseHandler();
                                                db.deletePurchase(purchase.getId());
                                                fillPurchases();
                                            }
                                        }
                                    });
                                    HBox hBox = new HBox();
                                    hBox.getChildren().addAll(editImage, deleteImage);
                                    hBox.setAlignment(Pos.CENTER_LEFT);
                                    hBox.setSpacing(5);
                                    setGraphic(hBox);
                                    this.setStyle("-fx-font-weight: normal");
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };
        pur_act_col.setCellFactory(cellFactory);

        TableView.TableViewSelectionModel tableViewSelectionModel = purchases_table.getSelectionModel();

        tableViewSelectionModel.setCellSelectionEnabled(true);
    }

    public void editPurchase(int id){
        try {
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/purchase_edit.fxml"));
            stage.setTitle("Bussiness Inventory System");
            Scene editPurchaseScene = new Scene(fxmlLoader.load(),657,563);
            stage.setScene(editPurchaseScene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initializeExpenses() {

        setUpExpenses();

        DatabaseHandler db = new DatabaseHandler();
        expenses_table.setItems(null);

        Task<ObservableList<Expense>> loadDataTask = new Task<ObservableList<Expense>>() {
            @Override
            protected ObservableList<Expense> call() throws Exception {
                Thread.sleep(1000);
                // load data and populate list ...
                expenses = db.loadExpenses();
                return expenses;
            }
        };
        loadDataTask.setOnSucceeded(e -> expenses_table.setItems(loadDataTask.getValue()));
        loadDataTask.setOnFailed(e -> { /* handle errors... */ });

        ProgressIndicator progressIndicator = new ProgressIndicator();
        progressIndicator.setMaxWidth(50);
        progressIndicator.setMaxHeight(50);
        progressIndicator.setStyle("-fx-progress-color: #558C89;");
        expenses_table.setPlaceholder(progressIndicator);

        Thread loadDataThread = new Thread(loadDataTask);
        loadDataThread.start();

        exp_rows_label.setText(String.valueOf(db.loadExpenses().size()));
    }

    public void setUpExpenses() {
        Callback<TableColumn<Expense, String>, TableCell<Expense, String>> cellFactory =
                new Callback<TableColumn<Expense, String>, TableCell<Expense, String>>() {
                    @Override
                    public TableCell<Expense, String> call(TableColumn<Expense, String> param) {
                        final TableCell<Expense, String> cell = new TableCell<Expense, String>() {
                            @Override
                            protected void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {

                                    Expense expense = getTableView().getItems().get(getIndex());

                                    ImageView viewImage = new ImageView(new Image(getClass().getResource("Images/info.png").toString(), true));
                                    viewImage.setFitHeight(29.5);
                                    viewImage.setFitWidth(29.5);

                                    ImageView deleteImage = new ImageView(new Image(getClass().getResource("Images/delete.png").toString(), true));
                                    deleteImage.setFitHeight(24);
                                    deleteImage.setFitWidth(24);

                                    ImageView editImage = new ImageView(new Image(getClass().getResource("Images/edt.png").toString(), true));
                                    editImage.setFitHeight(24);
                                    editImage.setFitWidth(24);

                                    DropShadow ds = new DropShadow();
                                    ds.setSpread(0);
                                    ds.setWidth(8);
                                    ds.setHeight(8);
                                    ds.setRadius(3.5);
                                    ds.setOffsetY(1.0);
                                    ds.setOffsetX(0.0);
                                    ds.setColor(Color.web("#000000", 0.4));

                                    viewImage.setEffect(ds);
                                    editImage.setEffect(ds);
                                    deleteImage.setEffect(ds);

                                    viewImage.setOnMouseEntered(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent event) {
                                            viewImage.setScaleY(1.3);
                                            viewImage.setScaleX(1.3);
                                        }
                                    });

                                    viewImage.setOnMouseExited(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent event) {
                                            viewImage.setScaleX(1);
                                            viewImage.setScaleY(1);
                                        }
                                    });

                                    deleteImage.setOnMouseEntered(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent event) {
                                            deleteImage.setScaleY(1.3);
                                            deleteImage.setScaleX(1.3);
                                        }
                                    });

                                    deleteImage.setOnMouseExited(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent event) {
                                            deleteImage.setScaleX(1);
                                            deleteImage.setScaleY(1);
                                        }
                                    });

                                    editImage.setOnMouseEntered(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent event) {
                                            editImage.setScaleY(1.3);
                                            editImage.setScaleX(1.3);
                                        }
                                    });

                                    editImage.setOnMouseExited(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent event) {
                                            editImage.setScaleX(1);
                                            editImage.setScaleY(1);
                                        }
                                    });

                                    deleteImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent event) {
                                            Alert alert = new Alert(Alert.AlertType.WARNING);
                                            alert.setTitle("Warning Dialog");
                                            alert.setHeaderText(null);
                                            alert.setContentText("Are you sure you want to delete the sale?");
                                            Optional<ButtonType> result = alert.showAndWait();
                                            if (result.get() == ButtonType.OK) {
                                                DatabaseHandler db = new DatabaseHandler();
                                                db.deleteExpense(expense.getId());
                                                initializeExpenses();
                                            }
                                        }
                                    });

                                    editImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent event) {
                                            try {
                                                if(LoginController.userObject.getString("role").equals("Main Admin")||LoginController.userObject.getString("role").equals("Admin")){
                                                    product_name = expense.getName();
                                                    date = expense.getDate();
                                                    cost = expense.getAmount();
                                                    desc = expense.getDescription();
                                                    id = expense.getId();
                                                    editExpense(expense.getId());
                                                }
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });

                                    HBox hBox = new HBox();
                                    hBox.getChildren().addAll(editImage, deleteImage);
                                    hBox.setAlignment(Pos.CENTER_LEFT);
                                    hBox.setSpacing(12);
                                    setGraphic(hBox);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };

        exp_act_col.setCellFactory(cellFactory);

        TableView.TableViewSelectionModel tableViewSelectionModel = expenses_table.getSelectionModel();

        tableViewSelectionModel.setCellSelectionEnabled(false);

    }

    public void editExpense(int id){
        try {
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/expense_edit.fxml"));
            stage.setTitle("Bussiness Inventory System");
            Scene editCustomerScene = new Scene(fxmlLoader.load(),560,558);
            stage.setScene(editCustomerScene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchExpense() {
        if (!expfieldSearch.getText().isEmpty()) {
            ObservableList<Expense> expenses = FXCollections.observableArrayList();
            if (Checker.isStringInt(expfieldSearch.getText().substring(0, 2).toLowerCase())) {
                for (Expense expense : this.expenses) {
                    if (expense.getDate().toLowerCase().contains(expfieldSearch.getText().toLowerCase())) {
                        expenses.add(expense);
                    }
                }
                setUpExpenses();
                expense_table.setItems(null);
                expense_table.setItems(expenses);
            } else {
                for (Expense expense : this.expenses) {
                    if (expense.getName().toLowerCase().contains(expfieldSearch.getText().toLowerCase()) || expense.getDate().toLowerCase().contains(expfieldSearch.getText().toLowerCase())) {
                        expenses.add(expense);
                    }
                }
                setUpExpenses();
                expense_table.setItems(null);
                expense_table.setItems(expenses);
            }
        } else
            initializeExpenses();
    }

    public void searchPurchase() {
        if (!purfieldSearch.getText().isEmpty()) {
            ObservableList<Purchase> purchases = FXCollections.observableArrayList();
            if (Checker.isStringInt(purfieldSearch.getText().substring(0, 2).toLowerCase())) {
                for (Purchase purchase : this.purchases) {
                    if (purchase.getDate().contains(purfieldSearch.getText())) {
                        purchases.add(purchase);
                    }
                }
                setUpPurchases();
                purchases_table.setItems(null);
                purchases_table.setItems(purchases);
            } else {
                for (Purchase purchase : this.purchases) {
                    if (purchase.getName().toLowerCase().contains(purfieldSearch.getText().toLowerCase())) {
                        purchases.add(purchase);
                    }
                }
                setUpPurchases();
                purchases_table.setItems(null);
                purchases_table.setItems(purchases);
            }
        } else {
            fillPurchases();
        }
    }

    public void refreshPurchases() {
        fillPurchases();
    }

    public void searchSale() {
        if (!salesfieldSearch.getText().isEmpty()) {
            ObservableList<Sale> sales = FXCollections.observableArrayList();
            DatabaseHandler db = new DatabaseHandler();
            if (Checker.isStringInt(salesfieldSearch.getText().substring(0, 2))) {
                for (Sale sale : db.loadSales()) {
                    if (sale.getDate().toLowerCase().contains(salesfieldSearch.getText().toLowerCase())) {
                        sales.add(sale);
                    }
                }
                setUpSales();
                sales_table.setItems(null);
                sales_table.setItems(sales);
            } else {
                for (Sale sale : db.loadSales()) {
                    if (sale.getProduct_name().toLowerCase().contains(salesfieldSearch.getText().toLowerCase())) {
                        sales.add(sale);
                    }
                }
                setUpSales();
                sales_table.setItems(null);
                sales_table.setItems(sales);
            }
        } else {
            fillSales();
        }

    }

    public void refreshSales() {
        fillSales();
    }

    public void refreshExpenses() {
        initializeExpenses();
    }

    public void fillSales() {

        DatabaseHandler db = new DatabaseHandler();

        setUpSales();

        sale_table.setItems(null);

        Task<ObservableList<Sale>> loadDataTask = new Task<ObservableList<Sale>>() {
            @Override
            protected ObservableList<Sale> call() throws Exception {
//                Thread.sleep(1000);
                // load data and populate list ...
                return db.loadSales();
            }
        };
        loadDataTask.setOnSucceeded(e ->
        {
            if (loadDataTask.getValue().size() > 0) {
                sale_table.setItems(loadDataTask.getValue());
            } else {
                sale_table.setItems(null);
            }
        });
        loadDataTask.setOnFailed(e -> {/*Handle Errors */});

        ProgressIndicator progressIndicator = new ProgressIndicator();
        progressIndicator.setMaxWidth(50);
        progressIndicator.setMaxHeight(50);
        progressIndicator.setStyle("-fx-progress-color: #558C89;");
        sale_table.setPlaceholder(progressIndicator);

        Thread loadDataThread = new Thread(loadDataTask);
        loadDataThread.start();

        sales_rows_label.setText(String.valueOf(db.loadSales().size()));
    }

    public void setUpSales() {
        Callback<TableColumn<Sale, String>, TableCell<Sale, String>> cellFactory =
                new Callback<TableColumn<Sale, String>, TableCell<Sale, String>>() {
                    @Override
                    public TableCell<Sale, String> call(TableColumn<Sale, String> param) {
                        final TableCell<Sale, String> cell = new TableCell<Sale, String>() {
                            @Override
                            protected void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {

                                    Sale sale = getTableView().getItems().get(getIndex());

                                    ImageView viewImage = new ImageView(new Image(getClass().getResource("Images/info.png").toString(), true));
                                    viewImage.setFitHeight(29.5);
                                    viewImage.setFitWidth(29.5);

                                    ImageView deleteImage = new ImageView(new Image(getClass().getResource("Images/delete.png").toString(), true));
                                    deleteImage.setFitHeight(24);
                                    deleteImage.setFitWidth(24);

                                    ImageView editImage = new ImageView(new Image(getClass().getResource("Images/edt.png").toString(), true));
                                    editImage.setFitHeight(24);
                                    editImage.setFitWidth(24);

                                    DropShadow ds = new DropShadow();
                                    ds.setSpread(0);
                                    ds.setWidth(8);
                                    ds.setHeight(8);
                                    ds.setRadius(3.5);
                                    ds.setOffsetY(1.0);
                                    ds.setOffsetX(0.0);
                                    ds.setColor(Color.web("#000000", 0.4));

                                    viewImage.setEffect(ds);
                                    editImage.setEffect(ds);
                                    deleteImage.setEffect(ds);

                                    viewImage.setOnMouseEntered(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent event) {
                                            viewImage.setScaleY(1.3);
                                            viewImage.setScaleX(1.3);
                                        }
                                    });

                                    viewImage.setOnMouseExited(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent event) {
                                            viewImage.setScaleX(1);
                                            viewImage.setScaleY(1);
                                        }
                                    });

                                    deleteImage.setOnMouseEntered(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent event) {
                                            deleteImage.setScaleY(1.3);
                                            deleteImage.setScaleX(1.3);
                                        }
                                    });

                                    deleteImage.setOnMouseExited(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent event) {
                                            deleteImage.setScaleX(1);
                                            deleteImage.setScaleY(1);
                                        }
                                    });

                                    editImage.setOnMouseEntered(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent event) {
                                            editImage.setScaleY(1.3);
                                            editImage.setScaleX(1.3);
                                        }
                                    });

                                    editImage.setOnMouseExited(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent event) {
                                            editImage.setScaleX(1);
                                            editImage.setScaleY(1);
                                        }
                                    });

                                    deleteImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent event) {
                                            Alert alert = new Alert(Alert.AlertType.WARNING);
                                            alert.setTitle("Warning Dialog");
                                            alert.setHeaderText(null);
                                            alert.setContentText("Are you sure you want to delete the sale?");
                                            Optional<ButtonType> result = alert.showAndWait();
                                            if (result.get() == ButtonType.OK) {
                                                DatabaseHandler db = new DatabaseHandler();
                                                db.deleteSale(Integer.parseInt(sale.getId()));
                                                fillSales();
                                            }
                                        }
                                    });

                                    editImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent event) {
                                            product_name = sale.getProduct_name();
                                            quantity = sale.getQuantity();
                                            cost = sale.getCost();
                                            date = sale.getDate();
                                            id = Integer.parseInt(sale.getId());
                                            editSale(Integer.parseInt(sale.getId()));
                                        }
                                    });

                                    HBox hBox = new HBox();
                                    hBox.getChildren().addAll(editImage, deleteImage);
                                    hBox.setAlignment(Pos.CENTER_LEFT);
                                    hBox.setSpacing(12);
                                    setGraphic(hBox);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };

        System.out.println(cellFactory.toString());

        act_col.setCellFactory(cellFactory);

        TableView.TableViewSelectionModel tableViewSelectionModel = sale_table.getSelectionModel();

        tableViewSelectionModel.setCellSelectionEnabled(false);
    }

}
