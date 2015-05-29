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
        fetchWeather(this.weatherFetcherTask, "94043");
    }

    @Override
    public void fetchWeather(IWeatherFetcherTask weatherFetcherTask, String zip) {
        weatherFetcherTask.doExecute(this, zip);
    }

    @Override
    public List<String> getWeather(String zip) {
        return weatherService.getWeatherData(zip);
    }

    @Override
    public void updateView(List<String> weatherData) {
        view.showWeather(weatherData);
    }

}
