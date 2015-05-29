package com.android.sunshine.model;

public class DayWeatherForecast {

    private final IDataSource temp;
    private IDataSource dataSource;

    public DayWeatherForecast(IDataSource dataSource) throws DataSourceException {
        this.dataSource = dataSource;
        this.temp = dataSource.getObject("temp");
    }

    public double getMin() throws DataSourceException {
        return temp.getDouble("min");
    }

    public double getMax() throws DataSourceException {
        return temp.getDouble("max");
    }

    public String getMain() throws DataSourceException {
        return dataSource.getArrayObject("weather").get(0).getString("main");
    }

}
