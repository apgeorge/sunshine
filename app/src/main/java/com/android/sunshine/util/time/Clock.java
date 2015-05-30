package com.android.sunshine.util.time;

import android.text.format.Time;

public class Clock {
    public long getOffsetFromEpochForJulianDay(int julianDay) {
        Time epoch = new Time();
        return epoch.setJulianDay(julianDay);
    }

    public int getJulianDayForCurrentTime() {
        Time dayTime = new Time();
        dayTime.setToNow();
        return Time.getJulianDay(System.currentTimeMillis(), dayTime.gmtoff);
    }
}
