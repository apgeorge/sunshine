package com.android.sunshine.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.sunshine.presenter.IPresenter;
import com.android.sunshine.presenter.MainActivityFragmentPresenter;
import com.android.sunshine.service.WeatherService;

import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements IMainView {

    private final IPresenter presenter;
    private ListView listView;

    public MainActivityFragment() {
        presenter = new MainActivityFragmentPresenter(this, new WeatherService());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        listView = (ListView) rootView.findViewById(R.id.listview_forecast);

        presenter.initialize();
        return rootView;
    }

    private ArrayAdapter<String> createAdapter(List<String> forecasts) {
        return new ArrayAdapter<>(getActivity(),
                    R.layout.list_item_forecast, R.id.list_item_forecast_textview, forecasts);
    }

    @Override
    public void showWeather(List<String> weatherData) {
        ArrayAdapter<String> adapter = createAdapter(weatherData);
        listView.setAdapter(adapter);
    }
}
