package ru.spring.demo.Objects;

public class Currency {
    private String ID;
    private String NumCode;
    private String CharCode;
    private String Nominal;
    private String Name;
    private String Value;
    private String Previous;

    public String getID() {
        return ID;
    }

    public String getNumCode() {
        return NumCode;
    }

    public String getCharCode() {
        return CharCode;
    }

    public String getNominal() {
        return Nominal;
    }

    public String getName() {
        return Name;
    }

    public String getValue() {
        return Value;
    }

    public String getPrevious() {
        return Previous;
    }

    public Currency(String ID, String numCode, String charCode, String nominal, String name, String value, String previous) {
        this.ID = ID;
        NumCode = numCode;
        CharCode = charCode;
        Nominal = nominal;
        Name = name;
        Value = value;
        Previous = previous;
    }
}
