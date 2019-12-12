package sample;

import animatefx.animation.*;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {

    @FXML
    ImageView dash_image, management_image, report_image, exit_image, user_image;
    @FXML
    Label dash_txt, management_txt, report_txt, exit_txt, txt, fullName_txt;
    @FXML
    AnchorPane main_window_anchor;
    @FXML
    StackPane main_pane;
    @FXML
    BorderPane borderPane;
    @FXML
    VBox user_pane;
    @FXML
    JFXButton change_pass_btn, logout_btn;

    JSONObject userCredentials;

    int check = 0;

    int dash_pop_check = 0;

    AnchorPane pane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try{
            fullName_txt.setText(LoginController.userObject.get("fname") + " " + LoginController.userObject.get("lname"));
        }catch (JSONException e){
            e.printStackTrace();
        }

//        borderPane.centerProperty().addListener(
//                (ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
////                    main_window_anchor.setPrefWidth( newValue.doubleValue() );
//                }
//        );

        btn_effects(logout_btn);
        btn_effects(change_pass_btn);

        user_pane.setVisible(false);

        dash_txt.setTextFill(Color.WHITE);
        dash_txt.setScaleY(1.2);
        dash_txt.setScaleX(1.2);
        dash_image.setImage(new Image(getClass().getResource("Images/home_.png").toString(), true));
        dash_image.setScaleX(1.2);
        dash_image.setScaleY(1.2);

        check = 1;

        try {
            if(LoginController.userObject.getString("role").equals("Main Admin")||LoginController.userObject.getString("role").equals("Admin")){
                management_image.setVisible(true);
                management_txt.setVisible(true);
                report_image.setVisible(true);
                report_txt.setVisible(true);
            }else{
                management_image.setVisible(false);
                management_txt.setVisible(false);
                report_txt.setVisible(false);
                report_image.setVisible(false);
            }

            pane = FXMLLoader.load(getClass().getResource("FXML/dashboard.fxml"));
//            pane.setPrefHeight(795);
//            pane.setPrefWidth(1343);
            main_pane.getChildren().setAll(pane);
        } catch (Exception e) {
            e.printStackTrace();
        }

        user_image.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                user_image.setScaleX(1.2);
                user_image.setScaleY(1.2);
//                user_image.setEffect(addPaneEffect());
            }
        });

        user_image.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                user_image.setImage(new Image(getClass().getResource("Images/user.png").toString(), true));
                user_image.setScaleX(1);
                user_image.setScaleY(1);
            }
        });

