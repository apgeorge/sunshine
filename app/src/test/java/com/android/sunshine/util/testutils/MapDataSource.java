package com.android.sunshine.util.testutils;

import com.android.sunshine.model.DataSourceException;
import com.android.sunshine.model.IDataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapDataSource implements IDataSource {
    private Map<String, Object> mapSource;

    public MapDataSource(Map<String, Object> map) {
        mapSource = map;
    }

    @Override
    public IDataSource getObject(String key) throws DataSourceException {
        return new MapDataSource((Map<String, Object>) mapSource.get(key));
    }

    @Override
    public String getString(String key) throws DataSourceException {
        return (String) mapSource.get(key);
    }

    @Override
    public List<IDataSource> getArrayObject(String key) throws DataSourceException {
        List<Map<String, Object>> o = (List<Map<String, Object>>) mapSource.get(key);
        ArrayList<IDataSource> list = new ArrayList<>();
        for (Map<String, Object> each : o) {
            list.add(new MapDataSource(each));
        }
        return list;
    }

    @Override
    public double getDouble(String key) throws DataSourceException {
        return Double.valueOf(String.valueOf(mapSource.get(key)));
    }

    @Override
    public Long getLong(String key) throws DataSourceException {
        return (Long) mapSource.get(key);
    }
}
