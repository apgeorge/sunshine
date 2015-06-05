package com.android.sunshine.app;

import android.content.Intent;

import java.util.List;

public interface ForecastView {
    void showWeather(List<String> weatherData);

    void launchDetail(Intent detailActivityIntent);
}
