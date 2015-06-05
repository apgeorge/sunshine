package com.android.sunshine.presenter;

import android.content.Context;
import android.content.Intent;

import com.android.sunshine.app.IMainView;
import com.android.sunshine.app.factory.IntentFactory;
import com.android.sunshine.common.UserPreferences;
import com.android.sunshine.model.DataSourceException;
import com.android.sunshine.model.WeatherForecast;
import com.android.sunshine.service.WeatherService;
import com.android.sunshine.util.Util;
import com.android.sunshine.util.testutils.MapDataSource;
import com.android.sunshine.util.testutils.MockClock;
import com.fasterxml.jackson.jr.ob.JSON;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class MainActivityFragmentPresenterTest {

    private IMainView view;
    private WeatherService weatherService;
    private MainActivityFragmentPresenter presenter;
    private WeatherFetcherTask weatherFetcherTask;
    private MockClock mockClock;
    private IntentFactory intentFactory;
    private UserPreferences userPreferences;
    private Context context;
    private CommandFactory commandFactory;

    @Before
    public void setUp() throws Exception {
        setupMockClock();
        view = mock(IMainView.class);
        weatherService = mock(WeatherService.class);
        weatherFetcherTask = new StubWeatherFetcherTask(weatherService);
        intentFactory = mock(IntentFactory.class);
        userPreferences = mock(UserPreferences.class);
        context = mock(Context.class);
        commandFactory = mock(CommandFactory.class);
        presenter = new MainActivityFragmentPresenter(view, intentFactory, userPreferences, context, commandFactory);
        when(weatherService.getWeatherData("94043")).thenReturn(getExpectedWeatherForecast());
        when(userPreferences.getZip()).thenReturn("94043");
        when(commandFactory.createWeatherFetcherCommand()).thenReturn(new StubWeatherFetcherTask(weatherService));
    }

    private void setupMockClock() {
        mockClock = new MockClock();
        mockClock.setJulianDayForCurrentTime(2457172);
        Util.setClock(mockClock);
    }

    @Test
    public void shouldDisplayWeather() throws Exception {
        String[] expectedWeather = {"Fri, May 29 - Clear - 8/8",
                "Sat, May 30 - Clear - 24/10",
                "Sun, May 31 - Clear - 26/10",
                "Mon, Jun 1 - Rain - 20/10",
                "Tue, Jun 2 - Rain - 19/10",
                "Wed, Jun 3 - Rain - 19/10",
                "Thu, Jun 4 - Clear - 21/11"};

        presenter.initialize();

        verify(view).showWeather(Arrays.asList(expectedWeather));

    }

    private WeatherForecast getExpectedWeatherForecast() throws DataSourceException, JSONException, IOException {
        WeatherForecast weatherForecast = new WeatherForecast(new MapDataSource(JSON.std.<String>mapFrom(getWeatherJson())));
        return weatherForecast;
    }

    @Test
    public void shouldCallWeatherServiceOnInitialize() throws DataSourceException, JSONException, IOException {
        presenter.initialize();

        verify(weatherService).getWeatherData("94043");
    }

    @Test
    public void shouldLaunchDetailView() throws DataSourceException, IOException, JSONException {
        Intent mockDetailActivityIntent = mock(Intent.class);
        when(intentFactory.createDetailActivityIntent(context, "Sun, May 31 - Clear - 26/10")).thenReturn(mockDetailActivityIntent);
        presenter.initialize();

        presenter.selectDay(2);

        verify(view).launchDetail(mockDetailActivityIntent);
    }

    private String getWeatherJson() {
        String json = "\n" +
                "{\"cod\":\"200\",\"message\":0.0259,\"city\":{\"id\":0,\"name\":\"Mountain View\",\"country\":\"US\",\"coord\":{\"lat\":37.4056,\"lon\":-122.0775}},\"cnt\":7,\"list\":[{\"dt\":1432843200,\"temp\":{\"day\":7.86,\"min\":7.86,\"max\":7.86,\"night\":7.86,\"eve\":7.86,\"morn\":7.86},\"pressure\":989.86,\"humidity\":96,\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"sky is clear\",\"icon\":\"01n\"}],\"speed\":0.92,\"deg\":270,\"clouds\":0},{\"dt\":1432929600,\"temp\":{\"day\":24.04,\"min\":9.69,\"max\":24.06,\"night\":9.69,\"eve\":19.55,\"morn\":10.69},\"pressure\":988.96,\"humidity\":65,\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"sky is clear\",\"icon\":\"01d\"}],\"speed\":1.4,\"deg\":257,\"clouds\":0},{\"dt\":1433016000,\"temp\":{\"day\":26.41,\"min\":10.14,\"max\":26.41,\"night\":10.14,\"eve\":20.94,\"morn\":12.92},\"pressure\":987.4,\"humidity\":60,\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"sky is clear\",\"icon\":\"02d\"}],\"speed\":1.41,\"deg\":246,\"clouds\":8},{\"dt\":1433102400,\"temp\":{\"day\":17.46,\"min\":9.89,\"max\":19.88,\"night\":12.5,\"eve\":19.88,\"morn\":9.89},\"pressure\":1008.47,\"humidity\":0,\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"speed\":1.63,\"deg\":283,\"clouds\":20},{\"dt\":1433188800,\"temp\":{\"day\":17.98,\"min\":10.07,\"max\":19.35,\"night\":13.61,\"eve\":19.35,\"morn\":10.07},\"pressure\":1012.06,\"humidity\":0,\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"speed\":1.73,\"deg\":282,\"clouds\":19},{\"dt\":1433275200,\"temp\":{\"day\":17.31,\"min\":10.2,\"max\":19.38,\"night\":14.13,\"eve\":19.38,\"morn\":10.2},\"pressure\":1014.65,\"humidity\":0,\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"speed\":3.67,\"deg\":331,\"clouds\":0},{\"dt\":1433361600,\"temp\":{\"day\":19.08,\"min\":11,\"max\":21.33,\"night\":14.11,\"eve\":21.33,\"morn\":11},\"pressure\":1007.22,\"humidity\":0,\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"sky is clear\",\"icon\":\"01d\"}],\"speed\":3.07,\"deg\":316,\"clouds\":0}]}\n";
        return json;
    }
}