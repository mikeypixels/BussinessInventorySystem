package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import sample.Handlers.DatabaseHandler;
import sample.Objects.Evaluation;

import java.net.URL;
import java.util.ResourceBundle;

public class ReportController implements Initializable {

    @FXML
    private CategoryAxis month_category, bar_month_figures;

    @FXML
    private NumberAxis sales_figures, bar_sales_figures;

    @FXML
    private LineChart<?,?> lineChart;

    @FXML
    private BarChart<?,?> barChart;

    @FXML
    TableColumn<Evaluation, String> month_col;
    @FXML
    TableColumn<Evaluation, String> income_col;
    @FXML
    TableColumn<Evaluation, String> purchase_col;
    @FXML
    TableColumn<Evaluation, String> profit_col;
    @FXML
    TableColumn<Evaluation, String> expense_col;
    @FXML
    TableView<Evaluation> evaluation_table;

    @FXML Label eval_rows_txt;

    ObservableList<Evaluation> evaluations = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        fillEvaluation();

        month_col.setCellValueFactory(new PropertyValueFactory<>("date"));
        purchase_col.setCellValueFactory(new PropertyValueFactory<>("total_purchases"));
        expense_col.setCellValueFactory(new PropertyValueFactory<>("expenses"));
        income_col.setCellValueFactory(new PropertyValueFactory<>("income"));
        profit_col.setCellValueFactory(new PropertyValueFactory<>("profit"));

        XYChart.Series series = new XYChart.Series();

        series.getData().add(new XYChart.Data("Jan", 30000));
        series.getData().add(new XYChart.Data("Feb", 23000));
        series.getData().add(new XYChart.Data("Mar", 80000));
        series.getData().add(new XYChart.Data("Apr", 93000));
        series.getData().add(new XYChart.Data("May", 7000));
        series.getData().add(new XYChart.Data("Jun", 49000));
        series.getData().add(new XYChart.Data("Jul", 120000));
        series.getData().add(new XYChart.Data("Aug", 54000));
        series.getData().add(new XYChart.Data("Sep", 70000));
        series.getData().add(new XYChart.Data("Oct", 92000));
        series.getData().add(new XYChart.Data("Nov", 29000));
        series.getData().add(new XYChart.Data("Dec", 14000));

        XYChart.Series series2 = new XYChart.Series();

        series2.getData().add(new XYChart.Data("Jan", 39000));
        series2.getData().add(new XYChart.Data("Feb", 13000));
        series2.getData().add(new XYChart.Data("Mar", 56000));
        series2.getData().add(new XYChart.Data("Apr", 60000));
        series2.getData().add(new XYChart.Data("May", 100000));
        series2.getData().add(new XYChart.Data("Jun", 149000));
        series2.getData().add(new XYChart.Data("Jul", 76000));
        series2.getData().add(new XYChart.Data("Aug", 5000));
        series2.getData().add(new XYChart.Data("Sep", 51000));
        series2.getData().add(new XYChart.Data("Oct", 25000));
        series2.getData().add(new XYChart.Data("Nov", 90000));
        series2.getData().add(new XYChart.Data("Dec", 99000));

        XYChart.Series series3 = new XYChart.Series();

        series3.getData().add(new XYChart.Data("Jan", 100000));
        series3.getData().add(new XYChart.Data("Feb", 63000));
        series3.getData().add(new XYChart.Data("Mar", 86000));
        series3.getData().add(new XYChart.Data("Apr", 33000));
        series3.getData().add(new XYChart.Data("May", 18000));
        series3.getData().add(new XYChart.Data("Jun", 69000));
        series3.getData().add(new XYChart.Data("Jul", 16000));
        series3.getData().add(new XYChart.Data("Aug", 34000));
        series3.getData().add(new XYChart.Data("Sep", 88000));
        series3.getData().add(new XYChart.Data("Oct", 114000));
        series3.getData().add(new XYChart.Data("Nov", 140000));
        series3.getData().add(new XYChart.Data("Dec", 32000));

        lineChart.getData().addAll(series, series2, series3);

        XYChart.Series bar1 = new XYChart.Series();

        bar1.getData().add(new XYChart.Data("2015", 100000));
        bar1.getData().add(new XYChart.Data("2016", 63000));
        bar1.getData().add(new XYChart.Data("2017", 86000));
        bar1.getData().add(new XYChart.Data("2018", 33000));
        bar1.getData().add(new XYChart.Data("2019", 18000));

        XYChart.Series bar2 = new XYChart.Series();

