package com.android.sunshine.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.android.sunshine.app.R;

public class UserPreferences {
    private Context context;

    public UserPreferences(Context context) {
        this.context = context;
    }

    public String getZip() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String location = preferences.getString(context.getString(R.string.pref_location_key), context.getString(R.string.pref_location_default_value));
        return location;
    }
}
