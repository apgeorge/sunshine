package com.android.sunshine.presenter;

import com.android.sunshine.app.ICommand;

public class CommandFactory {
    public ICommand createWeatherFetcherCommand() {
        return new WeatherFetcherTask();
    }
}
