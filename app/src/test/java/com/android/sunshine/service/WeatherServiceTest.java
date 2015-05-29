package com.android.sunshine.service;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class WeatherServiceTest {

    private WeatherService weatherService;
    private IApiClient mockClient;

    @Before
    public void setUp() throws Exception {
        mockClient = mock(IApiClient.class);
        weatherService = new WeatherService(mockClient);
    }

    @Test
    public void testGetWeatherData() throws Exception {
        List<String> weatherData = weatherService.getWeatherData("94043");

        verify(mockClient).doGet("http://api.openweathermap.org/data/2.5/forecast/daily?", getExpectedUrlParams());
    }

    private HashMap<String, String> getExpectedUrlParams() {
        HashMap<String, String> urlParams = new HashMap<>();
        urlParams.put("cnt", "7");
        urlParams.put("q", "94043");
        urlParams.put("units", "metric");
        urlParams.put("mode", "json");
        return urlParams;
    }
}