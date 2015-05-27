package com.android.sunshine.presenter;

import com.android.sunshine.app.IMainView;
import com.android.sunshine.service.WeatherService;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class MainActivityFragmentPresenterTest {

    private IMainView view;
    private WeatherService weatherService;
    private MainActivityFragmentPresenter presenter;

    @Before
    public void setUp() throws Exception {
        view = mock(IMainView.class);
        weatherService = mock(WeatherService.class);
        presenter = new MainActivityFragmentPresenter(view, weatherService);
    }

    @Test
    public void shouldDisplayWeather() throws Exception {
        String[] expectedWeather = {"Today - Sunny - 88/100",
                "Tomorrow - UMBRELLAS - 88/100",
                "Weds - GOTO BEACH!! - 88/100",
                "Thurs - OKIE DOKIE - 88/100",
                "Fri - NICE>>>>>>>> - 88/100",
                "Sat - YAY - 88/100",
                "Sun - Sunny - 88/100"};

        when(weatherService.getWeatherData()).thenReturn(Arrays.asList(expectedWeather));

        presenter.initialize();

        verify(view).showWeather(Arrays.asList(expectedWeather));

    }

    @Test
    public void shouldCallWeatherServiceOnInitialize()
    {
        presenter.initialize();

        verify(weatherService).getWeatherData();
    }
}