package com.android.sunshine.command;

import com.android.sunshine.model.WeatherForecast;

public interface IOnCommandCompletedListener {
    void OnCommandComplete(WeatherForecast weatherData);
}
