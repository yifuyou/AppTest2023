package com.yifuyou.web;

import android.content.Context;

import com.yifuyou.web.db.DataBaseUtil;
import com.yifuyou.web.load.LoadHandler;
import com.yifuyou.web.util.DownloadUtil;

public class WebInitUtil {

    public static void init(Context context) {
        LoadHandler.init();
        DataBaseUtil.dataInit(context);
    }

    public static void release(Context context) {
        DownloadUtil.release();
    }
}
