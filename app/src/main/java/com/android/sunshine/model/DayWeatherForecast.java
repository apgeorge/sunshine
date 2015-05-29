package com.android.sunshine.model;

public class DayWeatherForecast {

    private IDataSource dataSource;

    public DayWeatherForecast(IDataSource dataSource) {

        this.dataSource = dataSource;
    }

    public double getMin() throws DataSourceException {
        return dataSource.getObject("temp").getDouble("min");
    }

    public double getMax() throws DataSourceException {
        return dataSource.getObject("temp").getDouble("max");
    }

    public String getMain() throws DataSourceException {
        return dataSource.getArrayObject("weather").get(0).getString("main");
    }

}
