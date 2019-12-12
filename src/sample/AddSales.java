package sample;

import animatefx.animation.BounceIn;
import animatefx.animation.BounceOut;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import sample.Handlers.DatabaseHandler;
import sample.Objects.Product;
import sample.Objects.Stock;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddSales extends DashboardController implements Initializable {

    @FXML
    JFXComboBox sale_category, sale_sub_category;
    @FXML
    JFXTextField sale_amount, sale_quantity;
    @FXML
    JFXDatePicker sale_date;
    @FXML
    ImageView back_arrow;
    @FXML
    JFXButton sale_submit;
    @FXML
    Label header_txt, av_quantity;

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

    int total = 0;
    String[] pName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if(DashboardController.tab_identifier.toLowerCase().equals("sales"))
            av_quantity.setVisible(true);
        else
            av_quantity.setVisible(false);

        back_arrow.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (DashboardController.tab_identifier.equals("sales")) {
                    BounceOut out = new BounceOut(DashboardController.add_sale_pane);
                    try {

                        AnchorPane category_pane = DashboardController.p_dash_item_pane;
                        BounceIn in = new BounceIn(category_pane);
                        out.setOnFinished(event1 -> {
                            DashboardController.p_dash_item_pane.getChildren().remove(DashboardController.add_sale_pane);
                            DashboardController.p_sales_anchorpane.setTopAnchor(DashboardController.p_item_dash_holder, 40.0);
                            DashboardController.p_item_dash_holder.getChildren().setAll(category_pane);

                            in.play();
                        });
                        out.play();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    DashboardController.available_stocks = FXCollections.observableArrayList();

                } else {
                    BounceOut out = new BounceOut(DashboardController.add_sale_pane);
                    try {

                        AnchorPane category_pane = DashboardController.p_dash_item_pane;
                        BounceIn in = new BounceIn(category_pane);
                        out.setOnFinished(event1 -> {
                            DashboardController.p_dash_item_pane.getChildren().remove(DashboardController.add_sale_pane);
                            DashboardController.p_sales_anchorpane.setTopAnchor(DashboardController.p_item_dash_holder, 40.0);
                            DashboardController.p_item_dash_holder.getChildren().setAll(category_pane);

                            in.play();
                        });
                        out.play();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    DashboardController.available_store_stocks = FXCollections.observableArrayList();
                }
            }
        });

