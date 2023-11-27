/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Just for learn.
 */

package com.yifuyou.test_main.main.main.utls;

import android.app.Application;
import android.content.Context;

/**
 * info
 *
 * @author Administrator
 * @since 2023/11/26
 */
public class CommandUtil {
    private static Context mContext;

    public static void init(Application application) {
        mContext = application;
    }

    public static int getResId(Context context, String name, String type, String packageName) {
        if (context == null) {
            throw new NullPointerException("Context is null");
        }
        return context.getResources().getIdentifier(name, type, packageName);
    }

    public static int getDrawableId(String name) {
        if (mContext == null || name == null || "".equals(name)) {
            return -1;
        }

        return mContext.getResources().getIdentifier(name, "drawable", mContext.getOpPackageName());
    }
}
