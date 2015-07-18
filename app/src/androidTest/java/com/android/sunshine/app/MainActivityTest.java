package com.android.sunshine.app;

import android.content.Intent;
import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public IntentsTestRule<MainActivity> rule = new IntentsTestRule<>(MainActivity.class);

    @Test
    public void shouldShowWeather() {
        onView(withId(R.id.listview_forecast)).check(matches(isDisplayed()));
        DataInteraction weatherForDay = onData(allOf(is(instanceOf(String.class)), is("Mon, Jul 20 - Rain - 30/19")));
        weatherForDay.check(matches(isDisplayed()));

        weatherForDay.perform(click());

//        intended(allOf(
//                hasAction(equalTo(Intent.ACTION_VIEW)),
//                hasCategories(hasItem(equalTo(Intent.CATEGORY_BROWSABLE))),
//                hasData(hasHost(equalTo("www.google.com"))),
//                hasExtra(Intent.EXTRA_TEXT, "Mon, Jul 20 - Rain - 30/19"),
//                toPackage("com.android.browser")))
//
        intended(allOf(
                hasExtra(Intent.EXTRA_TEXT, "Mon, Jul 20 - Rain - 30/19"),
                hasComponent(ForecastDetailActivity.class.getName())
                ));

    }

}