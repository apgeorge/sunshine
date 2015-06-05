package com.android.sunshine.presenter;

import com.android.sunshine.datasource.DataSourceException;
import com.android.sunshine.model.DayWeatherForecast;
import com.android.sunshine.model.WeatherForecast;

import java.util.ArrayList;

public class ForecastViewModel {
    private final ArrayList<String> forecast;
    private WeatherForecast weatherData;

    public ForecastViewModel(WeatherForecast weatherData) {
        this.weatherData = weatherData;
        forecast = format();
    }

    ArrayList<String> format() {
        ArrayList<String> forecasts = new ArrayList<>();
        try {
            for (DayWeatherForecast day : weatherData.getDays()) {
                forecasts.add(String.format("%s - %s - %s/%s", day.getDay(), day.getMain(), Math.round(day.getMax()), Math.round(day.getMin())));
            }
        } catch (DataSourceException e) {
            e.printStackTrace();
        }
        return forecasts;
    }

    public ArrayList<String> getForecast() {
        return forecast;
    }

}
