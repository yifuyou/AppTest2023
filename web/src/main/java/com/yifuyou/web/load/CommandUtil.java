package com.yifuyou.web.load;

import java.util.Date;
import java.util.Random;

public class CommandUtil {
    public static final String TAG = "CommandUtil";

    public static String getRandomId(int bit) {
        StringBuilder result = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < bit; i++) {
            result.append(random.nextInt(10));
        }
        return result.toString();
    }

    public static String getStateByInt(int intState) {
        switch (intState) {
            case Constants.STATE_INT_LOAD_SUCCESS:
                return Constants.STATE_STRING_LOAD_SUCCESS;
            case Constants.STATE_INT_LOAD_FAILED:
                return Constants.STATE_STRING_LOAD_FAILED;
            case Constants.STATE_INT_LOAD_UNFINISH:
                return Constants.STATE_STRING_LOAD_UNFINISH;
            default: return "";
        }
    }

    public static int getStateByString(String strState) {
        if (strState == null){
            return Constants.STATE_INT_LOAD_UNFINISH;
        }
        switch (strState) {
            case Constants.STATE_STRING_LOAD_SUCCESS:
                return Constants.STATE_INT_LOAD_SUCCESS;
            case Constants.STATE_STRING_LOAD_FAILED:
                return Constants.STATE_INT_LOAD_FAILED;
            case Constants.STATE_STRING_LOADING:
            default:
                return Constants.STATE_INT_LOAD_UNFINISH;
        }
    }

    public static String getTimeByLong(long time) {
        Date date = new Date(time);
        Date cTime = new Date(System.currentTimeMillis());
        Date today = new Date(cTime.getYear(), cTime.getMonth(), cTime.getDate());
        if (date.compareTo(today)>0) {
            return (date.getHours()<10 ? "0" + date.getHours() : date.getHours()) + ":"
                    + (date.getMinutes()<10 ? "0" + date.getMinutes() : date.getMinutes());
        }

        int year = date.getYear() > 1900 ? date.getYear() : date.getYear() + 1900;
        return year+"-"+ date.getMonth()+"-"+date.getDate();
    }
}
