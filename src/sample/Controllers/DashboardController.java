package sample.Controllers;

import animatefx.animation.*;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.util.Callback;
import org.json.JSONException;
import org.json.JSONObject;
import sample.Handlers.DatabaseHandler;
import sample.Objects.Expense;
import sample.Objects.Sale;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    AnchorPane sales_anchorpane, dash_item_pane, item_dash_holder;
    @FXML
    AnchorPane  dash_pane, tv_pane, wire_pane, tv_stand_pane, mashuka_pane, pasi_pane, socket_pane, deki_pane, remote_pane, redio_pane, spika_pane, fan_pane, taa_pane, friji_pane;
    @FXML
    AnchorPane  st_dash_pane, st_tv_pane, st_wire_pane, st_tv_stand_pane, st_mashuka_pane, st_pasi_pane, st_socket_pane, st_deki_pane, st_remote_pane, st_redio_pane, st_spika_pane, st_fan_pane, st_taa_pane, st_friji_pane;
    @FXML
    Label tv_label, tv_idadi, tv_no, wire_label, wire_idadi, wire_no, tv_stand_label, tv_stand_idadi, tv_stand_no, mashuka_label, mashuka_idadi, mashuka_no, pasi_label, pasi_idadi, pasi_no, socket_label, socket_idadi, socket_no, deki_label, deki_idadi,
            deki_no, remote_label, remote_idadi, remote_no, redio_label, redio_idadi, redio_no, spika_label, spika_idadi, spika_no, fan_label, fan_idadi, fan_no, taa_label, taa_idadi, taa_no, friji_label, friji_idadi, friji_no;
    @FXML
    Label st_tv_label, st_tv_idadi, st_tv_no, st_wire_label, st_wire_idadi, st_wire_no, st_tv_stand_label, st_tv_stand_idadi, st_tv_stand_no,st_mashuka_label, st_mashuka_idadi, st_mashuka_no, st_pasi_label, st_pasi_idadi, st_pasi_no, st_socket_label, st_socket_idadi, st_socket_no, st_deki_label, st_deki_idadi,
            st_deki_no, st_remote_label, st_remote_idadi, st_remote_no, st_redio_label, st_redio_idadi, st_redio_no, st_spika_label, st_spika_idadi, st_spika_no, st_fan_label, st_fan_idadi, st_fan_no, st_taa_label, st_taa_idadi, st_taa_no, st_friji_label, st_friji_idadi, st_friji_no;
    @FXML
    ImageView tv_image, wire_image, tv_stand_image, mashuka_image, pasi_image, socket_image, deki_image, remote_image, redio_image, spika_image, fan_image, taa_image, friji_image;
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
    Button exp_submit;
    @FXML
    JFXTextField exp_name, exp_cost;
    @FXML
    JFXTextArea exp_desc;
    @FXML
    JFXDatePicker exp_date;
    @FXML
    Label exp_alert_txt;
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

    public static AnchorPane add_sale_pane, p_sales_anchorpane, p_dash_item_pane, p_item_dash_holder;

    public static String category_sale;

    ObservableList<Sale> sales;

    int user_id;

    DatabaseHandler db = new DatabaseHandler();

    ObservableList<Expense> expenses = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        mouseEvents(tv_label, tv_idadi, tv_no, tv_image, tv_pane);
        mouseEvents(wire_label, wire_idadi, wire_no, wire_image, wire_pane);
        mouseEvents(tv_stand_label, tv_stand_idadi, tv_stand_no, tv_stand_image, tv_stand_pane);
        mouseEvents(mashuka_label, mashuka_idadi, mashuka_no, mashuka_image, mashuka_pane);
        mouseEvents(pasi_label, pasi_idadi, pasi_no, pasi_image, pasi_pane);
        mouseEvents(socket_label, socket_idadi, socket_no, socket_image, socket_pane);
        mouseEvents(deki_label, deki_idadi, deki_no, deki_image, deki_pane);
        mouseEvents(remote_label, remote_idadi, remote_no, remote_image, remote_pane);
        mouseEvents(redio_label, redio_idadi, redio_no, redio_image, redio_pane);
        mouseEvents(spika_label, spika_idadi, spika_no, spika_image, spika_pane);
        mouseEvents(fan_label, fan_idadi, fan_no, fan_image, fan_pane);
        mouseEvents(taa_label, taa_idadi, taa_no, taa_image, taa_pane);
        mouseEvents(friji_label, friji_idadi, friji_no, friji_image, friji_pane);

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

        initializeExpenses();

        exp_alert_txt.setText("");

//        exp_date.getEditor().setDisable(true);

        productName_col.setCellValueFactory(new PropertyValueFactory<>("product_name"));
        quantity_col.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        cost_col.setCellValueFactory(new PropertyValueFactory<>("cost"));
        date_col.setCellValueFactory(new PropertyValueFactory<>("date"));
        action_col.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

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

        fillSales();
