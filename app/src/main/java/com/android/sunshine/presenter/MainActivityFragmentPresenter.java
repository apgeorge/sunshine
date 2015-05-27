package com.android.sunshine.presenter;

import com.android.sunshine.app.IMainView;
import com.android.sunshine.service.WeatherService;

import java.util.List;

public class MainActivityFragmentPresenter implements IPresenter {
    private final WeatherService weatherService;
    private IMainView view;

    public MainActivityFragmentPresenter(IMainView view, WeatherService weatherService) {
        this.view = view;
        this.weatherService = weatherService;
    }

    @Override
    public void initialize() {
        List<String> weatherData = weatherService.getWeatherData();
        view.showWeather(weatherData);
    }
}
