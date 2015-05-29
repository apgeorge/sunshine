package com.android.sunshine.model;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONDataSource implements IDataSource {
    private JSONObject jsonObject;

    public JSONDataSource(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    @Override
    public IDataSource getObject(String key) throws DataSourceException {
        try {
            return new JSONDataSource(jsonObject.getJSONObject(key));
        } catch (JSONException e) {
            throw new DataSourceException(e);
        }
    }

    @Override
    public String getString(String key) throws DataSourceException {
        try {
            return jsonObject.getString(key);
        } catch (JSONException e) {
            throw new DataSourceException(e);
        }
    }
}
