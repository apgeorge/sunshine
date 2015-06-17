package com.android.sunshine.presenter;

import com.android.sunshine.app.ForecastView;
import com.android.sunshine.app.Navigator;
import com.android.sunshine.app.UserPreferences;
import com.android.sunshine.command.AsyncCommand;
import com.android.sunshine.command.CommandFactory;
import com.android.sunshine.command.OnCommandCompletedListener;
import com.android.sunshine.model.WeatherForecast;

public class ForecastViewPresenter implements IForecastViewPresenter {
    private final CommandFactory commandFactory;
    private final Navigator navigator;
    private final UserPreferences userPreferences;
    private final ForecastView view;
    private ForecastViewModel forecastViewModel;

    public ForecastViewPresenter(ForecastView view, UserPreferences userPreferences, CommandFactory commandFactory, Navigator navigator) {
        this.view = view;
        this.userPreferences = userPreferences;
        this.commandFactory = commandFactory;
        this.navigator = navigator;
    }

    @Override
    public void fetchWeather() {
        AsyncCommand weatherFetcherTask = commandFactory.createWeatherFetcherCommand();
        weatherFetcherTask.setOnCompletedListener(new OnFetchWeatherCompletedListener());
        weatherFetcherTask.doExecute(userPreferences.getZip());
    }

    @Override
    public void selectDay(int day) {
        navigator.launchDetail(forecastViewModel.getForecast().get(day));
    }

    private class OnFetchWeatherCompletedListener implements OnCommandCompletedListener {
        @Override
        public void OnCommandComplete(WeatherForecast forecast) {
            forecastViewModel = new ForecastViewModel(forecast);
            view.showWeather(forecastViewModel.getForecast());
        }
    }
}
