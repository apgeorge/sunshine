package com.android.sunshine.presenter;

import android.content.Context;

import com.android.sunshine.app.ForecastView;
import com.android.sunshine.app.UserPreferences;
import com.android.sunshine.app.factory.IntentFactory;
import com.android.sunshine.command.AsyncCommand;
import com.android.sunshine.command.CommandFactory;
import com.android.sunshine.command.OnCommandCompletedListener;
import com.android.sunshine.model.WeatherForecast;

public class ForecastViewPresenter implements IForecastViewPresenter {
    private final IntentFactory intentFactory;
    private final CommandFactory commandFactory;
    private final UserPreferences userPreferences;
    private final Context context;
    private final ForecastView view;
    private ForecastViewModel forecastViewModel;

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
        weatherFetcherTask.setOnCompletedListener(new OnFetchWeatherCompletedListener());
        weatherFetcherTask.doExecute(userPreferences.getZip());
    }

    @Override
    public void selectDay(int day) {
        view.launchDetail(intentFactory.createDetailActivityIntent(context, forecastViewModel.getForecast().get(day)));
    }

    private class OnFetchWeatherCompletedListener implements OnCommandCompletedListener {
        @Override
        public void OnCommandComplete(WeatherForecast forecast) {
            forecastViewModel = new ForecastViewModel(forecast);
            view.showWeather(forecastViewModel.getForecast());
        }
    }
}
