package com.android.sunshine.model;

import com.android.sunshine.datasource.DataSourceException;
import com.android.sunshine.datasource.JSONDataSource;

import org.json.JSONException;

public class WeatherForecastFactory {
    public WeatherForecast createWeatherForecast(String response) throws DataSourceException, JSONException {
        return new WeatherForecast(new JSONDataSource(response));
    }
}
