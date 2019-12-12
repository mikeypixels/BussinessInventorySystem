package sample;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.sun.corba.se.spi.protocol.RequestDispatcherRegistry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import org.controlsfx.control.textfield.CustomTextField;
import org.json.JSONException;
import org.json.JSONObject;
import sample.Handlers.Checker;
import sample.Handlers.DatabaseHandler;
import sample.Objects.User;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class ManagementController implements Initializable {
    @FXML
    JFXTextField fname_txt, lname_txt, uname_txt;
    @FXML
    JFXRadioButton male_btn, female_btn;
    @FXML
    JFXComboBox role_box;
    @FXML
    Label user_rows_txt;

    @FXML
    TableColumn<User, String> name_col;
    @FXML
    TableColumn<User, String> role_col;
    @FXML
    TableColumn<User, String> status_col;
    @FXML
    TableColumn<User, String> action_col;
    @FXML
    TableColumn<User, String> uid_col;
    @FXML
    TableView<User> users_table;
    @FXML
    CustomTextField usersfieldSearch;

    ObservableList<User> users;

    DatabaseHandler db = new DatabaseHandler();

    ToggleGroup user_genderChoice = new ToggleGroup();

    JSONObject userObject;

//    ImageView viewImage;

    String user_status;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        male_btn.setToggleGroup(user_genderChoice);
        female_btn.setToggleGroup(user_genderChoice);

        role_box.getItems().addAll("Admin", "Worker");

//        name_col.setCellValueFactory(new PropertyValueFactory<>("name"));
//        uid_col.setCellValueFactory(new PropertyValueFactory<>("id"));
//        role_col.setCellValueFactory(new PropertyValueFactory<>("role"));
//        action_col.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
//        status_col.setCellValueFactory(new PropertyValueFactory<>("status"));

        users_table.setRowFactory(tableValue -> new TableRow<User>() {
            @Override
            public void updateItem(User item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null) {
                    setStyle("");
                } else {
                    setStyle("-fx-border-color: #E0E0E0; -fx-border-width: 0.7 0 0 0;");
                }
            }
        });

        user_rows_txt.setText("");

        initializeViewUsers();

    }

    public void searchUser() {
        if (!usersfieldSearch.getText().isEmpty()) {
            ObservableList<User> users = FXCollections.observableArrayList();

            try{
                for (User user : db.getUsers(LoginController.userObject.getString("role"))) {
                    if (user.getName().toLowerCase().contains(usersfieldSearch.getText())) {
                        users.add(user);
                    }
                }
                setUpUserTable();
                users_table.setItems(null);
                users_table.setItems(users);
            }catch(Exception e){
                e.printStackTrace();
            }

        }else
            initializeViewUsers();
    }

    public void refreshUsers(){
        initializeViewUsers();
    }

    public void setUpUserTable() {
        Callback<TableColumn<User, String>, TableCell<User, String>> cellFactory =
                new Callback<TableColumn<User, String>, TableCell<User, String>>() {
                    @Override
                    public TableCell<User, String> call(TableColumn<User, String> param) {
                        final TableCell<User, String> cell = new TableCell<User, String>() {

//                            final Button statusBtn = new Button("Block");

                            @Override
                            protected void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {

                                    final ImageView statusImage;
                                    User user = getTableView().getItems().get(getIndex());
                                    if (user.getStatus().equals("active")) {
                                        statusImage = new ImageView(new Image(getClass().getResource("Images/denieduser.png").toString(), true));
                                        user_status = "Block";
                                    } else {
                                        statusImage = new ImageView(new Image(getClass().getResource("Images/accepteduser.png").toString(), true));
                                        user_status = "Activate";
                                    }
//                                    final ImageView statusImage = viewImage1;

                                    statusImage.setFitHeight(29.5);
                                    statusImage.setFitWidth(29.5);

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

                                    statusImage.setEffect(ds);
                                    editImage.setEffect(ds);
                                    deleteImage.setEffect(ds);

                                    statusImage.setOnMouseEntered(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent event) {
                                            statusImage.setScaleY(1.3);
                                            statusImage.setScaleX(1.3);
                                        }
                                    });

                                    statusImage.setOnMouseExited(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent event) {
                                            statusImage.setScaleX(1);
                                            statusImage.setScaleY(1);
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

                                    if (user.getUser_no().equals("USER/1")) {
                                        deleteImage.setVisible(false);
                                        statusImage.setVisible(false);
                                        editImage.setVisible(false);
                                    }

                                    statusImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent event) {
                                            if(user.getStatus().toLowerCase().equals("active")){
                                                user_status = "block";
                                            }else{
                                                user_status = "activate";
                                            }
                                            User user = getTableView().getItems().get(getIndex());
                                            DatabaseHandler db = new DatabaseHandler();
                                            db.updateUserStatus(user_status.toLowerCase(), Integer.parseInt(user.getUser_no().substring(5)));
                                            System.out.println(user.getUser_no().substring(5));
                                            initializeViewUsers();
                                        }
                                    });

                                    editImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent event) {

                                        }
                                    });

                                    deleteImage.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                        @Override
                                        public void handle(MouseEvent event) {
                                            Alert alert = new Alert(Alert.AlertType.WARNING);
                                            alert.setTitle("Warning Dialog");
                                            alert.setHeaderText(null);
                                            alert.setContentText("Are you sure you want to delete the user?");
                                            Optional<ButtonType> result = alert.showAndWait();
                                            if (result.get() == ButtonType.OK) {
                                                User user = getTableView().getItems().get(getIndex());
                                                DatabaseHandler db = new DatabaseHandler();
                                                if (db.deleteUser(Integer.parseInt(user.getUser_no().substring(5)))) {
                                                    initializeViewUsers();
                                                }
                                            }
                                        }
                                    });

                                    HBox hBox = new HBox();
                                    hBox.getChildren().addAll(deleteImage, statusImage);
                                    hBox.setAlignment(Pos.CENTER);
                                    hBox.setSpacing(12);
                                    setGraphic(hBox);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };

        action_col.getStyleClass().add("centerAlignedTableColumnHeader");

        action_col.setCellFactory(cellFactory);

        status_col.setCellFactory(new Callback<TableColumn<User, String>, TableCell<User, String>>() {

            @Override
            public TableCell<User, String> call(TableColumn<User, String> p) {


                return new TableCell<User, String>() {

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!isEmpty()) {
                            if (item.equals("deactive")) {
                                this.setStyle("-fx-background-color: #f6182a;-fx-border-color: #FFFFFF;" +
                                        "-fx-border-width: 1px;-fx-text-fill: #FFFFFF;-fx-font-size: 12px;-fx-font-weight: normal;-fx-alignment: center");
                            } else {
                                this.setStyle("-fx-background-color:  #1ed760;-fx-border-color: #EEEEEE;" +
                                        "-fx-border-width: 1px;-fx-text-fill: #FFFFFF;-fx-font-size: 12px;-fx-font-weight:normal;-fx-alignment: center");
                            }
                            setText(item);
                        }
                    }
                };
            }
        });

        status_col.getStyleClass().add("centerAlignedTableColumnHeader");

        uid_col.setCellFactory(new Callback<TableColumn<User, String>, TableCell<User, String>>() {
            @Override
            public TableCell<User, String> call(TableColumn<User, String> param) {
                return new TableCell<User, String>() {

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!isEmpty()) {
                            this.setStyle("-fx-alignment: center-left");
                            setText(item);
                        }
                    }
                };
            }
        });

        name_col.setCellFactory(new Callback<TableColumn<User, String>, TableCell<User, String>>() {
            @Override
            public TableCell<User, String> call(TableColumn<User, String> param) {
                return new TableCell<User, String>() {

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!isEmpty()) {
                            this.setStyle("-fx-alignment: center-left");
                            setText(item);
                        }
                    }
                };
            }
        });

        role_col.setCellFactory(new Callback<TableColumn<User, String>, TableCell<User, String>>() {
            @Override
            public TableCell<User, String> call(TableColumn<User, String> param) {
                return new TableCell<User, String>() {

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!isEmpty()) {
                            this.setStyle("-fx-alignment: center-left");
                            setText(item);
                        }
                    }
                };
            }
        });
    }

    public void registerUser() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        if (fname_txt.getText().equals("") || lname_txt.getText().equals("") || uname_txt.getText().equals("") || role_box.getValue().equals("select role")) {
            alert.setContentText("Please fill all fields!");
            alert.showAndWait();
        } else {
            DatabaseHandler db = new DatabaseHandler();
            boolean result = db.addUser(fname_txt.getText(), lname_txt.getText(), uname_txt.getText(), uname_txt.getText().toLowerCase() + "123", ((RadioButton) user_genderChoice.getSelectedToggle()).getText(), role_box.getValue().toString(), "active");
            if (result) {
                alert.setContentText("Successful added!");
                initializeViewUsers();
                fname_txt.setText("");
                lname_txt.setText("");
                uname_txt.setText("");
                user_genderChoice.selectToggle(null);

            } else {
                alert.setContentText("UnSuccessful!");
            }
            alert.showAndWait();
        }
    }

    public void initializeViewUsers() {
        name_col.setCellValueFactory(new PropertyValueFactory<>("name"));
        uid_col.setCellValueFactory(new PropertyValueFactory<>("user_no"));
        role_col.setCellValueFactory(new PropertyValueFactory<>("role"));
        action_col.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
        status_col.setCellValueFactory(new PropertyValueFactory<>("status"));
        setUpUserTable();
        DatabaseHandler db = new DatabaseHandler();
        users_table.setItems(null);
        try {
//            System.out.println(userObject.getString("role"));
            users_table.setItems(db.getUsers(LoginController.userObject.getString("role")));
            user_rows_txt.setText(String.valueOf(db.getUsers(LoginController.userObject.getString("role")).size()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
