package com.android.sunshine.app;

import android.content.Context;
import android.content.Intent;

import com.android.sunshine.app.factory.IntentFactory;

public class Navigator {
    private Context context;

    public Navigator(Context context) {
        this.context = context;
    }

    public void launchDetail(String data) {
        Intent detailActivityIntent = new IntentFactory(context).createDetailActivityIntent(data);
        context.startActivity(detailActivityIntent);
    }
}