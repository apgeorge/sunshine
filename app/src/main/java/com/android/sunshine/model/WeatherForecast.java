package com.android.sunshine.model;

import com.android.sunshine.datasource.DataSourceException;
import com.android.sunshine.datasource.IDataSource;
import com.android.sunshine.util.Util;

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
        int julianStartDay = Util.getClock().getJulianDayForCurrentTime();

        List<DayWeatherForecast> dayWeatherForecasts = new ArrayList<>();
        List<IDataSource> days = weatherJson.getArrayObject("list");
        for (IDataSource day : days) {
            int julianDay = julianStartDay++;
            dayWeatherForecasts.add(new DayWeatherForecast(day, Util.getClock().getOffsetFromEpochForJulianDay(julianDay)));
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
