package com.android.sunshine.util.time;

public class StubClock extends Clock {

    private int currentJulianDay;

    @Override
    public int getJulianDayForCurrentTime() {
        return currentJulianDay;
    }

    public void setJulianDayForCurrentTime(int julianDay) {
        currentJulianDay = julianDay;
    }


}
