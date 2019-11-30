package sample.Controllers;

import animatefx.animation.BounceIn;
import animatefx.animation.BounceOut;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import org.json.JSONException;
import sample.Handlers.DatabaseHandler;
import sample.Objects.Sale;

import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddSales implements Initializable {

    @FXML
    JFXComboBox sale_category, sale_sub_category;
    @FXML
    JFXTextField sale_amount, sale_quantity;
    @FXML
    JFXDatePicker sale_date;
    @FXML
    ImageView back_arrow;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        back_arrow.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                BounceOut out = new BounceOut(DashboardController.add_sale_pane);
                try {

                    AnchorPane category_pane = DashboardController.p_dash_item_pane;
                    BounceIn in = new BounceIn(category_pane);
                    out.setOnFinished(event1 -> {
                        DashboardController.p_sales_anchorpane.setTopAnchor(DashboardController.p_item_dash_holder, 40.0);
                        DashboardController.p_item_dash_holder.getChildren().setAll(category_pane);
                        DashboardController.p_item_dash_holder.setBottomAnchor(category_pane, 0.0);
                        DashboardController.p_item_dash_holder.setTopAnchor(category_pane, 0.0);
                        DashboardController.p_item_dash_holder.setRightAnchor(category_pane, 0.0);
                        DashboardController.p_item_dash_holder.setLeftAnchor(category_pane, 0.0);
                        in.play();
                    });
                    out.play();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        if (DashboardController.category_sale.toLowerCase().equals("tv")) {
            sale_category.setItems(tv);
            sale_category.setValue(tv.get(0));

            sale_category.valueProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    if (sale_category.getValue().toString().equals("HOMEBASE CHOGO")) {
                        sale_sub_category.setItems(tv_homebase_chogo);
                    } else if (sale_category.getValue().toString().equals("SING SAN")) {
                        sale_sub_category.setItems(tv_singsang);
                    } else if (sale_category.getValue().toString().equals("HOMEBASE FLAT")) {
                        sale_sub_category.setItems(tv_homebase_flat);
                    } else if (sale_category.getValue().toString().equals("MR UK CHOGO")) {
                        sale_sub_category.setItems(tv_mr_uk_chogo);
                    } else if (sale_category.getValue().toString().equals("MR UK FLAT")) {
                        sale_sub_category.setItems(tv_mr_uk_flat);

                    } else if (sale_category.getValue().toString().equals("STAR X")) {
                        sale_sub_category.setItems(tv_star_x);

                    } else if (sale_category.getValue().toString().equals("RISING")) {
                        sale_sub_category.setItems(tv_rising);

                    } else if (sale_category.getValue().toString().equals("OCNG")) {
                        sale_sub_category.setItems(tv_ocng);

                    } else if (sale_category.getValue().toString().equals("RITECH")) {
                        sale_sub_category.setItems(tv_ritech);

                    } else if (sale_category.getValue().toString().equals("SUNDAR")) {
                        sale_sub_category.setItems(tv_sundar);

                    } else if (sale_category.getValue().toString().equals("EVOL")) {
                        sale_sub_category.setItems(tv_evol);

                    }
                }
            });
        } else if (DashboardController.category_sale.toLowerCase().equals("wire")) {
            sale_sub_category.setVisible(false);
            sale_category.setLayoutY(164);
            sale_category.setItems(wire);
            sale_category.setValue(wire.get(0));
        } else if (DashboardController.category_sale.toLowerCase().equals("tv-stand")) {
            sale_sub_category.setVisible(false);
            sale_category.setLayoutY(164);
            sale_category.setItems(tv_stand);
            sale_category.setValue(tv_stand.get(0));
        } else if (DashboardController.category_sale.toLowerCase().equals("mashuka")) {
            sale_sub_category.setVisible(false);
            sale_category.setLayoutY(164);
            sale_category.setItems(mashuka);
            sale_category.setValue(mashuka.get(0));
        } else if (DashboardController.category_sale.toLowerCase().equals("pasi")) {
            sale_sub_category.setVisible(false);
            sale_category.setLayoutY(164);
            sale_category.setItems(pasi);
            sale_category.setValue(pasi.get(0));
        } else if (DashboardController.category_sale.toLowerCase().equals("socket")) {
            sale_category.setItems(socket);
            sale_category.setValue(socket.get(0));

            sale_category.valueProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    if (sale_category.getValue().toString().equals("TRONIC")) {
                        sale_sub_category.setItems(socket_tronic);
                    } else if (sale_category.getValue().toString().equals("SUNDAR")) {
                        sale_sub_category.setItems(socket_sundar);
                    }
                }
            });

        } else if (DashboardController.category_sale.toLowerCase().equals("deki")) {
            sale_category.setItems(deki);
            sale_category.setValue(deki.get(0));

            sale_category.valueProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    if (sale_category.getValue().toString().equals("UK")) {
                        sale_sub_category.setItems(deki_uk);
                    } else if (sale_category.getValue().toString().equals("SUNDAR")) {
                        sale_sub_category.setItems(deki_sundar);
                    } else if (sale_category.getValue().toString().equals("REDI AT 7350")) {
                        sale_sub_category.setVisible(false);
                        sale_category.setLayoutY(164);
                    }
                }
            });

        } else if (DashboardController.category_sale.toLowerCase().equals("remote")) {
            sale_sub_category.setVisible(false);
            sale_category.setLayoutY(164);
            sale_category.setItems(remote);
            sale_category.setValue(remote.get(0));

        } else if (DashboardController.category_sale.toLowerCase().equals("redio")) {
            sale_category.setItems(redio);
            sale_category.setValue(redio.get(0));

            sale_category.valueProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    if (sale_category.getValue().toString().equals("SABUFA SP")) {
                        sale_sub_category.setItems(redio_sabufa_sp);
                    } else if (sale_category.getValue().toString().equals("SABUFA ALING")) {
                        sale_sub_category.setItems(redio_sabufa_aling);
                    } else if (sale_category.getValue().toString().equals("SABUFA ABODA")) {
                        sale_sub_category.setItems(redio_sabufa_aboda);
                    } else if (sale_category.getValue().toString().equals("SABUFA MR UK")) {
                        sale_sub_category.setItems(redio_sabufa_mr_uk);
                    } else if (sale_category.getValue().toString().equals("SABUFA SUNDAR")) {
                        sale_sub_category.setItems(redio_sabufa_sundar);
                    }
                }
            });

        } else if (DashboardController.category_sale.toLowerCase().equals("spika")) {
            sale_category.setItems(spika);
            sale_category.setValue(spika.get(0));

            sale_category.valueProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    if (sale_category.getValue().toString().equals("SPIKA YA GARI")) {
                        sale_sub_category.setItems(spika_spika_ya_gari);
                    } else if (sale_category.getValue().toString().equals("SPIKA KUBWA")) {
                        sale_sub_category.setItems(spika_spika_kubwa);
                    }
                }
            });

        } else if (DashboardController.category_sale.toLowerCase().equals("fan")) {
            sale_sub_category.setVisible(false);
            sale_category.setLayoutY(164);
            sale_category.setItems(fan);
            sale_category.setValue(fan.get(0));

        } else if (DashboardController.category_sale.toLowerCase().equals("solar taa")) {
            sale_sub_category.setVisible(false);
            sale_category.setLayoutY(164);
            sale_category.setItems(solar_taa);
            sale_category.setValue(solar_taa.get(0));

        } else if (DashboardController.category_sale.toLowerCase().equals("friji")) {
            sale_sub_category.setVisible(false);
            sale_category.setLayoutY(164);
            sale_category.setItems(friji);
            sale_category.setValue(friji.get(0));

        }
    }

