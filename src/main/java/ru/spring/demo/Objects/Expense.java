package ru.spring.demo.Objects;

public class Expense {
    private int id;
    private String name;
    private double money;
    private String type;
    private String date;

    public Expense(int id, String name, double money, String expenseType, String date) {
        this.id = id;
        this.name = name;
        this.money = money;
        this.type = expenseType;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }
}
