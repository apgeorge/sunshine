package com.android.sunshine.presenter;

public class StubWeatherFetcherTask extends WeatherFetcherTask {
    @Override
    public void doExecute(IPresenter presenter, String zip) {
        this.presenter = presenter;
        onPostExecute(doInBackground(zip));
    }
}
