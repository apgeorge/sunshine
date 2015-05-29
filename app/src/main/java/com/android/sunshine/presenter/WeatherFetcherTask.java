package com.android.sunshine.presenter;

import android.os.AsyncTask;

import com.android.sunshine.app.IWeatherFetcherTask;

import java.util.List;

public class WeatherFetcherTask extends AsyncTask<String, Void, List<String>> implements IWeatherFetcherTask {
    protected IPresenter presenter;

    public WeatherFetcherTask() {
    }

    @Override
    protected List<String> doInBackground(String... strings) {
        return presenter.getWeather(strings[0]);
    }

    @Override
    protected void onPostExecute(List<String> strings) {
        presenter.updateView(strings);
    }

    @Override
    public void doExecute(IPresenter presenter, String zip) {
        this.presenter = presenter;
        execute(zip); //this is not being UTed
    }
}