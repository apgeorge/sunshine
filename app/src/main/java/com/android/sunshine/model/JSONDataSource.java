package com.android.sunshine.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<IDataSource> getArrayObject(String key) throws DataSourceException {
        try {
            ArrayList<IDataSource> x = new ArrayList<>();
            JSONArray jsonArray = jsonObject.getJSONArray(key);

            int length = jsonArray.length();
            for (int i = 0; i < length; i++) {
                x.add(new JSONDataSource(jsonArray.getJSONObject(i)));
            }

            return x;
        } catch (JSONException e) {
            throw new DataSourceException(e);
        }
    }

    @Override
    public double getDouble(String key) throws DataSourceException {
        try {
            return jsonObject.getDouble(key);
        } catch (JSONException e) {
            throw new DataSourceException(e);
        }
    }
}
