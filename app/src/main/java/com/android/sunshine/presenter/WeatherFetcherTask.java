package com.android.sunshine.presenter;

import android.os.AsyncTask;

import com.android.sunshine.app.IWeatherFetcherTask;

import java.util.List;

public class WeatherFetcherTask extends AsyncTask<Void, Void, List<String>> implements IWeatherFetcherTask {
    protected IPresenter presenter;

    public WeatherFetcherTask() {
    }

    @Override
    protected List<String> doInBackground(Void... params) {
        return presenter.getWeather();
    }

    @Override
    protected void onPostExecute(List<String> strings) {
        presenter.updateView(strings);
    }

    @Override
    public void doExecute(IPresenter presenter) {
        this.presenter = presenter;
        execute();
    }
}
