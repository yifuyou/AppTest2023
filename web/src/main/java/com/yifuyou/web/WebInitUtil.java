package com.yifuyou.web;

import android.content.Context;

import com.yifuyou.web.load.LoadHandler;

public class WebInitUtil {

    public static void init(Context context) {
        LoadHandler.init();
    }
}
