package com.android.sunshine.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class UserPreferences {
    private Context context;

    public UserPreferences(Context context) {
        this.context = context;
    }

    public String getZip() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(context.getString(R.string.pref_location_key), context.getString(R.string.pref_location_default_value));
    }
}
