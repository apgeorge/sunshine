package com.android.sunshine.model;

public interface IDataSource {
    IDataSource getObject(String key) throws DataSourceException;

    String getString(String key) throws DataSourceException;
}
