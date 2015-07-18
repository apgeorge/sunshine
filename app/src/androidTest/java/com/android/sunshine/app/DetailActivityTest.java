package com.android.sunshine.app;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class DetailActivityTest {

    @Rule
    public ActivityTestRule<ForecastDetailActivity> rule = new ActivityTestRule<>(ForecastDetailActivity.class, false,false);

    @Test
    public void shouldShowWeather() {
        Intent startIntent = new Intent();
        startIntent.putExtra(Intent.EXTRA_TEXT, "something");
        rule.launchActivity(startIntent);
        onView(withId(R.id.detail_text)).check(matches(withText("something")));
    }

}