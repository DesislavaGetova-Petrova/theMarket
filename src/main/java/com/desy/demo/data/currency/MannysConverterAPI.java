package com.desy.demo.data.currency;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Currency;
import java.util.Scanner;
@Component
public class MannysConverterAPI {
    private static final String API_KEY
            = "41445f662f7c9eabd706";
    private static final String USER_AGENT_ID = "Java/"
            + System.getProperty("java.version");

    public double rate(Currency from, Currency to)
            throws IOException {
        String queryPath
                = "https://free.currconv.com/api/v7/convert?q="
                + from.getCurrencyCode() + "_"
                + to.getCurrencyCode()
                + "&compact=ultra&apiKey=" + API_KEY;
        URL queryURL = new URL(queryPath);
        HttpURLConnection connection
                = (HttpURLConnection) queryURL.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", USER_AGENT_ID);
        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            InputStream stream
                    = (InputStream) connection.getContent();
            Scanner scanner = new Scanner(stream);
            String quote = scanner.nextLine();
            String number = quote.substring(quote.indexOf(':') + 1,
                    quote.indexOf('}'));
            return Double.parseDouble(number);
        } else {
            String excMsg = "Query " + queryPath
                    + " returned status " + responseCode;
            throw new RuntimeException(excMsg);
        }
    }
}
