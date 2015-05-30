package com.android.sunshine.presenter;

import android.content.Context;

import com.android.sunshine.app.IWeatherFetcherTask;

import java.util.List;

public interface IPresenter {
    void initialize(Context context);

    void fetchWeather(IWeatherFetcherTask weatherFetcherTask, String zip);

    List<String> getWeather(String zip);

    void updateView(List<String> weatherData);

    void selectDay(int day);
}
