package sample.Controllers;

import animatefx.animation.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

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
