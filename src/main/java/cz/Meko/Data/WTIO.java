package cz.Meko.Data;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WTIO {
    public void setAtribute(String targetUrl){


        // 2. Open a basic connection to the URL
        URL url = null;
        try {
            url = new URL(targetUrl);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 3. Set the disguise and method
        try {
            connection.setRequestMethod("GET");
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        }
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) Chrome/120.0.0.0");
        connection.setConnectTimeout(1000); // Only wait 1 second to connect
        connection.setReadTimeout(1000);    // Don't wait around for a response

        // 4. Actually fire the request!
        // We use getResponseCode(), but we wrap it in a try-catch so we can ignore the "no bytes" error
        try {
            connection.getResponseCode();
        } catch (java.io.IOException e) {
            // We expect this! The server drops the connection, so we do nothing here.
        }

        System.out.println("Fired command to: " + targetUrl);

        // 5. Clean up the connection
        connection.disconnect();

    }

    public Data getTelemetry() {
        try {
            URL url = new URL("http://127.0.0.1:8111/indicators");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) Chrome/120.0.0.0");

            if (connection.getResponseCode() == 200) {
                ObjectMapper objectMapper = new ObjectMapper();
                Data data = objectMapper.readValue(connection.getInputStream(), Data.class);
                return data;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
