package com.android.sunshine.model;

import java.util.ArrayList;
import java.util.List;

public class WeatherForecast {

    private final IDataSource weatherJson;
    private final List<DayWeatherForecast> dayWeatherForecasts;

    public WeatherForecast(IDataSource dataSource) throws DataSourceException {
        this.weatherJson = dataSource;
        dayWeatherForecasts = getDayWeatherForecasts();
    }

    public String getCity() throws DataSourceException {
        return weatherJson.getObject("city").getString("name");
    }

    public List<DayWeatherForecast> getDays() throws DataSourceException {
        return dayWeatherForecasts;
    }

    private List<DayWeatherForecast> getDayWeatherForecasts() throws DataSourceException {
        List<DayWeatherForecast> dayWeatherForecasts = new ArrayList<>();
        List<IDataSource> list = weatherJson.getArrayObject("list");
        for (IDataSource each : list) {
            dayWeatherForecasts.add(new DayWeatherForecast(each));
        }
        return dayWeatherForecasts;
    }

    public double getMaxForDay(int dayIndex) throws DataSourceException {
        return getDays().get(dayIndex).getMax();
    }

    public double getMinForDay(int dayIndex) throws DataSourceException {
        return getDays().get(dayIndex).getMin();
    }
}
