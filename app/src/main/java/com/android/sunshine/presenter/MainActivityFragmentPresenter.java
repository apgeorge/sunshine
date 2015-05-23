package com.android.sunshine.presenter;

import com.android.sunshine.app.IMainView;
import com.android.sunshine.service.WeatherService;

import java.util.List;

public class MainActivityFragmentPresenter {
    private IMainView view;

    public MainActivityFragmentPresenter(IMainView view) {

        this.view = view;
    }

    public List<String> initialize() {
        WeatherService weatherService = new WeatherService();
        List<String> weatherData = weatherService.getWeatherData();
        view.showWeather(weatherData);
        return weatherData;
    }
}
