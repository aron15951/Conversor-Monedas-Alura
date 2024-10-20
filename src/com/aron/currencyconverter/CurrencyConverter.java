package com.aron.currencyconverter;

public class CurrencyConverter {
    private final double exchangeRate;

    public CurrencyConverter(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public double convert(double amount) {
        return amount * exchangeRate;
    }
}