//        System.out.println(DashboardController.category_sale.toLowerCase());

        if (DashboardController.tab_identifier.equals("sales")) {
            header_txt.setText("Sales");
        } else {
            header_txt.setText("Purchase");
        }

        if (DashboardController.category_sale.toLowerCase().equals("tv")) {
            sale_category.setItems(tv);
            sale_category.setValue(tv.get(0));
            sale_sub_category.setItems(tv_homebase_chogo);

            total = 0;

            for (Stock stock : DashboardController.available_stocks) {
                pName = stock.getProduct_name().split("/");
//                System.out.println("This is for the product name: " + stock.getProduct_name());
//                System.out.println(pName[0]);
//                System.out.println(pName[1]);
                if ((pName[0] + "/" + pName[1]).equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString())) {
                    total = total + stock.getAvailable_quantity();
                }
            }

            av_quantity.setText("Available quantity = " + total);

            sale_sub_category.valueProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    total = 0;
                    for (Stock stock : DashboardController.available_stocks) {
                        pName = stock.getProduct_name().split("/");
                        System.out.println(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString() + "/" + sale_sub_category.getValue().toString());
//                                        System.out.println(pName[0] + "/" + pName[1]);
                        if (stock.getProduct_name().equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString() + "/" + sale_sub_category.getValue().toString())) {
                            System.out.println("stock: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString() + "/" + sale_sub_category.getValue().toString());
                            total = total + stock.getAvailable_quantity();
                            System.out.println("socket total: " + total);
                        }
                    }

                    av_quantity.setText("Available quantity = " + total);
                }
            });

            sale_category.valueProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                    if (sale_category.getValue().toString().equals("HOMEBASE CHOGO")) {
                        sale_sub_category.setItems(tv_homebase_chogo);

                        total = 0;
                        for (Stock stock : DashboardController.available_stocks) {
                            pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
//                            System.out.println("This is a new one: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString());
                            if ((pName[0] + "/" + pName[1]).equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString())) {
                                total = total + stock.getAvailable_quantity();
                            }
                        }

                        sale_sub_category.valueProperty().addListener(new ChangeListener() {
                            @Override
                            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                                total = 0;
                                for (Stock stock : DashboardController.available_stocks) {
                                    pName = stock.getProduct_name().split("/");
                                    System.out.println(stock.getProduct_name());
                                    if (stock.getProduct_name().equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString() + "/" + sale_sub_category.getValue().toString())) {
                                        total = total + stock.getAvailable_quantity();
                                        System.out.println("The total is: " + total);
                                    }
                                }

                                av_quantity.setText("Available quantity = " + total);
                            }
                        });

                        av_quantity.setText("Available quantity = " + total);

                    } else if (sale_category.getValue().toString().equals("SING SAN")) {
                        sale_sub_category.setItems(tv_singsang);

                        total = 0;
                        for (Stock stock : DashboardController.available_stocks) {
                            pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
//                            System.out.println("This is a new one: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString());
                            if ((pName[0] + "/" + pName[1]).equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString())) {
                                total = total + stock.getAvailable_quantity();
                            }
                        }

                        sale_sub_category.valueProperty().addListener(new ChangeListener() {
                            @Override
                            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                                total = 0;
                                for (Stock stock : DashboardController.available_stocks) {
                                    pName = stock.getProduct_name().split("/");
                                    if (stock.getProduct_name().equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString() + "/" + sale_sub_category.getValue().toString())) {
                                        total = total + stock.getAvailable_quantity();
                                    }
                                }

                                av_quantity.setText("Available quantity = " + total);
                            }
                        });

                        av_quantity.setText("Available quantity = " + total);
                    } else if (sale_category.getValue().toString().equals("HOMEBASE FLAT")) {
                        sale_sub_category.setItems(tv_homebase_flat);

                        total = 0;
                        for (Stock stock : DashboardController.available_stocks) {
                            pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
//                            System.out.println("This is a new one: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString());
                            if ((pName[0] + "/" + pName[1]).equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString())) {
                                total = total + stock.getAvailable_quantity();
                            }
                        }

                        sale_sub_category.valueProperty().addListener(new ChangeListener() {
                            @Override
                            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                                total = 0;
                                for (Stock stock : DashboardController.available_stocks) {
                                    pName = stock.getProduct_name().split("/");
                                    if (stock.getProduct_name().equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString() + "/" + sale_sub_category.getValue().toString())) {
                                        total = total + stock.getAvailable_quantity();
                                    }
                                }


                                av_quantity.setText("Available quantity = " + total);
                            }
                        });

                        av_quantity.setText("Available quantity = " + total);
                    } else if (sale_category.getValue().toString().equals("MR UK CHOGO")) {
                        sale_sub_category.setItems(tv_mr_uk_chogo);

                        total = 0;
                        for (Stock stock : DashboardController.available_stocks) {
                            pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
//                            System.out.println("This is a new one: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString());
                            if ((pName[0] + "/" + pName[1]).equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString())) {
                                total = total + stock.getAvailable_quantity();
                            }
                        }

                        sale_sub_category.valueProperty().addListener(new ChangeListener() {
                            @Override
                            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                                total = 0;
                                for (Stock stock : DashboardController.available_stocks) {
                                    pName = stock.getProduct_name().split("/");
                                    if (stock.getProduct_name().equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString() + "/" + sale_sub_category.getValue().toString())) {
                                        total = total + stock.getAvailable_quantity();
                                    }
                                }


                                av_quantity.setText("Available quantity = " + total);
                            }
                        });

                        av_quantity.setText("Available quantity = " + total);
                    } else if (sale_category.getValue().toString().equals("MR UK FLAT")) {
                        sale_sub_category.setItems(tv_mr_uk_flat);

                        total = 0;
                        for (Stock stock : DashboardController.available_stocks) {
                            pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
//                            System.out.println("This is a new one: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString());
                            if ((pName[0] + "/" + pName[1]).equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString())) {
                                total = total + stock.getAvailable_quantity();
                            }
                        }

                        sale_sub_category.valueProperty().addListener(new ChangeListener() {
                            @Override
                            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                                total = 0;
                                for (Stock stock : DashboardController.available_stocks) {
                                    pName = stock.getProduct_name().split("/");
                                    if (stock.getProduct_name().equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString() + "/" + sale_sub_category.getValue().toString())) {
                                        total = total + stock.getAvailable_quantity();
                                    }
                                }


                                av_quantity.setText("Available quantity = " + total);
                            }
                        });

                        av_quantity.setText("Available quantity = " + total);

                    } else if (sale_category.getValue().toString().equals("STAR X")) {
                        sale_sub_category.setItems(tv_star_x);

                        total = 0;
                        for (Stock stock : DashboardController.available_stocks) {
                            pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
//                            System.out.println("This is a new one: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString());
                            if ((pName[0] + "/" + pName[1]).equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString())) {
                                total = total + stock.getAvailable_quantity();
                            }
                        }

                        sale_sub_category.valueProperty().addListener(new ChangeListener() {
                            @Override
                            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                                total = 0;
                                for (Stock stock : DashboardController.available_stocks) {
                                    pName = stock.getProduct_name().split("/");
                                    if (stock.getProduct_name().equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString() + "/" + sale_sub_category.getValue().toString())) {
                                        total = total + stock.getAvailable_quantity();
                                    }
                                }


                                av_quantity.setText("Available quantity = " + total);
                            }
                        });

                        av_quantity.setText("Available quantity = " + total);

                    } else if (sale_category.getValue().toString().equals("RISING")) {
                        sale_sub_category.setItems(tv_rising);

                        total = 0;
                        for (Stock stock : DashboardController.available_stocks) {
                            pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
//                            System.out.println("This is a new one: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString());
                            if ((pName[0] + "/" + pName[1]).equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString())) {
                                total = total + stock.getAvailable_quantity();
                            }
                        }

                        sale_sub_category.valueProperty().addListener(new ChangeListener() {
                            @Override
                            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                                total = 0;
                                for (Stock stock : DashboardController.available_stocks) {
                                    pName = stock.getProduct_name().split("/");
                                    if (stock.getProduct_name().equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString() + "/" + sale_sub_category.getValue().toString())) {
                                        total = total + stock.getAvailable_quantity();
                                    }
                                }


                                av_quantity.setText("Available quantity = " + total);
                            }
                        });

                        av_quantity.setText("Available quantity = " + total);

                    } else if (sale_category.getValue().toString().equals("OCNG")) {
                        sale_sub_category.setItems(tv_ocng);

                        total = 0;
                        for (Stock stock : DashboardController.available_stocks) {
                            pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
//                            System.out.println("This is a new one: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString());
                            if ((pName[0] + "/" + pName[1]).equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString())) {
                                total = total + stock.getAvailable_quantity();
                            }
                        }

                        sale_sub_category.valueProperty().addListener(new ChangeListener() {
                            @Override
                            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                                total = 0;
                                for (Stock stock : DashboardController.available_stocks) {
                                    pName = stock.getProduct_name().split("/");
                                    if (stock.getProduct_name().equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString() + "/" + sale_sub_category.getValue().toString())) {
                                        total = total + stock.getAvailable_quantity();
                                    }
                                }


                                av_quantity.setText("Available quantity = " + total);
                            }
                        });

                        av_quantity.setText("Available quantity = " + total);

                    } else if (sale_category.getValue().toString().equals("RITECH")) {
                        sale_sub_category.setItems(tv_ritech);

                        total = 0;
                        for (Stock stock : DashboardController.available_stocks) {
                            pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
//                            System.out.println("This is a new one: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString());
                            if ((pName[0] + "/" + pName[1]).equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString())) {
                                total = total + stock.getAvailable_quantity();
                            }
                        }

                        sale_sub_category.valueProperty().addListener(new ChangeListener() {
                            @Override
                            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                                total = 0;
                                for (Stock stock : DashboardController.available_stocks) {
                                    pName = stock.getProduct_name().split("/");
                                    if (stock.getProduct_name().equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString() + "/" + sale_sub_category.getValue().toString())) {
                                        total = total + stock.getAvailable_quantity();
                                    }
                                }

                                av_quantity.setText("Available quantity = " + total);
                            }
                        });

                        av_quantity.setText("Available quantity = " + total);

                    } else if (sale_category.getValue().toString().equals("SUNDAR")) {
                        sale_sub_category.setItems(tv_sundar);

                        total = 0;
                        for (Stock stock : DashboardController.available_stocks) {
                            pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
//                            System.out.println("This is a new one: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString());
                            if ((pName[0] + "/" + pName[1]).equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString())) {
                                total = total + stock.getAvailable_quantity();
                            }
                        }

                        sale_sub_category.valueProperty().addListener(new ChangeListener() {
                            @Override
                            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                                total = 0;
                                for (Stock stock : DashboardController.available_stocks) {
                                    pName = stock.getProduct_name().split("/");
                                    if (stock.getProduct_name().equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString() + "/" + sale_sub_category.getValue().toString())) {
                                        total = total + stock.getAvailable_quantity();
                                    }
                                }

                                av_quantity.setText("Available quantity = " + total);
                            }
                        });

                        av_quantity.setText("Available quantity = " + total);

                    } else if (sale_category.getValue().toString().equals("EVOL")) {
                        sale_sub_category.setItems(tv_evol);

                        total = 0;
                        for (Stock stock : DashboardController.available_stocks) {
                            pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
//                            System.out.println("This is a new one: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString());
                            if ((pName[0] + "/" + pName[1]).equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString())) {
                                total = total + stock.getAvailable_quantity();
                            }
                        }

                        av_quantity.setText("Available quantity = " + total);

                        sale_sub_category.valueProperty().addListener(new ChangeListener() {
                            @Override
                            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                                total = 0;
                                for (Stock stock : DashboardController.available_stocks) {
                                    pName = stock.getProduct_name().split("/");
                                    if (stock.getProduct_name().equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString() + "/" + sale_sub_category.getValue().toString())) {
                                        total = total + stock.getAvailable_quantity();
                                    }
                                }

                                av_quantity.setText("Available quantity = " + total);
                            }
                        });

                    }
                }
            });
        } else if (DashboardController.category_sale.toLowerCase().equals("wire")) {
            sale_sub_category.setVisible(false);
            sale_category.setLayoutY(164);
            sale_category.setItems(wire);
            sale_category.setValue(wire.get(0));

            total = 0;

            for (Stock stock : DashboardController.available_stocks) {
                pName = stock.getProduct_name().split("/");
//                System.out.println("This is for the product name: " + stock.getProduct_name());
//                System.out.println(pName[0]);
//                System.out.println(pName[1]);
                if ((pName[0] + "/" + pName[1]).equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString())) {
                    total = total + stock.getAvailable_quantity();
                }
            }

            av_quantity.setText("Available quantity = " + total);

        } else if (DashboardController.category_sale.toLowerCase().equals("tv-stand")) {
//            sale_sub_category.setVisible(false);
//            sale_category.setLayoutY(164);
            sale_category.setItems(tv_stand);
            sale_category.setValue(tv_stand.get(0));
            sale_sub_category.setItems(tv_stand_homebase);

            total = 0;

            for (Stock stock : DashboardController.available_stocks) {
                pName = stock.getProduct_name().split("/");
//                System.out.println("This is for the product name: " + stock.getProduct_name());
//                System.out.println(pName[0]);
//                System.out.println(pName[1]);
                if ((pName[0] + "/" + pName[1]).equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString())) {
                    total = total + stock.getAvailable_quantity();
                }
            }

            sale_sub_category.valueProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    total = 0;
                    for (Stock stock : DashboardController.available_stocks) {
                        pName = stock.getProduct_name().split("/");
                        System.out.println(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString() + "/" + sale_sub_category.getValue().toString());
//                                        System.out.println(pName[0] + "/" + pName[1]);
                        if (stock.getProduct_name().equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString() + "/" + sale_sub_category.getValue().toString())) {
                            System.out.println("stock: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString() + "/" + sale_sub_category.getValue().toString());
                            total = total + stock.getAvailable_quantity();
                            System.out.println("tv-stand total: " + total);
                        }
                    }

                    av_quantity.setText("Available quantity = " + total);
                }
            });

            av_quantity.setText("Available quantity = " + total);

        } else if (DashboardController.category_sale.toLowerCase().equals("mashuka")) {
            sale_sub_category.setVisible(false);
            sale_category.setLayoutY(164);
            sale_category.setItems(mashuka);
            sale_category.setValue(mashuka.get(0));

            total = 0;

            for (Stock stock : DashboardController.available_stocks) {
                pName = stock.getProduct_name().split("/");
//                System.out.println("This is for the product name: " + stock.getProduct_name());
//                System.out.println(pName[0]);
//                System.out.println(pName[1]);
                if ((pName[0] + "/" + pName[1]).equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString())) {
                    total = total + stock.getAvailable_quantity();
                }
            }

            av_quantity.setText("Available quantity = " + total);
        } else if (DashboardController.category_sale.toLowerCase().equals("pasi")) {
            sale_sub_category.setVisible(false);
            sale_category.setLayoutY(164);
            sale_category.setItems(pasi);
            sale_category.setValue(pasi.get(0));

            total = 0;

            for (Stock stock : DashboardController.available_stocks) {
                pName = stock.getProduct_name().split("/");
//                System.out.println("This is for the product name: " + stock.getProduct_name());
//                System.out.println(pName[0]);
//                System.out.println(pName[1]);
                if ((pName[0] + "/" + pName[1]).equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString())) {
                    total = total + stock.getAvailable_quantity();
                }
            }

            av_quantity.setText("Available quantity = " + total);
        } else if (DashboardController.category_sale.toLowerCase().equals("socket")) {
            sale_category.setItems(socket);
            sale_category.setValue(socket.get(0));
            sale_sub_category.setItems(socket_tronic);

            total = 0;

            for (Stock stock : DashboardController.available_stocks) {
                pName = stock.getProduct_name().split("/");
//                System.out.println(pName[0]);
//                System.out.println(pName[1]);
                if ((pName[0] + "/" + pName[1]).equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString())) {
                    total = total + stock.getAvailable_quantity();
                }
            }

            av_quantity.setText("Available quantity = " + total);

            sale_category.valueProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    if (sale_category.getValue().toString().equals("TRONIC")) {
                        sale_sub_category.setItems(socket_tronic);

                        total = 0;
                        for (Stock stock : DashboardController.available_stocks) {
                            pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
//                            System.out.println("This is a new one: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString());
                            if ((pName[0] + "/" + pName[1]).equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString())) {
                                total = total + stock.getAvailable_quantity();
                                System.out.println("socket total: " + total);
                            }
                        }

                        av_quantity.setText("Available quantity = " + total);

                        sale_sub_category.valueProperty().addListener(new ChangeListener() {
                            @Override
                            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                                total = 0;
                                for (Stock stock : DashboardController.available_stocks) {
                                    pName = stock.getProduct_name().split("/");
                                    System.out.println(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString() + "/" + sale_sub_category.getValue().toString());
//                                        System.out.println(pName[0] + "/" + pName[1]);
                                    if (stock.getProduct_name().equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString() + "/" + sale_sub_category.getValue().toString())) {
                                        System.out.println("stock: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString() + "/" + sale_sub_category.getValue().toString());
                                        total = total + stock.getAvailable_quantity();
                                        System.out.println("socket total: " + total);
                                    }
                                }

                                av_quantity.setText("Available quantity = " + total);
                            }
                        });
                    } else if (sale_category.getValue().toString().equals("SUNDAR")) {
                        sale_sub_category.setItems(socket_sundar);

                        total = 0;
                        for (Stock stock : DashboardController.available_stocks) {
                            pName = stock.getProduct_name().split("/");
//                            System.out.println(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString());
//                            System.out.println(pName[0] + "/" + pName[1]);
                            if ((pName[0] + "/" + pName[1]).equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString())) {
                                total = total + stock.getAvailable_quantity();
                                System.out.println(total);
                            }
                        }

                        av_quantity.setText("Available quantity = " + total);

                        sale_sub_category.valueProperty().addListener(new ChangeListener() {
                            @Override
                            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                                total = 0;
                                for (Stock stock : DashboardController.available_stocks) {
                                    pName = stock.getProduct_name().split("/");
//                                    System.out.println(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString() + "/" + sale_sub_category.getValue().toString());
//                                    System.out.println(stock.getProduct_name());
                                    if (stock.getProduct_name().equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString() + "/" + sale_sub_category.getValue().toString())) {
//                                        System.out.println(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString() + "/" + sale_sub_category.getValue().toString());
                                        total = total + stock.getAvailable_quantity();
                                        System.out.println(total);
                                    }
                                }

                                av_quantity.setText("Available quantity = " + total);
                            }
                        });
                    }
                }
            });

            sale_sub_category.valueProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    total = 0;
                    for (Stock stock : DashboardController.available_stocks) {
                        pName = stock.getProduct_name().split("/");
                        System.out.println(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString() + "/" + sale_sub_category.getValue().toString());
//                                        System.out.println(pName[0] + "/" + pName[1]);
                        if (stock.getProduct_name().equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString() + "/" + sale_sub_category.getValue().toString())) {
                            System.out.println("stock: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString() + "/" + sale_sub_category.getValue().toString());
                            total = total + stock.getAvailable_quantity();
                            System.out.println("socket total: " + total);
                        }
                    }

                    av_quantity.setText("Available quantity = " + total);
                }
            });

        } else if (DashboardController.category_sale.toLowerCase().equals("deki")) {
            sale_category.setItems(deki);
            sale_category.setValue(deki.get(0));
            sale_sub_category.setItems(deki_uk);

            total = 0;

            for (Stock stock : DashboardController.available_stocks) {
                pName = stock.getProduct_name().split("/");
//                System.out.println("This is for the product name: " + stock.getProduct_name());
//                System.out.println(pName[0]);
//                System.out.println(pName[1]);
                if ((pName[0] + "/" + pName[1]).equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString())) {
                    total = total + stock.getAvailable_quantity();
                }
            }

            av_quantity.setText("Available quantity = " + total);

            sale_sub_category.valueProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    total = 0;
                    for (Stock stock : DashboardController.available_stocks) {
                        pName = stock.getProduct_name().split("/");
                        System.out.println(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString() + "/" + sale_sub_category.getValue().toString());
//                                        System.out.println(pName[0] + "/" + pName[1]);
                        if (stock.getProduct_name().equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString() + "/" + sale_sub_category.getValue().toString())) {
                            System.out.println("stock: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString() + "/" + sale_sub_category.getValue().toString());
                            total = total + stock.getAvailable_quantity();
                            System.out.println("socket total: " + total);
                        }
                    }

                    av_quantity.setText("Available quantity = " + total);
                }
            });

            sale_category.valueProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    if (sale_category.getValue().toString().equals("UK")) {
                        sale_sub_category.setItems(deki_uk);
                        sale_sub_category.setVisible(true);
                        sale_category.setLayoutY(121);

                        total = 0;
                        for (Stock stock : DashboardController.available_stocks) {
                            pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
//                            System.out.println("This is a new one: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString());
                            if ((pName[0] + "/" + pName[1]).equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString())) {
                                total = total + stock.getAvailable_quantity();
                            }
                        }

                        av_quantity.setText("Available quantity = " + total);

                        sale_sub_category.valueProperty().addListener(new ChangeListener() {
                            @Override
                            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                                total = 0;
                                for (Stock stock : DashboardController.available_stocks) {
                                    pName = stock.getProduct_name().split("/");
                                    if (stock.getProduct_name().equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString() + "/" + sale_sub_category.getValue().toString())) {
                                        total = total + stock.getAvailable_quantity();
                                    }
                                }

                                av_quantity.setText("Available quantity = " + total);
                            }
                        });
                    } else if (sale_category.getValue().toString().equals("SUNDAR")) {
                        sale_sub_category.setItems(deki_sundar);
                        sale_sub_category.setVisible(true);
                        sale_category.setLayoutY(121);

                        total = 0;
                        for (Stock stock : DashboardController.available_stocks) {
                            pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
//                            System.out.println("This is a new one: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString());
                            if ((pName[0] + "/" + pName[1]).equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString())) {
                                total = total + stock.getAvailable_quantity();
                            }
                        }

                        av_quantity.setText("Available quantity = " + total);

                        sale_sub_category.valueProperty().addListener(new ChangeListener() {
                            @Override
                            public void changed(ObservableValue observable, Object oldValue, Object newValue) {

                                sale_sub_category.setVisible(true);
                                total = 0;
                                for (Stock stock : DashboardController.available_stocks) {
                                    pName = stock.getProduct_name().split("/");
                                    if (stock.getProduct_name().equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString() + "/" + sale_sub_category.getValue().toString())) {
                                        total = total + stock.getAvailable_quantity();
                                    }
                                }

                                av_quantity.setText("Available quantity = " + total);
                            }
                        });
                    } else if (sale_category.getValue().toString().equals("REDI AT 7350")) {
                        sale_sub_category.setVisible(false);
                        sale_category.setLayoutY(164);

                        total = 0;

                        for (Stock stock : DashboardController.available_stocks) {
                            pName = stock.getProduct_name().split("/");
//                System.out.println("This is for the product name: " + stock.getProduct_name());
//                System.out.println(pName[0]);
//                System.out.println(pName[1]);
                            if ((pName[0] + "/" + pName[1]).equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString())) {
                                total = total + stock.getAvailable_quantity();
                            }
                        }

                        av_quantity.setText("Available quantity = " + total);
                    }
                }
            });

        } else if (DashboardController.category_sale.toLowerCase().equals("remote")) {
            sale_sub_category.setVisible(false);
            sale_category.setLayoutY(164);
            sale_category.setItems(remote);
            sale_category.setValue(remote.get(0));

            total = 0;

            for (Stock stock : DashboardController.available_stocks) {
                pName = stock.getProduct_name().split("/");
//                System.out.println("This is for the product name: " + stock.getProduct_name());
                System.out.println(pName[0]);
                System.out.println(pName[1]);
                if ((pName[0] + "/" + pName[1]).equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString())) {
                    total = total + stock.getAvailable_quantity();
                }
            }

            av_quantity.setText("Available quantity = " + total);

            sale_category.valueProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    total = 0;
                    for (Stock stock : DashboardController.available_stocks) {
                        pName = stock.getProduct_name().split("/");
                        System.out.println(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString());
//                                        System.out.println(pName[0] + "/" + pName[1]);
                        if (stock.getProduct_name().equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString())) {
//                            System.out.println("stock: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString() + "/" + sale_sub_category.getValue().toString());
                            total = total + stock.getAvailable_quantity();
                            System.out.println("socket total: " + total);
                        }
                    }

                    av_quantity.setText("Available quantity = " + total);
                }
            });

            av_quantity.setText("Available quantity = " + total);

        } else if (DashboardController.category_sale.toLowerCase().equals("redio")) {
            sale_category.setItems(redio);
            sale_category.setValue(redio.get(0));
            sale_sub_category.setItems(redio_sabufa_sp);

            total = 0;

            for (Stock stock : DashboardController.available_stocks) {
                pName = stock.getProduct_name().split("/");
//                System.out.println("This is for the product name: " + stock.getProduct_name());
//                System.out.println(pName[0]);
//                System.out.println(pName[1]);
                if ((pName[0] + "/" + pName[1]).equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString())) {
                    total = total + stock.getAvailable_quantity();
                }
            }

            av_quantity.setText("Available quantity = " + total);

            sale_sub_category.valueProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    total = 0;
                    for (Stock stock : DashboardController.available_stocks) {
                        pName = stock.getProduct_name().split("/");
                        System.out.println(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString() + "/" + sale_sub_category.getValue().toString());
//                                        System.out.println(pName[0] + "/" + pName[1]);
                        if (stock.getProduct_name().equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString() + "/" + sale_sub_category.getValue().toString())) {
                            System.out.println("stock: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString() + "/" + sale_sub_category.getValue().toString());
                            total = total + stock.getAvailable_quantity();
                            System.out.println("socket total: " + total);
                        }
                    }

                    av_quantity.setText("Available quantity = " + total);
                }
            });

            sale_category.valueProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    if (sale_category.getValue().toString().equals("SABUFA SP")) {
                        sale_sub_category.setItems(redio_sabufa_sp);

                        total = 0;
                        for (Stock stock : DashboardController.available_stocks) {
                            pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
//                            System.out.println("This is a new one: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString());
                            if ((pName[0] + "/" + pName[1]).equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString())) {
                                total = total + stock.getAvailable_quantity();
                            }
                        }

                        av_quantity.setText("Available quantity = " + total);

                        sale_sub_category.valueProperty().addListener(new ChangeListener() {
                            @Override
                            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                                total = 0;
                                for (Stock stock : DashboardController.available_stocks) {
                                    pName = stock.getProduct_name().split("/");
                                    if (stock.getProduct_name().equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString() + "/" + sale_sub_category.getValue().toString())) {
                                        total = total + stock.getAvailable_quantity();
                                    }
                                }

                                av_quantity.setText("Available quantity = " + total);
                            }
                        });
                    } else if (sale_category.getValue().toString().equals("SABUFA ALING")) {
                        sale_sub_category.setItems(redio_sabufa_aling);

                        total = 0;
                        for (Stock stock : DashboardController.available_stocks) {
                            pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
//                            System.out.println("This is a new one: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString());
                            if ((pName[0] + "/" + pName[1]).equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString())) {
                                total = total + stock.getAvailable_quantity();
                            }
                        }

                        av_quantity.setText("Available quantity = " + total);

                        sale_sub_category.valueProperty().addListener(new ChangeListener() {
                            @Override
                            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                                total = 0;
                                for (Stock stock : DashboardController.available_stocks) {
                                    pName = stock.getProduct_name().split("/");
                                    if (stock.getProduct_name().equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString() + "/" + sale_sub_category.getValue().toString())) {
                                        total = total + stock.getAvailable_quantity();
                                    }
                                }

                                av_quantity.setText("Available quantity = " + total);
                            }
                        });
                    } else if (sale_category.getValue().toString().equals("SABUFA ABODA")) {
                        sale_sub_category.setItems(redio_sabufa_aboda);

                        total = 0;
                        for (Stock stock : DashboardController.available_stocks) {
                            pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
//                            System.out.println("This is a new one: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString());
                            if ((pName[0] + "/" + pName[1]).equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString())) {
                                total = total + stock.getAvailable_quantity();
                            }
                        }

                        av_quantity.setText("Available quantity = " + total);

                        sale_sub_category.valueProperty().addListener(new ChangeListener() {
                            @Override
                            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                                total = 0;
                                for (Stock stock : DashboardController.available_stocks) {
                                    pName = stock.getProduct_name().split("/");
                                    if (stock.getProduct_name().equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString() + "/" + sale_sub_category.getValue().toString())) {
                                        total = total + stock.getAvailable_quantity();
                                    }
                                }

                                av_quantity.setText("Available quantity = " + total);
                            }
                        });
                    } else if (sale_category.getValue().toString().equals("SABUFA MR UK")) {
                        sale_sub_category.setItems(redio_sabufa_mr_uk);

                        total = 0;
                        for (Stock stock : DashboardController.available_stocks) {
                            pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
//                            System.out.println("This is a new one: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString());
                            if ((pName[0] + "/" + pName[1]).equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString())) {
                                total = total + stock.getAvailable_quantity();
                            }
                        }

                        av_quantity.setText("Available quantity = " + total);

                        sale_sub_category.valueProperty().addListener(new ChangeListener() {
                            @Override
                            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                                total = 0;
                                for (Stock stock : DashboardController.available_stocks) {
                                    pName = stock.getProduct_name().split("/");
                                    if (stock.getProduct_name().equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString() + "/" + sale_sub_category.getValue().toString())) {
                                        total = total + stock.getAvailable_quantity();
                                    }
                                }

                                av_quantity.setText("Available quantity = " + total);
                            }
                        });
                    } else if (sale_category.getValue().toString().equals("SABUFA SUNDAR")) {
                        sale_sub_category.setItems(redio_sabufa_sundar);

                        total = 0;
                        for (Stock stock : DashboardController.available_stocks) {
                            pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
//                            System.out.println("This is a new one: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString());
                            if ((pName[0] + "/" + pName[1]).equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString())) {
                                total = total + stock.getAvailable_quantity();
                            }
                        }

                        av_quantity.setText("Available quantity = " + total);

                        sale_sub_category.valueProperty().addListener(new ChangeListener() {
                            @Override
                            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                                total = 0;
                                for (Stock stock : DashboardController.available_stocks) {
                                    pName = stock.getProduct_name().split("/");
                                    if (stock.getProduct_name().equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString() + "/" + sale_sub_category.getValue().toString())) {
                                        total = total + stock.getAvailable_quantity();
                                    }
                                }

                                av_quantity.setText("Available quantity = " + total);
                            }
                        });
                    }
                }
            });

        } else if (DashboardController.category_sale.toLowerCase().equals("spika")) {
            sale_category.setItems(spika);
            sale_category.setValue(spika.get(0));
            sale_sub_category.setItems(spika_spika_ya_gari);

            total = 0;

            for (Stock stock : DashboardController.available_stocks) {
                pName = stock.getProduct_name().split("/");
//                System.out.println("This is for the product name: " + stock.getProduct_name());
//                System.out.println(pName[0]);
//                System.out.println(pName[1]);
                if ((pName[0] + "/" + pName[1]).equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString())) {
                    total = total + stock.getAvailable_quantity();
                }
            }

            av_quantity.setText("Available quantity = " + total);

            sale_sub_category.valueProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    total = 0;
                    for (Stock stock : DashboardController.available_stocks) {
                        pName = stock.getProduct_name().split("/");
                        System.out.println(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString() + "/" + sale_sub_category.getValue().toString());
//                                        System.out.println(pName[0] + "/" + pName[1]);
                        if (stock.getProduct_name().equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString() + "/" + sale_sub_category.getValue().toString())) {
                            System.out.println("stock: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString() + "/" + sale_sub_category.getValue().toString());
                            total = total + stock.getAvailable_quantity();
                            System.out.println("socket total: " + total);
                        }
                    }

                    av_quantity.setText("Available quantity = " + total);
                }
            });

            sale_category.valueProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    if (sale_category.getValue().toString().equals("SPIKA YA GARI")) {
                        sale_sub_category.setItems(spika_spika_ya_gari);

                        total = 0;
                        for (Stock stock : DashboardController.available_stocks) {
                            pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
//                            System.out.println("This is a new one: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString());
                            if ((pName[0] + "/" + pName[1]).equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString())) {
                                total = total + stock.getAvailable_quantity();
                            }
                        }

                        av_quantity.setText("Available quantity = " + total);

                        sale_sub_category.valueProperty().addListener(new ChangeListener() {
                            @Override
                            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                                total = 0;
                                for (Stock stock : DashboardController.available_stocks) {
                                    pName = stock.getProduct_name().split("/");
                                    if (stock.getProduct_name().equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString() + "/" + sale_sub_category.getValue().toString())) {
                                        total = total + stock.getAvailable_quantity();
                                    }
                                }

                                av_quantity.setText("Available quantity = " + total);
                            }
                        });
                    } else if (sale_category.getValue().toString().equals("SPIKA KUBWA")) {
                        sale_sub_category.setItems(spika_spika_kubwa);

                        total = 0;
                        for (Stock stock : DashboardController.available_stocks) {
                            pName = stock.getProduct_name().split("/");
//                            System.out.println(pName[0] + pName[1]);
//                            System.out.println("This is a new one: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString());
                            if ((pName[0] + "/" + pName[1]).equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString())) {
                                total = total + stock.getAvailable_quantity();
                            }
                        }

                        av_quantity.setText("Available quantity = " + total);

                        sale_sub_category.valueProperty().addListener(new ChangeListener() {
                            @Override
                            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                                total = 0;
                                for (Stock stock : DashboardController.available_stocks) {
                                    pName = stock.getProduct_name().split("/");
                                    if (stock.getProduct_name().equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString() + "/" + sale_sub_category.getValue().toString())) {
                                        total = total + stock.getAvailable_quantity();
                                    }
                                }

                                av_quantity.setText("Available quantity = " + total);
                            }
                        });
                    }
                }
            });

        } else if (DashboardController.category_sale.toLowerCase().equals("fan")) {
            sale_sub_category.setVisible(false);
            sale_category.setLayoutY(164);
            sale_category.setItems(fan);
            sale_category.setValue(fan.get(0));

            total = 0;

            for (Stock stock : DashboardController.available_stocks) {
                pName = stock.getProduct_name().split("/");
//                System.out.println("This is for the product name: " + stock.getProduct_name());
//                System.out.println(pName[0]);
//                System.out.println(pName[1]);
                if ((pName[0] + "/" + pName[1]).equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString())) {
                    total = total + stock.getAvailable_quantity();
                }
            }

            sale_category.valueProperty().addListener(new ChangeListener() {
                @Override
                public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                    total = 0;
                    for (Stock stock : DashboardController.available_stocks) {
                        pName = stock.getProduct_name().split("/");
                        System.out.println(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString());
//                                        System.out.println(pName[0] + "/" + pName[1]);
                        if (stock.getProduct_name().equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString())) {
//                            System.out.println("stock: " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString() + "/" + sale_sub_category.getValue().toString());
                            total = total + stock.getAvailable_quantity();
                            System.out.println("socket total: " + total);
                        }
                    }

                    av_quantity.setText("Available quantity = " + total);
                }
            });

            av_quantity.setText("Available quantity = " + total);

        } else if (DashboardController.category_sale.toLowerCase().equals("solar taa")) {
            sale_sub_category.setVisible(false);
            sale_category.setLayoutY(164);
            sale_category.setItems(solar_taa);
            sale_category.setValue(solar_taa.get(0));

            total = 0;

            for (Stock stock : DashboardController.available_stocks) {
                pName = stock.getProduct_name().split("/");
//                System.out.println("This is for the product name: " + stock.getProduct_name());
//                System.out.println(pName[0]);
//                System.out.println(pName[1]);
                if ((pName[0]).equals(DashboardController.category_sale.toUpperCase())) {
                    total = total + stock.getAvailable_quantity();
                }
            }

            av_quantity.setText("Available quantity = " + total);

        } else if (DashboardController.category_sale.toLowerCase().equals("friji")) {
            sale_sub_category.setVisible(false);
            sale_category.setLayoutY(164);
            sale_category.setItems(friji);
            sale_category.setValue(friji.get(0));

            total = 0;

            for (Stock stock : DashboardController.available_stocks) {
                pName = stock.getProduct_name().split("/");
//                System.out.println("This is for the product name: " + stock.getProduct_name());
//                System.out.println(pName[0]);
//                System.out.println(pName[1]);
                if ((pName[0]).equals(DashboardController.category_sale.toUpperCase())) {
                    total = total + stock.getAvailable_quantity();
                }
            }

            av_quantity.setText("Available quantity = " + total);

        }
    }

    public void addSale() {
        LocalDate date = sale_date.getValue();

        if (sale_sub_category.isVisible()) {
            if (sale_sub_category.getValue().toString().isEmpty() || sale_quantity.getText().isEmpty() || date == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Empty Field");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all fields!");
                alert.showAndWait();
            } else {
                try {
                    int quantity_txt = Integer.parseInt(sale_quantity.getText());

                    String[] cat_quantity = av_quantity.getText().split("=");

                    DatabaseHandler db = new DatabaseHandler();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Sale Add");
                    alert.setHeaderText(null);

                    if (DashboardController.tab_identifier.equals("sales")) {

                        if (quantity_txt > Integer.parseInt(cat_quantity[1].replaceAll(" ", ""))) {
                            alert.setContentText("No enough " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString() + "/" + sale_sub_category.getValue().toString() + " in store!");
                            alert.showAndWait();
                        } else {
                            double cost = 0;
                            int product_id = 0;
                            for (Product product : db.loadProductList()) {
                                if (product.getProduct_name().equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString() + "/" + sale_sub_category.getValue().toString())) {
                                    product_id = Integer.parseInt(product.getId());
                                    System.out.println("This is the id: " + product_id);
                                }
                            }

                            System.out.println("This is the id: " + product_id);

                            cost = Double.parseDouble(sale_amount.getText().replaceAll(",", ""));

                            try {
                                boolean result = db.addSale(product_id, quantity_txt, cost, date, LoginController.userObject.getInt("user_id"));

                                DashboardController dc = new DashboardController();
                                if (result) {
                                    refreshItemPane(category_sale.toUpperCase(), quantity_txt);
                                    dc.fillSales();
                                    System.out.println(dc.getCurrentDateNew());
                                    System.out.println(sale_date.getValue().toString());
                                    String[] sales_string = DashboardController.sales_today.getText().split(" ");
                                    if(sale_date.getValue().toString().equals(dc.getCurrentDate())){
                                        DashboardController.sales_today.setText(String.format("%,.0f", Double.parseDouble(sales_string[0].replaceAll(",",""))+cost) + " Tshs");
                                    }
                                    sale_quantity.setText("");
                                    sale_amount.setText("");
                                    sale_date.setValue(null);
                                    av_quantity.setText("Available quantity = " + (Integer.parseInt(cat_quantity[1].replaceAll(" ", "")) - quantity_txt));
//                                    if(sale_date.getValue())
                                    alert.setContentText("Sale successful added!");
                                    alert.showAndWait();
                                } else {
                                    alert.setContentText("Unsuccessful added!");
                                    alert.showAndWait();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } else {

                        double cost = 0;
                        int product_id = 0;
                        for (Product product : db.loadProductList()) {
                            if (product.getProduct_name().equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString() + "/" + sale_sub_category.getValue().toString())) {
                                product_id = Integer.parseInt(product.getId());
                                System.out.println("This is the id: " + product_id);
                            }
                        }

                        System.out.println("This is the id: " + product_id);

                        cost = Double.parseDouble(sale_amount.getText().replaceAll(",", ""));

                        try {
                            boolean result = db.addPurchase(product_id, quantity_txt, cost, date, LoginController.userObject.getInt("user_id"));

                            DashboardController dc = new DashboardController();
                            if (result) {
                                refreshAddStoreItemPane(category_sale.toUpperCase(), quantity_txt);
                                dc.fillPurchases();
                                String[] purchase_string = DashboardController.purchases_today.getText().split(" ");
                                if(sale_date.getValue().toString().equals(dc.getCurrentDate())){
                                    DashboardController.purchases_today.setText(String.format("%,.0f",Double.parseDouble(purchase_string[0].replaceAll(",",""))+cost) + " Tshs");
                                }
                                sale_quantity.setText("");
                                sale_amount.setText("");
                                sale_date.setValue(null);
                                av_quantity.setText("Available quantity = " + (Integer.parseInt(cat_quantity[1].replaceAll(" ", "")) - quantity_txt));
                                alert.setContentText("Purchase successful added!");
                                alert.showAndWait();
                            } else {
                                alert.setContentText("Unsuccessful added!");
                                alert.showAndWait();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }


                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Empty Field");
                    alert.setHeaderText(null);
                    alert.setContentText("Please check the amount or quantity!");
                    alert.showAndWait();
                    e.printStackTrace();
                }
            }
        } else {
            if (sale_quantity.getText().isEmpty() || date == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Empty Field");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all fields!");
                alert.showAndWait();
            } else {

                try {
                    int quantity_txt = Integer.parseInt(sale_quantity.getText());

                    String[] cat_quantity = av_quantity.getText().split("=");

                    DatabaseHandler db = new DatabaseHandler();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Sale Add");
                    alert.setHeaderText(null);


                    if (DashboardController.tab_identifier.equals("sales")) {

                        if (quantity_txt > Integer.parseInt(cat_quantity[1].replaceAll(" ", ""))) {
                            alert.setContentText("No enough " + DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString() + " in store!");
                            alert.showAndWait();
                        } else {
                            double cost = 0;
                            int product_id = 0;
                            for (Product product : db.loadProductList()) {
                                System.out.println(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString());
                                System.out.println(product.getProduct_name());
                                if (DashboardController.category_sale.toUpperCase().equals(sale_category.getValue().toString())) {
                                    if (product.getProduct_name().equals(DashboardController.category_sale.toUpperCase())) {
                                        product_id = Integer.parseInt(product.getId());
                                        System.out.println("This is the id: " + product_id);
                                        break;
                                    }
                                } else {
                                    if (product.getProduct_name().equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString())) {
                                        product_id = Integer.parseInt(product.getId());
                                        System.out.println("This is the id: " + product_id);
                                        break;
                                    }
                                }
                            }

                            System.out.println("This is the outside id: " + product_id);

                            cost = Double.parseDouble(sale_amount.getText().replaceAll(",", ""));

                            try {
                                boolean result = db.addSale(product_id, quantity_txt, cost, date, LoginController.userObject.getInt("user_id"));

                                DashboardController dc = new DashboardController();
                                if (result) {
                                    refreshItemPane(category_sale.toUpperCase(), quantity_txt);
                                    dc.fillSales();
                                    String[] sales_string = DashboardController.sales_today.getText().split(" ");
                                    if(sale_date.getValue().toString().equals(dc.getCurrentDate())){
                                        DashboardController.sales_today.setText(String.format("%,.0f", Double.parseDouble(sales_string[0].replaceAll(",",""))+cost) + " Tshs");
                                    }
                                    sale_quantity.setText("");
                                    sale_amount.setText("");
                                    sale_date.setValue(null);
                                    alert.setContentText("Sale successful added!");
                                    alert.showAndWait();
                                } else {
                                    alert.setContentText("Unsuccessful added!");
                                    alert.showAndWait();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } else {
                        double cost = 0;
                        int product_id = 0;
                        for (Product product : db.loadProductList()) {
                            System.out.println(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString());
                            System.out.println(product.getProduct_name());
                            if (DashboardController.category_sale.toUpperCase().equals(sale_category.getValue().toString())) {
                                if (product.getProduct_name().equals(DashboardController.category_sale.toUpperCase())) {
                                    product_id = Integer.parseInt(product.getId());
                                    System.out.println("This is the id: " + product_id);
                                    break;
                                }
                            } else {
                                if (product.getProduct_name().equals(DashboardController.category_sale.toUpperCase() + "/" + sale_category.getValue().toString())) {
                                    product_id = Integer.parseInt(product.getId());
                                    System.out.println("This is the id: " + product_id);
                                    break;
                                }
                            }
                        }

                        System.out.println("This is the outside id: " + product_id);

                        cost = Double.parseDouble(sale_amount.getText().replaceAll(",", ""));

                        try {
                            boolean result = db.addPurchase(product_id, quantity_txt, cost, date, LoginController.userObject.getInt("user_id"));

                            DashboardController dc = new DashboardController();
                            if (result) {
                                refreshAddStoreItemPane(category_sale.toUpperCase(), quantity_txt);
                                dc.fillPurchases();
                                String[] purchases_string = DashboardController.purchases_today.getText().split(" ");
                                if(sale_date.getValue().toString().equals(dc.getCurrentDate())){
                                    DashboardController.purchases_today.setText(String.format("%,.0f",Double.parseDouble(purchases_string[0].replaceAll(",",""))+cost) + " Tshs");
                                }
                                sale_quantity.setText("");
                                sale_amount.setText("");
                                sale_date.setValue(null);
                                alert.setContentText("Purchase successful added!");
                                alert.showAndWait();
                            } else {
                                alert.setContentText("Unsuccessful added!");
                                alert.showAndWait();
                            }
                        } catch (Exception e) {

                            Alert alert1 = new Alert(Alert.AlertType.WARNING);
                            alert1.setTitle("Empty Field");
                            alert1.setHeaderText(null);
                            alert1.setContentText("Please check the amount or quantity!");
                            alert1.showAndWait();
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e) {

                }


            }
        }

    }
}
