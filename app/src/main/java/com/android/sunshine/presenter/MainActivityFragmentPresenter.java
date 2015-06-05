package com.android.sunshine.presenter;

import android.content.Context;

import com.android.sunshine.app.IMainView;
import com.android.sunshine.app.ICommand;
import com.android.sunshine.app.factory.IntentFactory;
import com.android.sunshine.common.UserPreferences;
import com.android.sunshine.model.DataSourceException;
import com.android.sunshine.model.DayWeatherForecast;
import com.android.sunshine.model.WeatherForecast;
import com.android.sunshine.service.WeatherService;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class MainActivityFragmentPresenter implements IPresenter {
    private final IntentFactory intentFactory;
    private final CommandFactory commandFactory;
    private UserPreferences userPreferences;
    private Context context;
    private final WeatherService weatherService;
    private IMainView view;
    private WeatherForecast weatherData;
    private ArrayList<String> forecasts;

    public MainActivityFragmentPresenter(IMainView view, WeatherService weatherService, IntentFactory intentFactory, UserPreferences userPreferences, Context context, CommandFactory commandFactory) {
        this.view = view;
        this.weatherService = weatherService;
        this.intentFactory = intentFactory;
        this.userPreferences = userPreferences;
        this.context = context;
        this.commandFactory = commandFactory;
    }

    @Override
    public void initialize() {
        fetchWeather();
    }

    @Override
    public void fetchWeather() {
        ICommand weatherFetcherTask = commandFactory.createWeatherFetcherCommand();
        weatherFetcherTask.doExecute(this, userPreferences.getZip());
    }

    @Override
    public List<String> getWeather(String zip) {
        ArrayList<String> forecasts = null;
        try {
            weatherData = weatherService.getWeatherData(zip);
            forecasts = getFormattedWeatherData(weatherData);
        } catch (DataSourceException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return forecasts;
    }

    private ArrayList<String> getFormattedWeatherData(WeatherForecast weatherData) {
        forecasts = new ArrayList<>();

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

    @Override
    public void selectDay(int day) {
        view.launchDetail(intentFactory.createDetailActivityIntent(context, forecasts.get(day)));
    }

}
