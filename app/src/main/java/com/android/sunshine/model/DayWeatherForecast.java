package com.android.sunshine.model;

import java.text.SimpleDateFormat;

public class DayWeatherForecast {

    private final IDataSource temp;
    private final String dateString;
    private IDataSource dataSource;

    public DayWeatherForecast(IDataSource dataSource, long dateTime) throws DataSourceException {
        this.dataSource = dataSource;
        this.temp = dataSource.getObject("temp");
        dateString = getReadableDateString(dateTime);
    }

    public double getMin() throws DataSourceException {
        return temp.getDouble("min");
    }

    public String getDay() {
        return dateString;
    }

    public double getMax() throws DataSourceException {
        return temp.getDouble("max");
    }

    public String getMain() throws DataSourceException {
        return dataSource.getArrayObject("weather").get(0).getString("main");
    }

    private String getReadableDateString(long time){
        SimpleDateFormat shortenedDateFormat = new SimpleDateFormat("EEE MMM dd");
        return shortenedDateFormat.format(time);
    }

}
