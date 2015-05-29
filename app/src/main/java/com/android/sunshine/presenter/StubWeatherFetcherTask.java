package com.android.sunshine.presenter;

public class StubWeatherFetcherTask extends WeatherFetcherTask {
    @Override
    public void doExecute(IPresenter presenter) {
        this.presenter = presenter;
        onPostExecute(doInBackground());
    }
}
