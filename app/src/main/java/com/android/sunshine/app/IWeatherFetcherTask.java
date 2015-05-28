package com.android.sunshine.app;

import com.android.sunshine.presenter.IPresenter;

public interface IWeatherFetcherTask {
    void doExecute(IPresenter presenter);
}
