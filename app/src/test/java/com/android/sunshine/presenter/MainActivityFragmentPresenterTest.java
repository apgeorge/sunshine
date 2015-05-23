package com.android.sunshine.presenter;

import com.android.sunshine.app.IMainView;

import org.junit.Test;

import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public class MainActivityFragmentPresenterTest {
    @Test
    public void testInitialize() throws Exception {
        IMainView view = mock(IMainView.class);
        MainActivityFragmentPresenter presenter = new MainActivityFragmentPresenter(view);
        presenter.initialize();

        verify(view).showWeather(Arrays.asList("Today - Sunny - 88/100",
                "Tomorrow - UMBRELLAS - 88/100",
                "Weds - GOTO BEACH!! - 88/100",
                "Thurs - OKIE DOKIE - 88/100",
                "Fri - NICE>>>>>>>> - 88/100",
                "Sat - YAY - 88/100",
                "Sun - Sunny - 88/100"));
    }
}