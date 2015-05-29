package com.android.sunshine.model;

import org.json.JSONException;

import java.util.List;

public class WeatherForecast {

    private final IDataSource weatherJson;

    public WeatherForecast(IDataSource dataSource) throws JSONException {
        this.weatherJson = dataSource;
    }

    public String getCity() throws DataSourceException {
        return weatherJson.getObject("city").getString("name");
    }

    public List<DayWeatherForecast> getDays(){
        return null;
    }
}