        bar2.getData().add(new XYChart.Data("2015", 39000));
        bar2.getData().add(new XYChart.Data("2016", 13000));
        bar2.getData().add(new XYChart.Data("2017", 56000));
        bar2.getData().add(new XYChart.Data("2018", 60000));
        bar2.getData().add(new XYChart.Data("2019", 100000));

        XYChart.Series bar3 = new XYChart.Series();

        bar3.getData().add(new XYChart.Data("2015", 30000));
        bar3.getData().add(new XYChart.Data("2016", 23000));
        bar3.getData().add(new XYChart.Data("2017", 80000));
        bar3.getData().add(new XYChart.Data("2018", 93000));
        bar3.getData().add(new XYChart.Data("2019", 7000));

        barChart.getData().addAll(bar1, bar2, bar3);

        eval_rows_txt.setText("");
    }

    public void fillEvaluation(){

        DatabaseHandler db = new DatabaseHandler();

        setUpEvaluations();

        System.out.println("It passes through here");

        evaluation_table.setItems(null);

        Task<ObservableList<Evaluation>> loadDataTask = new Task<ObservableList<Evaluation>>() {
            @Override
            protected ObservableList<Evaluation> call() throws Exception {
                // load data and populate list ...
                evaluations = db.evaluation();
                return  evaluations;
            }
        };
        loadDataTask.setOnSucceeded(e ->
        {if(loadDataTask.getValue().size()>0){
            evaluation_table.setItems(loadDataTask.getValue());
        }else {
            evaluation_table.setItems(null);
        }});
        loadDataTask.setOnFailed(e -> {/*Handle Errors */});

        ProgressIndicator progressIndicator = new ProgressIndicator();
        progressIndicator.setMaxWidth(50);
        progressIndicator.setMaxHeight(50);
        progressIndicator.setStyle("-fx-progress-color: #558C89;");
        evaluation_table.setPlaceholder(progressIndicator);

        Thread loadDataThread = new Thread(loadDataTask);
        loadDataThread.start();

        eval_rows_txt.setText(String.valueOf(evaluations.size()));
    }

    public void setUpEvaluations(){

        month_col.setCellFactory(new Callback<TableColumn<Evaluation, String>, TableCell<Evaluation, String>>() {
            @Override
            public TableCell<Evaluation, String> call(TableColumn<Evaluation, String> param) {
                return new TableCell<Evaluation, String>() {

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

        purchase_col.setCellFactory(new Callback<TableColumn<Evaluation, String>, TableCell<Evaluation, String>>() {
            @Override
            public TableCell<Evaluation, String> call(TableColumn<Evaluation, String> param) {
                return new TableCell<Evaluation, String>() {

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

        expense_col.setCellFactory(new Callback<TableColumn<Evaluation, String>, TableCell<Evaluation, String>>() {
            @Override
            public TableCell<Evaluation, String> call(TableColumn<Evaluation, String> param) {
                return new TableCell<Evaluation, String>() {

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

        income_col.setCellFactory(new Callback<TableColumn<Evaluation, String>, TableCell<Evaluation, String>>() {
            @Override
            public TableCell<Evaluation, String> call(TableColumn<Evaluation, String> param) {
                return new TableCell<Evaluation, String>() {

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

        profit_col.setCellFactory(new Callback<TableColumn<Evaluation, String>, TableCell<Evaluation, String>>() {
            @Override
            public TableCell<Evaluation, String> call(TableColumn<Evaluation, String> param) {
                return new TableCell<Evaluation, String>() {

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (!isEmpty()) {
                            if(item.startsWith("-"))
                                this.setStyle("-fx-text-fill: red;-fx-alignment: center-left");
                            else
                                this.setStyle("-fx-alignment: center-left");
                            setText(item);
                        }
                    }
                };
            }
        });
    }

//    public void searchEvaluation(){
//        if(evaluation_choicebox.getValue().equals("Choose store")){
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setTitle("Store option");
//            alert.setHeaderText(null);
//            alert.setContentText("Please choose store!");
//            alert.showAndWait();
//        }else {
//            ObservableList<Evaluation> evaluations = FXCollections.observableArrayList();
//            for(Evaluation evaluation : this.evaluations){
//                if(evaluation.getDate().split("/")[0].toLowerCase().contains(searchEvTxt.getText())||evaluation.getDate().split("/")[1].contains(searchEvTxt.getText())){
//                    evaluations.add(evaluation);
//                }
//            }
//            setUpEvaluations();
//            ev_table.setItems(null);
//            ev_table.setItems(evaluations);
//        }
//    }

    public void refreshEvaluation(){
        fillEvaluation();
    }
}
