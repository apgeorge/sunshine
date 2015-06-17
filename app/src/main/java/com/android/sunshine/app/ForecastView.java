package com.android.sunshine.app;

import java.util.List;

public interface ForecastView {
    void showWeather(List<String> weatherData);

    void launchDetail(String data);
}
