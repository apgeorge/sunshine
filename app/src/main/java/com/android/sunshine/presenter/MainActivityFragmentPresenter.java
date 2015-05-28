package com.android.sunshine.presenter;

import com.android.sunshine.app.IMainView;
import com.android.sunshine.app.IWeatherFetcherTask;
import com.android.sunshine.service.WeatherService;

import java.util.List;

public class MainActivityFragmentPresenter implements IPresenter {
    private final WeatherService weatherService;
    private final IWeatherFetcherTask weatherFetcherTask;
    private IMainView view;

    public MainActivityFragmentPresenter(IMainView view, WeatherService weatherService, IWeatherFetcherTask weatherFetcherTask) {
        this.view = view;
        this.weatherService = weatherService;
        this.weatherFetcherTask = weatherFetcherTask;
    }

    @Override
    public void initialize() {
        weatherFetcherTask.doExecute(this);
    }

    @Override
    public void getWeather() {
        List<String> weatherData = weatherService.getWeatherData();
        view.showWeather(weatherData);
    }

}
