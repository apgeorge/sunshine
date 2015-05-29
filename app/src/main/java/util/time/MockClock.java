package util.time;

public class MockClock extends Clock {

    private int currentJulianDay;

    @Override
    public int getJulianDayForCurrentTime() {
        return currentJulianDay;
    }

    public void setJulianDayForCurrentTime(int julianDay) {
        currentJulianDay = julianDay;
    }
}
