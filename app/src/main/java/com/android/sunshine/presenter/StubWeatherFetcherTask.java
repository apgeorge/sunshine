package com.android.sunshine.presenter;

import com.android.sunshine.service.WeatherService;

public class StubWeatherFetcherTask extends WeatherFetcherTask {
    public StubWeatherFetcherTask(WeatherService weatherService) {
        super(weatherService);
    }

    @Override
    public void doExecute(String zip) {
        onPostExecute(doInBackground(zip));
    }
}
