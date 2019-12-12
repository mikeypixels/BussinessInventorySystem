package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.json.JSONException;
import org.json.JSONObject;
import sample.Handlers.Checker;
import sample.Handlers.DatabaseHandler;
import sample.Objects.LoginResult;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    TextField uname_field, pass_field;
    @FXML
    Button login_btn;
    @FXML
    Label action_txt;

    DatabaseHandler db;
    public static JSONObject userObject;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        action_txt.setVisible(false);
        uname_field.getStyleClass().add("loginTextField");
        pass_field.getStyleClass().add("loginTextField");
    }

    public void onClick(){
        String username = uname_field.getText();
        String password = pass_field.getText();

        if(username.equals("")||password.equals("")){
            action_txt.setText("Please fill all the fields");
            action_txt.setVisible(true);
        }else {
            if(Checker.isStringInt(username)){
                action_txt.setText("Please check your username");
                action_txt.setVisible(true);
                pass_field.setText("");
            }else {
                Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), ev -> {

                    db = new DatabaseHandler();
                    LoginResult result = db.login(username,password);
                    if (result.success){
                        try{
                            userObject = result.userCredentials;
                            if(userObject.get("status").equals("active")){
//                                GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
//                                int width = gd.getDisplayMode().getWidth();
//                                int height = gd.getDisplayMode().getHeight();
                                System.out.println(userObject.get("status").toString());
                                System.out.println(userObject.get("fname").toString());
//                                action_txt.setText("It reaches here before");
                                Stage stage = (Stage) login_btn.getScene().getWindow();
                                action_txt.setText("It reaches here before before");
                                action_txt.setVisible(true);
                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXML/main_window.fxml"));
                                action_txt.setText("It reaches here");
                                action_txt.setVisible(true);
                                stage.setTitle("Business Inventory System");
                                stage.setScene(new Scene(fxmlLoader.load(), 1600, 900));
                                action_txt.setText("It reaches here after");
                                action_txt.setVisible(true);
                                stage.centerOnScreen();
                                stage.setMaximized(true);
                                stage.show();
                                stage.setResizable(true);
                            }else{
                                action_txt.setText("You have no access!");
                                action_txt.setVisible(true);
                                pass_field.setText("");
                            }

                        }catch (IOException e){
                            e.printStackTrace();
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Warning Dialog");
                            alert.setHeaderText(null);
                            alert.setContentText(e.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Warning Dialog");
                            alert.setHeaderText(null);
                            alert.setContentText(e.toString());
                        }
                    }else {
                        action_txt.setText("Please check your login credentials");
                        action_txt.setVisible(true);
                        pass_field.setText("");
                    }
                }));
                timeline.play();
            }
        }
    }
}
