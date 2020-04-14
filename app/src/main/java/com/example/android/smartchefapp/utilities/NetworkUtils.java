package com.example.android.smartchefapp.utilities;

import android.net.Uri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public final class NetworkUtils {

    private final static String SPOONACULAR_BASE_URL = "https://api.spoonacular.com/recipes/search";

    private final static String QUERY_PARAM = "query";
    private final static String NUMBER_PARAM = "number";
    private final static String API_PARAM = "apiKey"; // ?apiKey=11e4e265681f4c25ba36274fa364298e

    // query (comes as a method parameter)
    private final static String number = "10";
    private final static String API_KEY = "11e4e265681f4c25ba36274fa364298e";



    public static URL buildUrl(String query) {
        Uri builtUri = Uri.parse(SPOONACULAR_BASE_URL).buildUpon() // Constructs a new builder, copying the attributes from this Uri.
                .appendQueryParameter(API_PARAM, API_KEY)
                .appendQueryParameter(QUERY_PARAM, query)
                .appendQueryParameter(NUMBER_PARAM, number)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {

        InputStream in = null;
        HttpsURLConnection urlConnection = null;
        String jsonResponse = "";

        try {
            // 1. Declare a URL Connection
            urlConnection = (HttpsURLConnection) url.openConnection();

            urlConnection.setReadTimeout(3000);
            urlConnection.setConnectTimeout(3000);
            urlConnection.setRequestMethod("GET");

            // 2. Open InputStream to connection
            urlConnection.connect(); // Open communications link (network traffic occurs here).

            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                in = urlConnection.getInputStream(); // Retrieve the response body as an InputStream.

                if (in != null) {
                    // 3. Download and decode the string response using builder
                    StringBuilder output = new StringBuilder();
                    InputStreamReader inReader = new InputStreamReader(in);
                    BufferedReader reader = new BufferedReader(inReader);

                    String line = reader.readLine();
                    while (line != null) {
                        output.append(line);
                        line = reader.readLine();
                    }
                    jsonResponse = output.toString();
                }
            }
        } finally {
            urlConnection.disconnect();
        }
        return jsonResponse;
    }

}
