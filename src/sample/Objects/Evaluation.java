package sample.Objects;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Evaluation {
    public final StringProperty date,total_purchases,expenses,income,profit;

    public Evaluation(String date, String total_purchases, String expenses, String income, String profit) {
        this.date = new SimpleStringProperty(date);
        this.total_purchases = new SimpleStringProperty(total_purchases);
        this.expenses = new SimpleStringProperty(expenses);
        this.income = new SimpleStringProperty(income);
        this.profit = new SimpleStringProperty(profit);
    }

    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getTotal_purchases() {
        return total_purchases.get();
    }

    public StringProperty total_purchasesProperty() {
        return total_purchases;
    }

    public void setTotal_purchases(String total_purchases) {
        this.total_purchases.set(total_purchases);
    }

    public String getExpenses() {
        return expenses.get();
    }

    public StringProperty expensesProperty() {
        return expenses;
    }

    public void setExpenses(String expenses) {
        this.expenses.set(expenses);
    }

    public String getIncome() {
        return income.get();
    }

    public StringProperty incomeProperty() {
        return income;
    }

    public void setIncome(String income) {
        this.income.set(income);
    }

    public String getProfit() {
        return profit.get();
    }

    public StringProperty profitProperty() {
        return profit;
    }

    public void setProfit(String profit) {
        this.profit.set(profit);
    }
}

