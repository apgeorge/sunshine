package com.android.sunshine.model;

import java.util.List;

public interface IDataSource {
    IDataSource getObject(String key) throws DataSourceException;

    String getString(String key) throws DataSourceException;

    List<IDataSource> getArrayObject(String key) throws DataSourceException;

    double getDouble(String key) throws DataSourceException;
}
