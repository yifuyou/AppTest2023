package com.yifuyou.web.load;

import android.os.Environment;

public class FileUtils {
    private static String storedPath = Environment.getExternalStorageDirectory() + "/" +Environment.DIRECTORY_DOWNLOADS + "/";


    public static void setStoredPath(String storedPath) {
        FileUtils.storedPath = storedPath;
    }


    public static String getStoredPath() {
        return storedPath;
    }
}
