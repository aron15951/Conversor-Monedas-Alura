package com.aron.currencyconverter;

import com.google.gson.Gson;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ApiService {
    private final String apiKey;

    public ApiService(String apiKey) {
        this.apiKey = apiKey;
    }

    public double getExchangeRate(String baseCurrency, String targetCurrency) {
        try {
            String urlStr = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + baseCurrency;
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            InputStreamReader reader = new InputStreamReader(connection.getInputStream());
            ExchangeRatesResponse response = new Gson().fromJson(reader, ExchangeRatesResponse.class);

            return response.conversion_rates.get(targetCurrency);
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    private class ExchangeRatesResponse {
        String result;
        String base_code;
        java.util.Map<String, Double> conversion_rates;
    }
}
