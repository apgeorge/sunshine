package com.android.sunshine.service;

import com.android.sunshine.apiclient.IApiClient;
import com.android.sunshine.model.WeatherForecastFactory;
import com.android.sunshine.util.Util;
import com.android.sunshine.util.testutils.MockClock;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class WeatherServiceTest {

    public static final String ZIP = "94043";
    private WeatherService weatherService;
    private IApiClient mockClient;
    private MockClock mockClock;
    private WeatherForecastFactory weatherForecastFactory;

    @Before
    public void setUp() throws Exception {
        setupMockClock();
        mockClient = mock(IApiClient.class);
        weatherForecastFactory = mock(WeatherForecastFactory.class);
        weatherService = new WeatherService(mockClient, weatherForecastFactory);
    }

    private void setupMockClock() {
        mockClock = new MockClock();
        mockClock.setJulianDayForCurrentTime(2457172);
        Util.setClock(mockClock);
    }

    @Test
    public void testGetWeatherData() throws Exception {
        weatherService.getWeatherData(ZIP);

        verify(mockClient).doGet("http://api.openweathermap.org/data/2.5/forecast/daily?", getExpectedUrlParams());
    }

    @Test
    public void shouldCreateWeatherForecastFromResponse() throws Exception {
        String expectedJsonResponse = "some response";
        when(mockClient.doGet("http://api.openweathermap.org/data/2.5/forecast/daily?", getExpectedUrlParams())).thenReturn(expectedJsonResponse);

        weatherService.getWeatherData(ZIP);

        verify(weatherForecastFactory).createWeatherForecast(expectedJsonResponse);
    }

    private HashMap<String, String> getExpectedUrlParams() {
        HashMap<String, String> urlParams = new HashMap<>();
        urlParams.put("cnt", "7");
        urlParams.put("q", ZIP);
        urlParams.put("units", "metric");
        urlParams.put("mode", "json");
        return urlParams;
    }
}