//        sales_table.setItems(sales);

    }

    public void expSubClick(){
        if (exp_name.getText().isEmpty() && exp_cost.getText().isEmpty() && exp_desc.getText().isEmpty()) {
            exp_alert_txt.setText("Please fill all fields!");
        } else {
            user_id = 3;
//                LocalDate date = exp_date.getValue();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Expense Status");
            alert.setHeaderText(null);
            if(db.addExpense(exp_name.getText(), Double.parseDouble(exp_cost.getText().replaceAll(",", "")), exp_desc.getText(), exp_date.getValue(), user_id)){
                exp_name.setText("");
                exp_cost.setText("");
                exp_desc.setText("");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String date = sdf.format(new Date());
                exp_date.setValue(LocalDate.parse(date));
                exp_alert_txt.setText("");
                alert.setContentText("successfully added!");
                alert.showAndWait();
            }else{
                alert.setContentText("Unsuccessfully added!");
                alert.showAndWait();
            }

        }
    }

    public void mouseEvents(Label label1, Label label2, Label label3, ImageView imageView, AnchorPane pane) {

        Effect effect = tv_pane.getEffect();

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
                BounceOut out = new BounceOut(dash_item_pane);
                try {

                    p_sales_anchorpane = sales_anchorpane;
                    p_dash_item_pane = dash_item_pane;
                    p_item_dash_holder = item_dash_holder;

                    category_sale = label1.getText();
                    add_sale_pane = FXMLLoader.load(getClass().getResource("../FXML/add_sales.fxml"));
                    BounceIn in = new BounceIn(add_sale_pane);
                    out.setOnFinished(event1 -> {
                        sales_anchorpane.setTopAnchor(item_dash_holder, 75.0);
                        item_dash_holder.getChildren().setAll(add_sale_pane);
                        item_dash_holder.setBottomAnchor(add_sale_pane, 0.0);
                        item_dash_holder.setTopAnchor(add_sale_pane, 0.0);
                        item_dash_holder.setRightAnchor(add_sale_pane, 0.0);
                        item_dash_holder.setLeftAnchor(add_sale_pane, 0.0);
                        in.play();
                    });
                    out.play();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void mouseEventsStore(Label label1, Label label2, Label label3, ImageView imageView, AnchorPane pane) {

        Effect effect = tv_pane.getEffect();

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

    public void initializeExpenses(){
        exp_name_col.setCellValueFactory(new PropertyValueFactory<>("name"));
        exp_cost_col.setCellValueFactory(new PropertyValueFactory<>("amount"));
        exp_desc_col.setCellValueFactory(new PropertyValueFactory<>("description"));
        exp_date_col.setCellValueFactory(new PropertyValueFactory<>("date"));
        exp_action_col.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

        setUpExpenses();

        DatabaseHandler db = new DatabaseHandler();
        expense_table.setItems(null);

        Task<ObservableList<Expense>> loadDataTask = new Task<ObservableList<Expense>>() {
            @Override
            protected ObservableList<Expense> call() throws Exception {
                Thread.sleep(1000);
                // load data and populate list ...
                expenses = db.loadExpenses();
                return expenses ;
            }
        };
        loadDataTask.setOnSucceeded(e -> expense_table.setItems(loadDataTask.getValue()));
        loadDataTask.setOnFailed(e -> { /* handle errors... */ });

        ProgressIndicator progressIndicator = new ProgressIndicator();
        progressIndicator.setMaxWidth(50);
        progressIndicator.setMaxHeight(50);
        progressIndicator.setStyle("-fx-progress-color: #558C89;");
        expense_table.setPlaceholder(progressIndicator);

        Thread loadDataThread = new Thread(loadDataTask);
        loadDataThread.start();
    }

    public void setUpExpenses(){
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

                                    ImageView viewImage = new ImageView(new Image(getClass().getResource("../Images/info.png").toString(), true));
                                    viewImage.setFitHeight(29.5);
                                    viewImage.setFitWidth(29.5);

                                    ImageView deleteImage = new ImageView(new Image(getClass().getResource("../Images/delete.png").toString(), true));
                                    deleteImage.setFitHeight(24);
                                    deleteImage.setFitWidth(24);

                                    ImageView editImage = new ImageView(new Image(getClass().getResource("../Images/edt.png").toString(), true));
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

                                    HBox hBox = new HBox();
                                    hBox.getChildren().addAll(editImage, deleteImage, viewImage);
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

        exp_action_col.setCellFactory(cellFactory);

        TableView.TableViewSelectionModel tableViewSelectionModel = expense_table.getSelectionModel();

        tableViewSelectionModel.setCellSelectionEnabled(true);

    }

    public void fillSales(){

        DatabaseHandler db = new DatabaseHandler();

        setUpSales();

        sales_table.setItems(null);

        Task<ObservableList<Sale>> loadDataTask = new Task<ObservableList<Sale>>() {
            @Override
            protected ObservableList<Sale> call() throws Exception {
//                Thread.sleep(1000);
                // load data and populate list ...
                return db.loadSales() ;
            }
        };
        loadDataTask.setOnSucceeded(e ->
        {if(loadDataTask.getValue().size()>0){
            sales_table.setItems(loadDataTask.getValue());
        }else {
            sales_table.setItems(null);
        }});
        loadDataTask.setOnFailed(e -> {/*Handle Errors */});

        ProgressIndicator progressIndicator = new ProgressIndicator();
        progressIndicator.setMaxWidth(50);
        progressIndicator.setMaxHeight(50);
        progressIndicator.setStyle("-fx-progress-color: #558C89;");
        sales_table.setPlaceholder(progressIndicator);

        Thread loadDataThread = new Thread(loadDataTask);
        loadDataThread.start();
    }

    public void setUpSales(){
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

                                    ImageView viewImage = new ImageView(new Image(getClass().getResource("../Images/info.png").toString(), true));
                                    viewImage.setFitHeight(29.5);
                                    viewImage.setFitWidth(29.5);

                                    ImageView deleteImage = new ImageView(new Image(getClass().getResource("../Images/delete.png").toString(), true));
                                    deleteImage.setFitHeight(24);
                                    deleteImage.setFitWidth(24);

                                    ImageView editImage = new ImageView(new Image(getClass().getResource("../Images/edt.png").toString(), true));
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

                                    HBox hBox = new HBox();
                                    hBox.getChildren().addAll(editImage, deleteImage, viewImage);
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

        action_col.setCellFactory(cellFactory);

        TableView.TableViewSelectionModel tableViewSelectionModel = sales_table.getSelectionModel();

        tableViewSelectionModel.setCellSelectionEnabled(false);
    }

}
