package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
    }
}
