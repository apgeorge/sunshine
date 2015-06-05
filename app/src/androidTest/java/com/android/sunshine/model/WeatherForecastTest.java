package com.android.sunshine.model;

import android.support.test.runner.AndroidJUnit4;

import com.android.sunshine.datasource.DataSourceException;
import com.android.sunshine.datasource.JSONDataSource;
import com.android.sunshine.util.Util;
import com.android.sunshine.util.time.StubClock;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.core.Is.is;

@RunWith(AndroidJUnit4.class)
public class WeatherForecastTest {
    private StubClock stubClock;
    private WeatherForecast weatherForecast;

    @Before
    public void setUp() throws DataSourceException, JSONException {
        setupMockClock();
        final JSONObject jsonObject = new JSONObject(getWeatherJson());
        weatherForecast = new WeatherForecast(new JSONDataSource(jsonObject));
    }

    private void setupMockClock() {
        stubClock = new StubClock();
        stubClock.setJulianDayForCurrentTime(2457172);
        Util.setClock(stubClock);
    }

    @Test
    public void testGetCity() throws Exception {
        Assert.assertThat(weatherForecast.getCity(), is("Mountain View"));
    }

    @Test
    public void testGetDays() throws Exception {
        Assert.assertThat(weatherForecast.getDays().size(), is(7));
        DayWeatherForecast dayWeatherForecast = weatherForecast.getDays().get(0);
        Assert.assertThat(dayWeatherForecast.getMain(), is("Clear"));
        Assert.assertThat(dayWeatherForecast.getMax(), is(7.86));
        Assert.assertThat(dayWeatherForecast.getMin(), is(7.86));
    }


    @Test
    public void testMaxForDay() throws Exception {
        Assert.assertThat(weatherForecast.getMaxForDay(1), is(24.06));
    }

    @Test
    public void testDayTextForDay() throws Exception {
        Assert.assertThat(weatherForecast.getDays().get(6).getDay(), is("Thu, Jun 4"));
        Assert.assertThat(weatherForecast.getDays().get(1).getDay(), is("Sat, May 30"));
    }

    @Test
    public void testMinForDay() throws Exception {
        Assert.assertThat(weatherForecast.getMinForDay(1), is(9.69));
    }

    private String getWeatherJson() {
        String json = "\n" +
                "{\"cod\":\"200\",\"message\":0.0259,\"city\":{\"id\":0,\"name\":\"Mountain View\",\"country\":\"US\",\"coord\":{\"lat\":37.4056,\"lon\":-122.0775}},\"cnt\":7,\"list\":[{\"dt\":1432843200,\"temp\":{\"day\":7.86,\"min\":7.86,\"max\":7.86,\"night\":7.86,\"eve\":7.86,\"morn\":7.86},\"pressure\":989.86,\"humidity\":96,\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"sky is clear\",\"icon\":\"01n\"}],\"speed\":0.92,\"deg\":270,\"clouds\":0},{\"dt\":1432929600,\"temp\":{\"day\":24.04,\"min\":9.69,\"max\":24.06,\"night\":9.69,\"eve\":19.55,\"morn\":10.69},\"pressure\":988.96,\"humidity\":65,\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"sky is clear\",\"icon\":\"01d\"}],\"speed\":1.4,\"deg\":257,\"clouds\":0},{\"dt\":1433016000,\"temp\":{\"day\":26.41,\"min\":10.14,\"max\":26.41,\"night\":10.14,\"eve\":20.94,\"morn\":12.92},\"pressure\":987.4,\"humidity\":60,\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"sky is clear\",\"icon\":\"02d\"}],\"speed\":1.41,\"deg\":246,\"clouds\":8},{\"dt\":1433102400,\"temp\":{\"day\":17.46,\"min\":9.89,\"max\":19.88,\"night\":12.5,\"eve\":19.88,\"morn\":9.89},\"pressure\":1008.47,\"humidity\":0,\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"speed\":1.63,\"deg\":283,\"clouds\":20},{\"dt\":1433188800,\"temp\":{\"day\":17.98,\"min\":10.07,\"max\":19.35,\"night\":13.61,\"eve\":19.35,\"morn\":10.07},\"pressure\":1012.06,\"humidity\":0,\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"speed\":1.73,\"deg\":282,\"clouds\":19},{\"dt\":1433275200,\"temp\":{\"day\":17.31,\"min\":10.2,\"max\":19.38,\"night\":14.13,\"eve\":19.38,\"morn\":10.2},\"pressure\":1014.65,\"humidity\":0,\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"speed\":3.67,\"deg\":331,\"clouds\":0},{\"dt\":1433361600,\"temp\":{\"day\":19.08,\"min\":11,\"max\":21.33,\"night\":14.11,\"eve\":21.33,\"morn\":11},\"pressure\":1007.22,\"humidity\":0,\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"sky is clear\",\"icon\":\"01d\"}],\"speed\":3.07,\"deg\":316,\"clouds\":0}]}\n";
        return json;
    }

}