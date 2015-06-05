package com.android.sunshine.model;

import com.android.sunshine.model.DataSourceException;
import com.android.sunshine.model.JSONDataSource;
import com.android.sunshine.model.WeatherForecast;

import org.json.JSONException;

public class WeatherForecastFactory {
    public WeatherForecast createWeatherForecast(String response) throws DataSourceException, JSONException {
        return new WeatherForecast(new JSONDataSource(response));
    }
}
