package com.android.sunshine.util.testutils;


import com.android.sunshine.util.time.Clock;

import org.joda.time.DateTimeUtils;

public class StubClock extends Clock{
    private int currentJulianDay;

    @Override
    public int getJulianDayForCurrentTime() {
        return currentJulianDay;
    }

    public void setJulianDayForCurrentTime(int julianDay) {
        currentJulianDay = julianDay;
    }

    public long getOffsetFromEpochForJulianDay(int julianDay) {
        return DateTimeUtils.fromJulianDay(julianDay);
    }
}
