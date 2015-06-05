package com.android.sunshine.command;

import com.android.sunshine.model.WeatherForecastFactory;
import com.android.sunshine.apiclient.ApiClient;
import com.android.sunshine.service.WeatherService;

public class CommandFactory {
    public IAsyncCommand createWeatherFetcherCommand() {
        return new WeatherFetcherTask(new WeatherService(new ApiClient(),new WeatherForecastFactory()));
    }
}
