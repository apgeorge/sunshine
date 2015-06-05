package com.android.sunshine.command;

import com.android.sunshine.model.WeatherForecastFactory;
import com.android.sunshine.net.http.ApiClient;
import com.android.sunshine.service.WeatherService;

public class CommandFactory {
    public AsyncCommand createWeatherFetcherCommand() {
        return new WeatherFetcherTask(new WeatherService(new ApiClient(),new WeatherForecastFactory()));
    }
}
