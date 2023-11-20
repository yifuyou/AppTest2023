package com.yifuyou.web;

import android.content.Context;

import com.yifuyou.web.load.LoadHandler;
import com.yifuyou.web.load.db.DataBaseUtil;

public class WebInitUtil {

    public static void init(Context context) {
        LoadHandler.init();
        DataBaseUtil.dataInit(context);
    }
}
