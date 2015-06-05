package com.android.sunshine.app.factory;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.android.sunshine.app.ForecastDetailActivity;
import com.android.sunshine.app.SettingsActivity;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.core.Is.is;

@RunWith(AndroidJUnit4.class)
public class IntentFactoryTest {

    @Test
    public void shouldCreateDetailActivityIntent() throws Exception {
        Intent detailActivityIntent = new IntentFactory().createDetailActivityIntent(
                InstrumentationRegistry.getInstrumentation().getContext()
                , "some data");

        Assert.assertThat(detailActivityIntent.getStringExtra(Intent.EXTRA_TEXT), is("some data"));
        Assert.assertThat(detailActivityIntent.getComponent().getShortClassName(), is(ForecastDetailActivity.class.getName()));
    }

    @Test
    public void shouldCreateSettingsActivityIntent() throws Exception {
        Intent settings = new IntentFactory().createSettingsIntent(
                InstrumentationRegistry.getInstrumentation().getContext());

        Assert.assertThat(settings.getComponent().getShortClassName(), is(SettingsActivity.class.getName()));
    }
}