package com.yifuyou.web;

import com.yifuyou.web.load.DownloadUtil;
import com.yifuyou.web.load.LoadHandler;
import com.yifuyou.web.load.db.DataBaseUtil;

import android.content.Context;

public class WebInitUtil {

    public static void init(Context context) {
        LoadHandler.init();
        DataBaseUtil.dataInit(context);
    }

    public static void release(Context context) {
        DownloadUtil.release();
    }
}
