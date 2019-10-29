package sample.Controllers;

import animatefx.animation.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
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
import sample.Objects.Sale;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    AnchorPane dash_pane, tv_pane, wire_pane, tv_stand_pane, mashuka_pane, pasi_pane, socket_pane, deki_pane, remote_pane, redio_pane, spika_pane, fan_pane, taa_pane, friji_pane, dash_item_pane;
    @FXML
    Label tv_label, tv_idadi, tv_no, wire_label, wire_idadi, wire_no, tv_stand_label, tv_stand_idadi, tv_stand_no, mashuka_label, mashuka_idadi, mashuka_no, pasi_label, pasi_idadi, pasi_no, socket_label, socket_idadi, socket_no, deki_label, deki_idadi,
            deki_no, remote_label, remote_idadi, remote_no, redio_label, redio_idadi, redio_no, spika_label, spika_idadi, spika_no, fan_label, fan_idadi, fan_no, taa_label, taa_idadi, taa_no, friji_label, friji_idadi, friji_no;
    @FXML
    ImageView tv_image, wire_image, tv_stand_image, mashuka_image, pasi_image, socket_image, deki_image, remote_image, redio_image, spika_image, fan_image, taa_image, friji_image;
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

    ObservableList<Sale> sales;

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

        productName_col.setCellValueFactory(new PropertyValueFactory<>("product_name"));
        quantity_col.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        cost_col.setCellValueFactory(new PropertyValueFactory<>("cost"));
        date_col.setCellValueFactory(new PropertyValueFactory<>("date"));
        action_col.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

        sales_table.setRowFactory(tableValue -> new TableRow<Sale>() {
            @Override
            public void updateItem(Sale item, boolean empty) {
                super.updateItem(item, empty) ;
                if (item == null) {
                    setStyle("");
                } else {
                    setStyle("-fx-border-color: #E0E0E0; -fx-border-width: 0.7 0 0 0;");
                }
            }
        });

        Callback<TableColumn<Sale,String>, TableCell<Sale,String>> cellFactory =
                new Callback<TableColumn<Sale, String>, TableCell<Sale, String>>() {
                    @Override
                    public TableCell<Sale, String> call(TableColumn<Sale, String> param) {
                        final TableCell<Sale,String> cell = new TableCell<Sale,String>(){
                            @Override
                            protected void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if(empty){
                                    setGraphic(null);
                                    setText(null);
                                }else{

                                    ImageView viewImage = new ImageView(new Image(getClass().getResource("../Images/info.png").toString(), true));
                                    viewImage.setFitHeight(24);
                                    viewImage.setFitWidth(24);

                                    ImageView deleteImage = new ImageView(new Image(getClass().getResource("../Images/delete.png").toString(), true));
                                    deleteImage.setFitHeight(24);
                                    deleteImage.setFitWidth(24);

                                    ImageView editImage = new ImageView(new Image(getClass().getResource("../Images/edit.png").toString(), true));
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

//                                    viewImage.resize(30, 24);
//                                    viewImage.setEffect(new DropShadow(BlurType.THREE_PASS_BOX, Color.rgb(0, 0, 0, 0.23), 10, 0, 0, 1));
                                    viewImage.setEffect(ds);

//                                    editImage.resize(30, 24);
//                                    editImage.setEffect(new DropShadow(BlurType.THREE_PASS_BOX, Color.rgb(0, 0, 0, 0.23), 10, 0, 0, 1));
                                    editImage.setEffect(ds);

//                                    deleteImage.resize(30, 24);
//                                    deleteImage.setEffect(new DropShadow(BlurType.THREE_PASS_BOX, Color.rgb(0, 0, 0, 0.23), 10, 0, 0, 1));
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

        sales = FXCollections.observableArrayList(
          new Sale("Television", "2", "1,500,000", "29/10/2019"),
          new Sale("Radio", "4", "500,000", "29/10/2019"),
          new Sale("Wire", "2", "35,000", "29/10/2019"),
          new Sale("Television", "6", "3,235,000", "29/10/2019"),
          new Sale("Socket", "20", "400,000", "29/10/2019"),
          new Sale("Speaker", "2", "300,000", "29/10/2019"),
          new Sale("Fan", "10", "500,000", "29/10/2019"),
          new Sale("Iron", "2", "70,000", "29/10/2019")
        );

        TableView.TableViewSelectionModel tableViewSelectionModel = sales_table.getSelectionModel();

        tableViewSelectionModel.setCellSelectionEnabled(true);
        sales_table.setItems(sales);

    }

    public void mouseEvents(Label label1, Label label2, Label label3, ImageView imageView, AnchorPane pane){

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
                out.setOnFinished(event1 -> {

                });
                out.play();
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
        ds.setColor(Color.web("#000000", 0.7));

        return ds;

    }

    public void labelMouseEntered(Label label){
//        label.setTextFill(Color.WHITE);
        label.setScaleY(1.1);
        label.setScaleX(1.1);
    }
}
