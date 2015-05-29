package com.android.sunshine.service;

import java.util.Arrays;
import java.util.List;

public class WeatherService {

    public static final String GET_WEATHER_FOR_ZIP = "http://api.openweathermap.org/data/2.5/forecast/daily?q=94043&mode=json&units=metric&cnt=7";
    private IApiClient apiClient;

    public WeatherService(IApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public List<String> getWeatherData() {
        String response = apiClient.doGet(GET_WEATHER_FOR_ZIP);
        return Arrays.asList(response);
    }

}
