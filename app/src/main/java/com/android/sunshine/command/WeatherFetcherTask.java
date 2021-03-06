package com.android.sunshine.command;

import android.os.AsyncTask;

import com.android.sunshine.datasource.DataSourceException;
import com.android.sunshine.model.WeatherForecast;
import com.android.sunshine.service.WeatherService;

import org.json.JSONException;

public class WeatherFetcherTask extends AsyncTask<String, Void, WeatherForecast> implements AsyncCommand {
    private OnCommandCompletedListener onCommandCompletedListerner;
    private WeatherService weatherService;

    WeatherFetcherTask(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Override
    protected WeatherForecast doInBackground(String... strings) {
        try {
            return weatherService.getWeatherData(strings[0]);
        } catch (DataSourceException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(WeatherForecast weatherForecast) {
        if (onCommandCompletedListerner != null) onCommandCompletedListerner.OnCommandComplete(weatherForecast);
    }

    @Override
    public void doExecute(String zip) {
        execute(zip);
    }

    @Override
    public void setOnCompletedListener(OnCommandCompletedListener onCommandCompletedListener) {
        this.onCommandCompletedListerner = onCommandCompletedListener;
    }
}
