package ru.spring.demo.Objects;

public class Expense {
    private int id;
    private String name;
    private double sum;
    private int categoryId;
    private String ts;

    public Expense(int id, String name, double sum, int categoryId, String date) {
        this.id = id;
        this.name = name;
        this.sum = sum;
        this.categoryId = categoryId;
        this.ts = date;
    }

    public Expense(String name, double sum, int categoryId, String ts) {
        this.name = name;
        this.sum = sum;
        this.categoryId = categoryId;
        this.ts = ts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public int getId() {
        return id;
    }

}
