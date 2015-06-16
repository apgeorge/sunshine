package com.android.sunshine.app.factory;

import android.content.Context;
import android.content.Intent;

import com.android.sunshine.app.ForecastDetailActivity;
import com.android.sunshine.app.SettingsActivity;

public class IntentFactory {
    public IntentFactory(Context packageContext) {
        this.packageContext = packageContext;
    }

    private Context packageContext;

    public Intent createDetailActivityIntent(String data) {
        Intent intent = new Intent(packageContext, ForecastDetailActivity.class);
        intent.putExtra(Intent.EXTRA_TEXT, data);
        return intent;
    }

    public Intent createSettingsIntent() {
        return new Intent(packageContext, SettingsActivity.class);
    }
}
