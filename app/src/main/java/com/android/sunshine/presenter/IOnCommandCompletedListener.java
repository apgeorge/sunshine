package com.android.sunshine.presenter;

import com.android.sunshine.model.WeatherForecast;

public interface IOnCommandCompletedListener {
    void OnCommandComplete(WeatherForecast weatherData);
}
