package com.android.sunshine.service;

import com.android.sunshine.common.AppLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import util.Util;

public class ApiClient implements IApiClient {
    private static final String LOG_TAG = ApiClient.class.getSimpleName();
    private AppLogger logger = AppLogger.getLogger();

    @Override
    public String doGet(String baseURL, HashMap<String, String> urlParams) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        String url = Util.urlMaker().getUrl(baseURL, urlParams);

        String response;
        try {

            logger.logVerbose(LOG_TAG, url);

            urlConnection = createConnection(new URL(url), "GET");

            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            response = buffer.toString();
            logger.logVerbose(LOG_TAG, response);
        } catch (IOException e) {
            logger.logError(LOG_TAG, "Error ", e);
            // If the code didn't successfully get the weather data, there's no point in attemping
            // to parse it.
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    logger.logError(LOG_TAG, "Error closing stream", e);
                }
            }
        }
        return response;
    }

    private static HttpURLConnection createConnection(URL url, String method) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod(method);
        return urlConnection;
    }
}
