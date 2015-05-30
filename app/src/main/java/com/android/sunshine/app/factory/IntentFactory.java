package com.android.sunshine.app.factory;

import android.content.Context;
import android.content.Intent;

import com.android.sunshine.app.DetailActivity;

public class IntentFactory {
    public Intent createDetailActivityIntent(Context packageContext, String data) {
        Intent intent = new Intent(packageContext, DetailActivity.class);
        intent.putExtra(Intent.EXTRA_TEXT, data);
        return intent;
    }
}
