package com.android.sunshine.command;

public interface AsyncCommand {
    void doExecute(String zip);

    void setOnCompletedListener(OnCommandCompletedListener onCommandCompletedListerner);

}
