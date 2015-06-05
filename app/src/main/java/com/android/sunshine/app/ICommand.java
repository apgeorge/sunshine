package com.android.sunshine.app;

import com.android.sunshine.presenter.IPresenter;

public interface ICommand {
    void doExecute(IPresenter presenter, String zip);
}
