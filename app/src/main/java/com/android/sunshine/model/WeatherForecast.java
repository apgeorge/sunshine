package com.android.sunshine.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class WeatherForecast {

    private final JSONObject weatherJson;

    public WeatherForecast(String weatherJson) throws JSONException {
        this.weatherJson = new JSONObject(weatherJson);
    }

    public String getCity() throws JSONException {
        return weatherJson.getJSONObject("city").getString("name");
    }

    public List<DayWeatherForecast> getDays(){
        return null;
    }
}