//        user_image.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                new Bounce(user_image).play();
//            }
//        });

        user_image.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(dash_pop_check == 0){
                    user_pane.setVisible(true);
                    dash_pop_check = 1;
                }else{
                    user_pane.setVisible(false);
                    dash_pop_check = 0;
                }
            }
        });

        DashboardClick();
        ManagementClick();
        ReportClick();
        ExitClick();

        mouseEvents(dash_txt, dash_image, "Images/home_.png", "Images/home.png", 1);
        mouseEvents(management_txt, management_image, "Images/admin_settings_.png", "Images/admin_settings.png", 2);
        mouseEvents(report_txt, report_image, "Images/business_report_.png", "Images/business_report.png", 3);
        mouseEvents(exit_txt, exit_image, "Images/shutdown_.png", "Images/shutdown.png", 4);
    }

    public void btn_effects(JFXButton button){
        button.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                button.setStyle("-fx-background-color: #45c7fb; -fx-text-fill: #ffffff");
            }
        });

        button.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                button.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #000000");
            }
        });
    }

    public void logout(){
        try {
            Stage stage = (Stage) logout_btn.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("FXML/login.fxml"));
            stage.setTitle("Bussiness Inventory System");
            stage.setMaximized(false);
            Scene loginScene = new Scene(root,1392,822);
            stage.setScene(loginScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changePassword(){
        try {
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader fxmlLoader1 = new FXMLLoader(getClass().getResource("FXML/change_password.fxml"));
            stage.setTitle("Bussiness Inventory System");
            Scene changePasswordScene = new Scene(fxmlLoader1.load(),600,426);
            stage.setScene(changePasswordScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mouseClicks(int prev){
        if(prev == 1){
            beforeClick(dash_txt, dash_image, "Images/home.png");
        }else if(prev == 2){
            beforeClick(management_txt, management_image, "Images/admin_settings.png");
        }else if(prev == 3){
            beforeClick(report_txt, report_image, "Images/business_report.png");
        }else if(prev == 4){
            beforeClick(exit_txt, exit_image, "Images/shutdown.png");
        }
    }

    public void beforeClick(Label label, ImageView imageView, String url_before){
        label.setTextFill(Color.web("#c0c0c0"));
        label.setScaleX(1);
        label.setScaleY(1);
        imageView.setImage(new Image(getClass().getResource(url_before).toString(), true));
        imageView.setScaleX(1);
        imageView.setScaleY(1);
    }

    public void afterClick(Label label, ImageView imageView, String url_after){
        label.setTextFill(Color.WHITE);
        label.setScaleY(1.2);
        label.setScaleX(1.2);
        imageView.setImage(new Image(getClass().getResource(url_after).toString(), true));
        imageView.setScaleX(1.2);
        imageView.setScaleY(1.2);
        new Bounce(imageView).play();
    }

    public void mouseEvents(Label label, ImageView imageView, String url_enter, String url_exit, int ch){

        label.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                label.setTextFill(Color.WHITE);
                label.setScaleX(1.2);
                label.setScaleY(1.2);
                imageView.setImage(new Image(getClass().getResource(url_enter).toString(), true));
                imageView.setScaleX(1.2);
                imageView.setScaleY(1.2);
            }
        });

        imageView.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                label.setTextFill(Color.WHITE);
                label.setScaleX(1.2);
                label.setScaleY(1.2);
                imageView.setImage(new Image(getClass().getResource(url_enter).toString(), true));
                imageView.setScaleX(1.2);
                imageView.setScaleY(1.2);
            }
        });

//        if(ch != check){
        label.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if(ch != check){
                    label.setTextFill(Color.web("#c0c0c0"));
                    label.setScaleX(1);
                    label.setScaleY(1);
                    imageView.setImage(new Image(getClass().getResource(url_exit).toString(), true));
                    imageView.setScaleX(1);
                    imageView.setScaleY(1);
                }

            }
        });

        imageView.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(ch != check){
                    label.setTextFill(Color.web("#c0c0c0"));
                    label.setScaleX(1);
                    label.setScaleY(1);
                    imageView.setImage(new Image(getClass().getResource(url_exit).toString(), true));
                    imageView.setScaleX(1);
                    imageView.setScaleY(1);
                }
            }
        });
