package com.android.sunshine.presenter;

import com.android.sunshine.app.IWeatherFetcherTask;

import java.util.List;

public interface IPresenter {
    void initialize();

    void fetchWeather(IWeatherFetcherTask weatherFetcherTask);

    List<String> getWeather(String zip);

    void updateView(List<String> weatherData);

    void selectDay(int day);

}
