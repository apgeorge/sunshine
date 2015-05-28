package com.android.sunshine.presenter;

import android.os.AsyncTask;

import com.android.sunshine.app.IWeatherFetcherTask;

public class WeatherFetcherTask extends AsyncTask<Void, Void, Void> implements IWeatherFetcherTask {
    protected IPresenter presenter;

    public WeatherFetcherTask() {
    }

    @Override
    protected Void doInBackground(Void... params) {
        presenter.getWeather();
        return null;
    }

    @Override
    public void doExecute(IPresenter presenter) {
        this.presenter = presenter;
        execute();
    }
}
