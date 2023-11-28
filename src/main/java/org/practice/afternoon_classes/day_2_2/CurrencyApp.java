package org.practice.afternoon_classes.day_2_2;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CurrencyApp {

    public static void main(String[] args) {
        Utils utils = new Utils();
        double exchangeSum = utils.getDoubleInput("Please enter sum for exchange in USD: ");
        String currency = utils.printAndReadOutput("Enter currency to exchange to from following: " + utils.getCurrencies());
        if(utils.validateCurrencyInput(currency)) {
            try {
                double result = getInCurrency(exchangeSum, currency);
                System.out.println("Exchanged sum: " + result + " " + currency);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else System.out.println("Invalid currency");
    }

    public static double getInCurrency(double sum, String exchangeCur) throws IOException {
        String key = System.getenv("apiKey");
        String url_str = "https://api.freecurrencyapi.com/v1/latest?apikey="+ key +
                "&currencies=" + exchangeCur;
        URL url = new URL(url_str);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();

        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject jsonb = root.getAsJsonObject();
        double exchangeRate = jsonb.getAsJsonObject("data").get(exchangeCur).getAsDouble();
        System.out.println("Exchange rate is " + exchangeRate);
        return sum*exchangeRate;
    }
}