//    public void addSale() {
//
//        int quantity_txt = Integer.parseInt(sale_quantity.getText());
//        LocalDate date = sale_date.getValue();
//
//        if (sale_sub_category.equals("select sub category") || sale_quantity.getText().isEmpty() || date == null) {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("Empty Field");
//            alert.setHeaderText(null);
//            alert.setContentText("Please fill all fields!");
//            alert.showAndWait();
//        } else {
//            DatabaseHandler db = new DatabaseHandler();
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Sale Add");
//            alert.setHeaderText(null);
//
//            if (quantity_txt > Integer.parseInt(item_count.getText())) {
//                alert.setContentText("No enough " + sale_category.getValue() + "/" + sale_sub_category.getValue() + " in store!");
//                alert.showAndWait();
//            } else {
//                double cost = 0;
//                int product_id = 0;
//                Double price = 0.0;
//                for (Products products : db.getPrices()) {
//                    if (products.getName().equals(product)) {
//                        price = products.getPrice();
//                        product_id = products.getId();
//                    }
//                }
//
//                cost = quantity_txt * price;
//
//                boolean result = db.addSale(product_id, quantity_txt, cost, date);
//                if (result) {
//                    sale_sub_category.setValue(null);
//                    sale_quantity.setText("");
//                    sale_date.setValue(null);
//                    fillSales(home_choicebox.getValue());
//                    alert.setContentText("Sale successful added!");
//                    alert.showAndWait();
//                } else {
//                    alert.setContentText("Unsuccessful added!");
//                    alert.showAndWait();
//                }
//
//
//            }
//        }
//    }
//
//    public void fillSales(String store_name){
//        int number = 0;
//        DatabaseHandler db = new DatabaseHandler();
//
//        for(NewProducts newProducts: db.getStocks()){
//            if(newProducts.getName().equals(product_choicebox.getValue())){
//                number = newProducts.getAvailable_quantity();
//            }
//        }
//
//        item_count.setText(String.valueOf(number));
//
//        setUpSales();
//
//        sales_table.setItems(null);
//
//        Task<ObservableList<Sale>> loadDataTask = new Task<ObservableList<Sale>>() {
//            @Override
//            protected ObservableList<Sale> call() throws Exception {
////                Thread.sleep(1000);
//                // load data and populate list ...
//                return db.loadSales(store_name) ;
//            }
//        };
//        loadDataTask.setOnSucceeded(e ->
//        {if(loadDataTask.getValue().size()>0){
//            sales_table.setItems(loadDataTask.getValue());
//        }else {
//            sales_table.setItems(null);
//        }});
//        loadDataTask.setOnFailed(e -> {/*Handle Errors */});
//
//        ProgressIndicator progressIndicator = new ProgressIndicator();
//        progressIndicator.setMaxWidth(50);
//        progressIndicator.setMaxHeight(50);
//        progressIndicator.setStyle("-fx-progress-color: #558C89;");
//        sales_table.setPlaceholder(progressIndicator);
//
//        Thread loadDataThread = new Thread(loadDataTask);
//        loadDataThread.start();
//    }
//
//    public void setUpSales(){
//        Callback<TableColumn<Sale, String>, TableCell<Sale, String>> cellFactory =
//                new Callback<TableColumn<Sale, String>, TableCell<Sale, String>>() {
//                    @Override
//                    public TableCell<Sale, String> call(TableColumn<Sale, String> param) {
//                        final TableCell<Sale, String> cell = new TableCell<Sale, String>() {
//                            @Override
//                            protected void updateItem(String item, boolean empty) {
//                                super.updateItem(item, empty);
//                                if (empty) {
//                                    setGraphic(null);
//                                    setText(null);
//                                } else {
//                                    final Button delBtn = new Button("Delete");
//                                    final Button editBtn = new Button("Edit");
//                                    Sale sale = getTableView().getItems().get(getIndex());
//                                    delBtn.setOnAction(new EventHandler<ActionEvent>() {
//                                        @Override
//                                        public void handle(ActionEvent event) {
//                                            try {
//                                                if(userObject.getString("role").equals("Main Admin")||userObject.getString("role").equals("Admin")){
//                                                    Alert alert = new Alert(Alert.AlertType.WARNING);
//                                                    alert.setTitle("Warning Dialog");
//                                                    alert.setHeaderText(null);
//                                                    alert.setContentText("Are you sure you want to delete the sale?");
//                                                    Optional<ButtonType> result =alert.showAndWait();
//                                                    if(result.get() == ButtonType.OK) {
//                                                        DatabaseHandler db = new DatabaseHandler();
//                                                        db.deleteSale(sale.getId());
//                                                        fillSales(home_choicebox.getValue());
//                                                    }
//                                                }
//                                            } catch (JSONException e) {
//                                                e.printStackTrace();
//                                            }
//                                        }
//                                    });
//                                    editBtn.setOnAction(new EventHandler<ActionEvent>() {
//                                        @Override
//                                        public void handle(ActionEvent event) {
//                                            try {
//                                                if(userObject.getString("role").equals("Main Admin")||userObject.getString("role").equals("Admin")){
//                                                    editSale(sale.getId());
//                                                }
//                                            } catch (JSONException e) {
//                                                e.printStackTrace();
//                                            }
//                                        }
//                                    });
//                                    HBox hBox = new HBox();
//                                    hBox.getChildren().addAll(delBtn, editBtn);
//                                    hBox.setAlignment(Pos.CENTER);
//                                    hBox.setSpacing(5);
//                                    setGraphic(hBox);
//                                    this.setStyle("-fx-font-weight: normal");
//                                    setText(null);
//                                }
//                            }
//                        };
//                        return cell;
//                    }
//                };
//        sales_action.setCellFactory(cellFactory);
//
//    }
}
