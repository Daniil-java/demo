package ru.spring.demo.Objects;

import java.time.LocalDateTime;

public class Filter {
    private String category = "";
    private double sumFrom = 0d;
    private double sumTo = Double.MAX_VALUE;
    private LocalDateTime dateFrom = LocalDateTime.MIN;
    private LocalDateTime dateTo = LocalDateTime.MAX;
    private String order = "ASC";

//    LocalDateTime localDateTime = LocalDateTime.parse("2007-12-03T10:15:30");

    public Filter(String category, double moneyFrom, double moneyTo, String dateFrom, String dateTo, String order) {
        if (!category.equals(null)) {
            this.category = category;
        }

        if (moneyFrom >= 0d) {
            this.sumFrom = moneyFrom;
        }

        if (moneyTo < Double.MAX_VALUE) {
            this.sumTo = moneyTo;
        }

        if (dateFrom != null) {
            this.dateFrom = LocalDateTime.parse(dateFrom);
        }

        if (dateTo != null) {
            this.dateTo = LocalDateTime.parse(dateTo);
        }

        if (order != null) {
            if (order.equals("DESC")) {
                this.order = order;
            }
        }
    }

    public String getCategory() {
        return category;
    }

    public double getSumFrom() {
        return sumFrom;
    }

    public double getSumTo() {
        return sumTo;
    }

    public LocalDateTime getDateFrom() {
        return dateFrom;
    }

    public LocalDateTime getDateTo() {
        return dateTo;
    }

    public String getOrder() {
        return order;
    }
}
