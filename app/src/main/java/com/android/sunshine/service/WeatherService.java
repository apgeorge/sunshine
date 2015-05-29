package com.android.sunshine.service;

import java.util.Arrays;
import java.util.List;

public class WeatherService {

    public static final String GET_WEATHER_BY_ZIP = "http://api.openweathermap.org/data/2.5/forecast/daily?q=%s&mode=json&units=metric&cnt=7";
    private IApiClient apiClient;

    public WeatherService(IApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public List<String> getWeatherData(String zip) {
        String response = apiClient.doGet(String.format(GET_WEATHER_BY_ZIP, zip));
        return Arrays.asList(response);
    }

}
