package com.aron.currencyconverter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Mostrar un mensaje de bienvenida
        System.out.println("========================================");
        System.out.println("     Sea bienvenido/a al Conversor de moneda     ");
        System.out.println("========================================\n\n");

        // Mostrar las opciones de conversión
        System.out.println("1.- Dólar (USD) => Peso Argentino (ARS)");
        System.out.println("2.- Euro (EUR) => Dólar (USD)");
        System.out.println("3.- Libra Esterlina (GBP) => Euro (EUR)");
        System.out.println("4.- Yen Japonés (JPY) => Dólar (USD)");
        System.out.println("5.- Dólar Canadiense (CAD) => Euro (EUR)");
        System.out.println("6.- Peso Mexicano (MXN) => Dólar (USD)");
        System.out.println("7.- Dólar (USD) => Sol Peruano (PEN)");
        System.out.println("8.- Elegir un par de monedas personalizado");
        System.out.println("========================================\n");

        // Pedir al usuario que elija una opción
        System.out.print("Elija una opción: ");
        int opcion = scanner.nextInt();

        String baseCurrency = "";
        String targetCurrency = "";

        switch (opcion) {
            case 1:
                baseCurrency = "USD";
                targetCurrency = "ARS";
                break;
            case 2:
                baseCurrency = "EUR";
                targetCurrency = "USD";
                break;
            case 3:
                baseCurrency = "GBP";
                targetCurrency = "EUR";
                break;
            case 4:
                baseCurrency = "JPY";
                targetCurrency = "USD";
                break;
            case 5:
                baseCurrency = "CAD";
                targetCurrency = "EUR";
                break;
            case 6:
                baseCurrency = "MXN";
                targetCurrency = "USD";
                break;
            case 7:
                baseCurrency = "USD";
                targetCurrency = "PEN"; // Sol Peruano
                break;
            case 8:
                System.out.print("Introduce la moneda base (ejemplo USD): ");
                baseCurrency = scanner.next().toUpperCase();
                System.out.print("Introduce la moneda objetivo (ejemplo EUR): ");
                targetCurrency = scanner.next().toUpperCase();
                break;
            default:
                System.out.println("Opción no válida. Saliendo del programa...");
                System.exit(0);
        }

        // Aquí deberías hacer la llamada al servicio de API para obtener las tasas de cambio
        System.out.println("\n\n========================================");
        System.out.println("       Resultado de la Conversión       ");
        System.out.println("========================================");

        String apiKey = "aa7c69634ea0f36441372c1a"; // Tu API key
        ApiService apiService = new ApiService(apiKey);
        double rate = apiService.getExchangeRate(baseCurrency, targetCurrency);

        if (rate == 0.0) {
            System.out.println("Error al obtener la tasa de cambio. Inténtalo de nuevo.");
        } else {
            System.out.print("Introduce la cantidad a convertir (" + baseCurrency + "): ");
            double amount = scanner.nextDouble();

            // Convertir la moneda
            CurrencyConverter converter = new CurrencyConverter(rate);
            double result = converter.convert(amount);

            // Mostrar el resultado
            System.out.println(amount + " " + baseCurrency + " es igual a " + result + " " + targetCurrency);
        }

        System.out.println("========================================");
        System.out.println("       ¡Gracias por usar nuestro servicio!      ");
        System.out.println("========================================");

        scanner.close();
    }
}
