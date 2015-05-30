package com.android.sunshine.model;

import org.json.JSONException;

public class WeatherForecastFactory {
    public  WeatherForecast createWeatherForecast(String response) throws DataSourceException, JSONException {
        return new WeatherForecast(new JSONDataSource(response));
    }
}
