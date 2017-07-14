package com.example.deezy.googlebooksearch;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.R.string.no;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;
import static com.example.deezy.googlebooksearch.R.id.pages;


public final class QueryUtils {


    private QueryUtils() {
    }


    public static final String LOG_TAG = QueryUtils.class.getSimpleName();


    public static List<Book> fetchBookData(String requestUrl) {

        URL url = createUrl(requestUrl);

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }
        List<Book> books = extractBooks(jsonResponse);

        return books;
    }


    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating URL ", e);
        }
        return url;
    }


    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();


            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }


    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    public static List<Book> extractBooks(String bookJSON) {
        if (TextUtils.isEmpty(bookJSON)) {
            return null;
        }

        List<Book> books = new ArrayList<Book>();
        String authorsStr = "";
        JSONArray items;

        try {
            JSONObject root = new JSONObject(bookJSON);
            try {
                items = root.getJSONArray("items");
            } catch (JSONException e) {
                Log.e(LOG_TAG, "No item available", e);
                items = null;
            }
            
            for (int i = 0; i < items.length(); i++) {
                String title;
                String subtitle;
                String publisher;
                String publishedDate;
                int pages;
                String rating;
                String description;

                JSONObject it = items.getJSONObject(i).getJSONObject("volumeInfo");
                try {
                    title = it.getString("title");
                } catch (JSONException e) {
                    Log.e(LOG_TAG, "no title available", e);
                    title = "No title available";
                }
                try {
                    subtitle = it.getString("subtitle");
                } catch (JSONException e) {
                    Log.e(LOG_TAG, "No subtitle available", e);
                    subtitle = "No subtitle available";
                }
                try {
                    publisher = it.getString("publisher");
                } catch (JSONException e) {
                    Log.e(LOG_TAG, "No publisher available", e);
                    publisher = "No publisher available";
                }
                JSONArray authors = it.getJSONArray("authors");
                authorsStr = "";
                for (int j = 0; j < authors.length(); j++) {
                    if (authorsStr.isEmpty()) {
                        authorsStr = authors.getString(j);
                    } else {
                        authorsStr = authorsStr + ", " + authors.getString(j);
                    }
                }
                try {
                    publishedDate = it.getString("publishedDate");
                } catch (JSONException e) {
                    Log.e(LOG_TAG, "No date available", e);
                    publishedDate = "No date available";
                }
                try {
                    pages = it.getInt("pageCount");
                } catch (JSONException e) {
                    Log.e(LOG_TAG, "no pageCount available", e);
                    pages = 000;
                }
                try {
                    rating = it.getString("averageRating");
                } catch (JSONException e) {
                    Log.e(LOG_TAG, "no rating available");
                    rating = "No rating available";
                }
                try {
                    description = it.getString("description");
                } catch (JSONException e) {
                    Log.e(LOG_TAG, "no description available");
                    description = "No description available";
                }
                books.add(new Book(title, subtitle, authorsStr, publisher, publishedDate, pages, rating, description));
            }
        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the book JSON results", e);
        }
        return books;
    }
}