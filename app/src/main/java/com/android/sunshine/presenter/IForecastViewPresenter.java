package com.android.sunshine.presenter;

public interface IForecastViewPresenter {
    void initialize();

    void fetchWeather();

    void selectDay(int day);

}
