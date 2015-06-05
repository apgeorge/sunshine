package com.android.sunshine.presenter;

import com.android.sunshine.app.ICommand;

import java.util.List;

public interface IPresenter {
    void initialize();

    void fetchWeather();

    List<String> getWeather(String zip);

    void updateView(List<String> weatherData);

    void selectDay(int day);

}
