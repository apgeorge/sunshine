package com.android.sunshine.app;

import com.android.sunshine.presenter.IOnCommandCompletedListener;

public interface IAsyncCommand {
    void doExecute(String zip);

    void setOnCompletedListener(IOnCommandCompletedListener onCommandCompletedListerner);

}
