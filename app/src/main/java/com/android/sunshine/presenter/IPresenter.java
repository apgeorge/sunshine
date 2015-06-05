package com.android.sunshine.presenter;

public interface IPresenter {
    void initialize();

    void fetchWeather();

    void selectDay(int day);

}
