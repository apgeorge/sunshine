package com.android.sunshine.model;

import org.json.JSONException;

public class DataSourceException extends Exception {
    public DataSourceException(JSONException e) {
        super(e);
    }
}
