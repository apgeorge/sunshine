package com.android.sunshine.command;

import com.android.sunshine.model.WeatherForecast;

public interface OnCommandCompletedListener {
    void OnCommandComplete(WeatherForecast weatherData);
}