//        }

    }

    public void DashboardClick(){

        dash_txt.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                txt.setText("Dashboard");
                mouseClicks(check);
                afterClick(dash_txt, dash_image, "Images/home_.png");

                try{
                    if(check != 1){
                        pane = FXMLLoader.load(getClass().getResource("FXML/dashboard.fxml"));
                        pane.setStyle("-fx-background-color: #f3f3f3");
                        main_pane.getChildren().add(pane);
//                        main_pane.setBottomAnchor(pane, 0.0);
//                        main_pane.setTopAnchor(pane, 0.0);
//                        main_pane.setRightAnchor(pane, 0.0);
//                        main_pane.setLeftAnchor(pane, 0.0);
                        new FadeIn(main_pane).play();

                        check = 1;
                    }

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        dash_image.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                txt.setText("Dashboard");
                mouseClicks(check);
                afterClick(dash_txt, dash_image, "Images/home_.png");

                try{
                    if(check != 1){
                        pane = FXMLLoader.load(getClass().getResource("FXML/dashboard.fxml"));
                        pane.setStyle("-fx-background-color: #f3f3f3");
                        main_pane.getChildren().add(pane);
//                        main_pane.setBottomAnchor(pane, 0.0);
//                        main_pane.setTopAnchor(pane, 0.0);
//                        main_pane.setRightAnchor(pane, 0.0);
//                        main_pane.setLeftAnchor(pane, 0.0);
                        new FadeIn(main_pane).play();

                        check = 1;
                    }

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    public void ManagementClick(){

        management_txt.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                txt.setText("Management Panel");
                mouseClicks(check);
                afterClick(management_txt, management_image, "Images/admin_settings_.png");

                try{
                    if(check != 2){
                        Parent root = FXMLLoader.load(getClass().getResource("FXML/management.fxml"));
                        root.setStyle("-fx-background-color: #f3f3f3");
                        main_pane.getChildren().remove(pane);
                        main_pane.getChildren().add(root);
//                        main_pane.setBottomAnchor(root, 0.0);
//                        main_pane.setTopAnchor(root, 0.0);
//                        main_pane.setRightAnchor(root, 0.0);
//                        main_pane.setLeftAnchor(root, 0.0);
                        new FadeIn(main_pane).play();

                        check = 2;

                    }

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        management_image.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                txt.setText("Management Panel");
                mouseClicks(check);
                afterClick(management_txt, management_image, "Images/admin_settings_.png");

                try{
                    if(check != 2){
                        Parent root = FXMLLoader.load(getClass().getResource("FXML/management.fxml"));
                        root.setStyle("-fx-background-color: #f3f3f3");
                        main_pane.getChildren().remove(pane);
                        main_pane.getChildren().add(root);
//                        main_pane.setBottomAnchor(root, 0.0);
//                        main_pane.setTopAnchor(root, 0.0);
//                        main_pane.setRightAnchor(root, 0.0);
//                        main_pane.setLeftAnchor(root, 0.0);
                        new FadeIn(main_pane).play();

                        check = 2;

                    }

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    public void ReportClick(){

        report_txt.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                txt.setText("Report");
                mouseClicks(check);
                afterClick(report_txt, report_image, "Images/business_report_.png");

                try{
                    if(check != 3){
                        Parent root = FXMLLoader.load(getClass().getResource("FXML/report.fxml"));
                        root.setStyle("-fx-background-color: #f3f3f3");
                        main_pane.getChildren().remove(pane);
                        main_pane.getChildren().add(root);
//                        main_pane.setBottomAnchor(root, 0.0);
//                        main_pane.setTopAnchor(root, 0.0);
//                        main_pane.setRightAnchor(root, 0.0);
//                        main_pane.setLeftAnchor(root, 0.0);
                        new FadeIn(main_pane).play();

                        check = 3;

                    }

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        report_image.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                txt.setText("Report");
                mouseClicks(check);
                afterClick(report_txt, report_image, "Images/business_report_.png");

                try{
                    if(check != 3){
                        Parent root = FXMLLoader.load(getClass().getResource("FXML/report.fxml"));
                        root.setStyle("-fx-background-color: #f3f3f3");
                        main_pane.getChildren().remove(pane);
                        main_pane.getChildren().add(root);
//                        main_pane.setBottomAnchor(root, 0.0);
//                        main_pane.setTopAnchor(root, 0.0);
//                        main_pane.setRightAnchor(root, 0.0);
//                        main_pane.setLeftAnchor(root, 0.0);
                        new FadeIn(main_pane).play();

                        check = 3;

                    }

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

    }

    public void ExitClick(){
        exit_txt.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mouseClicks(check);
                check = 4;
                afterClick(exit_txt, exit_image, "Images/shutdown_.png");
                Platform.exit();
            }
        });

        exit_image.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mouseClicks(check);
                check = 4;
                afterClick(exit_txt, exit_image, "Images/shutdown_.png");
                Platform.exit();
            }
        });
    }
}
