package com.kozyrski.budgetPlanner;

import java.util.Date;

public class Expenses {

    private int id;
    private String category;
    private Date date;
    private double amount;
    private double sum;
    private double lastId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public double getLastId() {
        return lastId;
    }

    public void setLastId(double lastId) {
        this.lastId = lastId;
    }
}

