package com.android.sunshine.model;

import java.text.SimpleDateFormat;

public class DayWeatherForecast {

    private final IDataSource temp;
    private IDataSource dataSource;
    private long dateTime;

    public DayWeatherForecast(IDataSource dataSource, long dateTime) throws DataSourceException {
        this.dataSource = dataSource;
        this.dateTime = dateTime;
        this.temp = dataSource.getObject("temp");
    }

    public double getMin() throws DataSourceException {
        return temp.getDouble("min");
    }

    public String getDay() {
        return getReadableDateString(dateTime);
    }

    public double getMax() throws DataSourceException {
        return temp.getDouble("max");
    }

    public String getMain() throws DataSourceException {
        return dataSource.getArrayObject("weather").get(0).getString("main");
    }

    private String getReadableDateString(long time){
        SimpleDateFormat shortenedDateFormat = new SimpleDateFormat("EEE, MMM d");
        return shortenedDateFormat.format(time);
    }

}
