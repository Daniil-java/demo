package ru.spring.demo.Objects;

import java.util.Map;

public class Money {

    private String Date;
    private String PreviousDate;
    private String PreviousURL;
    private String Timestamp;
    private Map<String, Currency> Valute;

    public Money(String date, String previousDate, String previousURL, String timestamp, Map<String, Currency> valute) {
        Date = date;
        PreviousDate = previousDate;
        PreviousURL = previousURL;
        Timestamp = timestamp;
        Valute = valute;
    }

    public String getDate() {
        return Date;
    }

    public String getPreviousDate() {
        return PreviousDate;
    }

    public String getPreviousURL() {
        return PreviousURL;
    }

    public String getTimestamp() {
        return Timestamp;
    }

    public Map<String, Currency> getValute() {
        return Valute;
    }
}
