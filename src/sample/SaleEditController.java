package sample;

import com.jfoenix.controls.JFXComboBox;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.JSONException;
import org.json.JSONObject;
import sample.Handlers.Checker;
import sample.Handlers.DatabaseHandler;
import sample.Objects.PurchaseEdit;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class SaleEditController implements Initializable {
    @FXML
    public TextField quantity;
    @FXML
    public TextField cost;
    @FXML
    public DatePicker datePicker;
    @FXML
    public JFXComboBox edit_category;

    DatabaseHandler db = new DatabaseHandler();

    String product_string;
    int product_quantity;

    int quantity_nxt;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        edit_category.setItems(db.loadProductNames());
        edit_category.setValue(DashboardController.product_name);

        quantity.setText(DashboardController.quantity);
        cost.setText(DashboardController.cost);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate localDate = LocalDate.parse(DashboardController.date, dateTimeFormatter);
        datePicker.setValue(localDate);

        product_string = edit_category.getValue().toString();
        product_quantity = Integer.parseInt(quantity.getText());

//        edit_category.valueProperty().addListener(new ChangeListener() {
//            @Override
//            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
//                product_quantity = db.getQuantity(edit_category.getValue().toString());
//            }
//        });
    }

    public void editSale() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);

        if (quantity.getText().isEmpty() || cost.getText().isEmpty() || datePicker.getValue().toString().isEmpty()) {
            alert.setContentText("Please fill all fields!");
            alert.showAndWait();
        } else {

            if(Checker.isStringInt(quantity.getText()) && Checker.isStringInt(cost.getText().replaceAll(",",""))){
                LocalDate date = datePicker.getValue();

                String[] amountString = cost.getText().split(",");
                String amounter = "";
                for (int i = 0; i < amountString.length; i++) {
                    amounter = amounter + amountString[i];
                }
                double amont = Double.parseDouble(amounter);

                int extra;
                String state;

                if (product_quantity > Integer.parseInt(quantity.getText())) {
                    extra = product_quantity - Integer.parseInt(quantity.getText());
                    state = "add";
                } else {
                    extra = Integer.parseInt(quantity.getText()) - product_quantity;
                    state = "sub";
                }

                DashboardController dc = new DashboardController();

                DatabaseHandler db = new DatabaseHandler();
                if (db.editSale(DashboardController.id, edit_category.getValue().toString(), Integer.parseInt(quantity.getText()), amont, date, state, extra)) {
                    DashboardController.sales_today.setText(db.getTodaySales() + " Tshs");
                    String[] value_category = edit_category.getValue().toString().split("/");
                    if (product_quantity > Integer.parseInt(quantity.getText())) {
                        dc.refreshItemStockPane(value_category[0], extra);
                    } else {
                        dc.refreshItemPane(value_category[0], extra);
                    }
                    alert.setContentText("Successful Edited!");
                } else {
                    alert.setContentText("UnSuccessful try Again!");
                }
                alert.showAndWait();
                DashboardController dashboardController = new DashboardController();
                dashboardController.fillSales();
                Stage stage = (Stage) quantity.getScene().getWindow();
                stage.close();
            }else{
                alert.setContentText("Please check the quantity and amount fields!");
                alert.showAndWait();
            }

        }
    }
}
