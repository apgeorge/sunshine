package com.android.sunshine.datasource;

import org.json.JSONException;

public class DataSourceException extends Exception {
    public DataSourceException(JSONException e) {
        super(e);
    }
}
