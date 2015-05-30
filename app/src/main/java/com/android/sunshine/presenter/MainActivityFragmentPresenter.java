package com.android.sunshine.presenter;

import com.android.sunshine.app.IMainView;
import com.android.sunshine.app.IWeatherFetcherTask;
import com.android.sunshine.model.DataSourceException;
import com.android.sunshine.model.DayWeatherForecast;
import com.android.sunshine.model.WeatherForecast;
import com.android.sunshine.service.WeatherService;

import org.json.JSONException;

import java.util.ArrayList;
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
        ArrayList<String> forecasts = null;
        try {
            WeatherForecast weatherData = weatherService.getWeatherData(zip);
            forecasts = getFormattedWeatherData(weatherData);
        } catch (DataSourceException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return forecasts;
    }

    private ArrayList<String> getFormattedWeatherData(WeatherForecast weatherData) {
        ArrayList<String> forecasts = new ArrayList<>();

        try {
            for (DayWeatherForecast day : weatherData.getDays()) {
                forecasts.add(String.format("%s - %s - %s/%s", day.getDay(), day.getMain(), Math.round(day.getMax()), Math.round(day.getMin())));
            }
        } catch (DataSourceException e) {
            e.printStackTrace();
        }
        return forecasts;
    }

    @Override
    public void updateView(List<String> weatherData) {
        view.showWeather(weatherData);
    }

}
