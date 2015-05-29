package util;

import com.android.sunshine.util.net.UrlMaker;

import util.time.Clock;

public class Util {
    private static Clock clock = new Clock();

    public static UrlMaker urlMaker() {
        return new UrlMaker();
    }

    public static Clock getClock() {
        return clock;
    }

    public static void setClock(Clock mockClock) {
        clock = mockClock;
    }
}
