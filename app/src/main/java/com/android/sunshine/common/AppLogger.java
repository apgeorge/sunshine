package com.android.sunshine.common;

import android.util.Log;

import java.io.IOException;

public class AppLogger {

    private static AppLogger appLogger = new AppLogger();

    private AppLogger() {
    }

    public int logError(String logTag, String msg, IOException e) {
        return Log.e(logTag, msg, e);
    }

    public static AppLogger getLogger() {
        return appLogger;
    }

    public static void setLogger(AppLogger newAppLogger) {
        appLogger = newAppLogger;
    }

    public int logVerbose(String logTag, String msg) {
        return Log.v(logTag, msg);
    }
}
