package com.android.sunshine.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.sunshine.app.factory.IntentFactory;
import com.android.sunshine.command.CommandFactory;
import com.android.sunshine.presenter.IPresenter;
import com.android.sunshine.presenter.MainActivityFragmentPresenter;

import java.util.List;


public class ForecastFragment extends Fragment implements IMainView {

    private IPresenter presenter;
    private ListView listView;

    public ForecastFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        listView = (ListView) rootView.findViewById(R.id.listview_forecast);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                presenter.selectDay(i);
            }
        });
        presenter = new MainActivityFragmentPresenter(this, new IntentFactory(),new UserPreferences(getActivity()), getActivity(), new CommandFactory());
        presenter.initialize();
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.forecastfragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_refresh) {
            presenter.fetchWeather();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private ArrayAdapter<String> createAdapter(List<String> forecasts) {
        return new ArrayAdapter<>(getActivity(),
                    R.layout.list_item_forecast, R.id.list_item_forecast_textview, forecasts);
    }

    @Override
    public void showWeather(List<String> weatherData) {
        if (weatherData == null) return;
        ArrayAdapter<String> adapter = createAdapter(weatherData);
        listView.setAdapter(adapter);
    }

    @Override
    public void launchDetail(Intent detailActivityIntent) {
        startActivity(detailActivityIntent);
    }


}
