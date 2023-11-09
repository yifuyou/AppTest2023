package com.yifuyou.test_main.main.main.utls;

import java.sql.Time;

public class TimeUtil {
    public static long getTimeString(){
        return System.currentTimeMillis();
    }

    public static String convertTimeToString(long timeMillis) {
        Time time = new Time(timeMillis);
        System.out.println(time.toString());
        return time.toString();
    }

}
