package ru.spring.demo.Objects;

public class Filter {
    private String category = "";
    private double moneyFrom = 0d;
    private double moneyTo = Double.MAX_VALUE;
    private String dateFrom = "2000-01-01";
    private String dateTo = new java.util.Date().toString();

    public Filter(String category, double moneyFrom, double moneyTo, String dateFrom, String dateTo) {
        if (!category.equals(null)) {
            this.category = category;
        }

        if (moneyFrom >= 0d) {
            this.moneyFrom = moneyFrom;
        }

        if (moneyTo < Double.MAX_VALUE) {
            this.moneyTo = moneyTo;
        }

        if (!dateFrom.equals(null)) {
            this.dateFrom = dateFrom;
        }

        if (!dateTo.equals(null)) {
            this.dateTo = dateTo;
        }
    }

    public String getCategory() {
        return category;
    }

    public double getMoneyFrom() {
        return moneyFrom;
    }

    public double getMoneyTo() {
        return moneyTo;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }
}
