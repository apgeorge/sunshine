package com.android.sunshine.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class WeatherService {

    public static final String FORMAT = "json";
    public static final String UNIT = "metric";
    public static final int COUNT = 7;
    public static final String BASE_URL = "http://api.openweathermap.org/data/2.5/forecast/daily?";
    public static final String QUERY_PARAM = "q";
    public static final String MODE_PARAM = "mode";
    public static final String UNITS_PARAM = "units";
    public static final String CNT_PARAM = "cnt";
    private IApiClient apiClient;

    public WeatherService(IApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public List<String> getWeatherData(String zip) {
        HashMap<String, String> urlParams = new HashMap<>();
        urlParams.put(QUERY_PARAM, zip);
        urlParams.put(MODE_PARAM, FORMAT);
        urlParams.put(UNITS_PARAM, UNIT);
        urlParams.put(CNT_PARAM, String.valueOf(COUNT));
        String response = apiClient.doGet(BASE_URL, urlParams);
        return Arrays.asList(response);
    }

}