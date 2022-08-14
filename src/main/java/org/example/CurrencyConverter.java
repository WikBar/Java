package org.example;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.*;

public class CurrencyConverter implements UserController {
    private double exchangedValue = 0;
    private double value;
    private double currencyTo;
    private double currencyFrom;

    private List<JsonNode> jsonRates=new ArrayList<>();
    ReadJson readJson;
    static Scanner scanner = new Scanner(System.in);
    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }
    String Code="";
    public double getExchangedValue() {
        return exchangedValue;
    }

    public void setExchangedValue(double exchangedValue) {
        this.exchangedValue = exchangedValue;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getCurrencyTo() {
        return currencyTo;
    }

    public void setCurrencyTo(double currencyTo) {
        this.currencyTo = currencyTo;
    }

    public double getCurrencyFrom() {
        return currencyFrom;
    }

    public void setCurrencyFrom(double currencyFrom) {
        this.currencyFrom = currencyFrom;
    }

    public CurrencyConverter(ReadJson readJson) {
        this.readJson = readJson;
    }// Use without internet

    public CurrencyConverter(List<JsonNode> JSONRates){this.jsonRates=JSONRates;} // Use with Internet

    public void inPLN() {

    setValue(getValueCurrency("Podaj kwote do wymiany w swojej walucie"));
    setCurrencyTo(getValueByCode("Podaj kod waluty na ktora wymieniasz"));
    System.out.println("Wpisana przez ciebie kwota to " + getValue() + " PLN");

    setExchangedValue(getValue() / getCurrencyTo());

    System.out.println(getValue() + " in PLN -> " + getCurrencyTo() + " : "
            + String.format("%,.2f", getExchangedValue()));
    }

    public void InOtherCurrency()  {

        setValue(getValueCurrency("Podaj kwote do wymiany w swojej walucie"));
        setCurrencyFrom(getValueByCode("podaj kod twojej waluty "));
        System.out.println("Wpisana przez ciebie kwota to " + getValue() + " " + getCode());
        setCurrencyTo(getValueByCode("podaj kod waluty na ktora wymieniasz"));


        setExchangedValue(getValue() /( getCurrencyFrom() / getCurrencyTo()));
        System.out.println(getValue() +
                " in " + getCurrencyFrom() +
                " -> " + getCurrencyTo() + " : " +
                String.format("%,.2f", getExchangedValue())+
                " "+getCode());

    }

    @Override
    public Double getValueCurrency(String message) {
    setValue(0);
        while (this.value <= 0) {
            System.out.println(message);
            try {


                value = scanner.nextDouble();

            } catch (InputMismatchException exception) {
                System.out.println("Prosze podac liczbe");
            }

        }
        return value;
    }

    @Override
    public double getValueByCode(String message) {
    setCode("");
        while (this.readJson.getValueByCode(getCode())<=0) {
            System.out.println(message);
            try {
                setCode(scanner.next());

            } catch (InputMismatchException e) {
                System.out.println("Brak w bazie. Prosze podac kod jeszcze raz");
            }
        }

        return this.readJson.getValueByCode(getCode());
    }
}
