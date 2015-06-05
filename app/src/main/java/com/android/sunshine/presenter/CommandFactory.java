package com.android.sunshine.presenter;

import com.android.sunshine.app.IAsyncCommand;
import com.android.sunshine.model.WeatherForecastFactory;
import com.android.sunshine.service.ApiClient;
import com.android.sunshine.service.WeatherService;

public class CommandFactory {
    public IAsyncCommand createWeatherFetcherCommand() {
        return new WeatherFetcherTask(new WeatherService(new ApiClient(),new WeatherForecastFactory()));
    }
}
