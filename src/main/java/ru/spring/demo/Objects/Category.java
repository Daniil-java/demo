package ru.spring.demo.Objects;

public class Category {
    private int id;
    private String category;

    public Category(int id, String name) {
        this.id = id;
        this.category = name;
    }

    public Category(String category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
