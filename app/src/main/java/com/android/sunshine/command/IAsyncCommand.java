package com.android.sunshine.command;

public interface IAsyncCommand {
    void doExecute(String zip);

    void setOnCompletedListener(IOnCommandCompletedListener onCommandCompletedListerner);

}
