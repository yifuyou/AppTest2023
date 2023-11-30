/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Just for learn.
 */

package com.yifuyou.web.db;

import android.content.Context;
import android.content.SharedPreferences;


public class SharedPreferenceUtil {

    private static SharedPreferences msp;

    public static SharedPreferences getOrCreateSp(Context context, String name, int mode) {
        if (msp != null) {
            msp = context.getSharedPreferences(name, mode);
        }
        return msp;
    }

    public static void saveString(String key, String value) {
        if (msp != null) {
            msp.edit().putString(key, value).apply();
        }
    }

    public static void release() {
        msp = null;
    }

}
