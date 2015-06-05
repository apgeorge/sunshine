package com.android.sunshine.presenter;

import android.content.Context;

import com.android.sunshine.app.ForecastView;
import com.android.sunshine.app.UserPreferences;
import com.android.sunshine.app.factory.IntentFactory;
import com.android.sunshine.command.CommandFactory;
import com.android.sunshine.command.AsyncCommand;
import com.android.sunshine.command.OnCommandCompletedListener;
import com.android.sunshine.datasource.DataSourceException;
import com.android.sunshine.model.DayWeatherForecast;
import com.android.sunshine.model.WeatherForecast;

import java.util.ArrayList;

public class ForecastViewPresenter implements IForecastViewPresenter {
    private final IntentFactory intentFactory;
    private final CommandFactory commandFactory;
    private final UserPreferences userPreferences;
    private final Context context;
    private final ForecastView view;
    private WeatherForecast weatherData;
    private ArrayList<String> forecasts;

    public ForecastViewPresenter(ForecastView view, IntentFactory intentFactory, UserPreferences userPreferences, Context context, CommandFactory commandFactory) {
        this.view = view;
        this.intentFactory = intentFactory;
        this.userPreferences = userPreferences;
        this.context = context;
        this.commandFactory = commandFactory;
    }

    @Override
    public void fetchWeather() {
        AsyncCommand weatherFetcherTask = commandFactory.createWeatherFetcherCommand();
        weatherFetcherTask.setOnCompletedListener(new OnFetchWeatherCompletedListerner());
        weatherFetcherTask.doExecute(userPreferences.getZip());
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
    public void selectDay(int day) {
        view.launchDetail(intentFactory.createDetailActivityIntent(context, forecasts.get(day)));
    }

    private class OnFetchWeatherCompletedListerner implements OnCommandCompletedListener {
        @Override
        public void OnCommandComplete(WeatherForecast forecast) {
            weatherData = forecast;
            forecasts = getFormattedWeatherData(weatherData);
            view.showWeather(forecasts);
        }
    }
}
