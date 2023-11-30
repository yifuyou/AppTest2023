/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Just for learn.
 */

package com.yifuyou.web.util;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.IOException;

public class FileUtils {
    private static final String TAG = "FileUtils";
    private static String storedPath = Environment.getExternalStorageDirectory() + "/" +Environment.DIRECTORY_DOWNLOADS + "/";


    public static void setStoredPath(String storedPath) {
        FileUtils.storedPath = storedPath;
    }


    public static String getStoredPath() {
        return storedPath;
    }


    public static String checkAndGetNextFileName(String filePath, String fileName) {
        File file = new File(filePath + fileName);
        Log.i(TAG, "checkFile: " + file.getPath());
        if (TextUtils.isEmpty(fileName)) {
            return CommandUtil.getRandomId(10);
        }

        if (!file.exists()) {
            Log.i(TAG, "exists: " + file.exists());
            return fileName;
        }
        int lastPointIndex = fileName.lastIndexOf(".");
        if (fileName.contains(".") &&  lastPointIndex != fileName.length()-1) {
            return getNextUnExistFileName(filePath, fileName.substring(0, lastPointIndex), fileName.substring(lastPointIndex));
        }
        return getNextUnExistFileName(filePath, fileName, "");
    }

    private static String getNextUnExistFileName(String filePath, String fileName, String endStr) {
        String newName = "";
        if (fileName.contains("(") && fileName.contains(")")
                && fileName.lastIndexOf("(") < fileName.lastIndexOf(")")) {

            int rI = fileName.lastIndexOf("(");
            int lI = fileName.lastIndexOf(")");
            String content = fileName.substring(rI+1, lI);
            if (content.length()<4 && content.length()>0) {
                boolean isNum = true;
                for (int i = 0; i <content.length(); i++) {
                    if (content.charAt(i) < '0' && content.charAt(i) > '9') {
                        isNum = false;
                        break;
                    }
                }
                if(isNum) {
                    int integer = Integer.parseInt(content);
                    newName = fileName.substring(0, rI) + "(" + (integer + 1) + ")" + endStr;
                }
            }
        }


        newName = "".equals(newName) ? (fileName + "(" + 1 + ")" + endStr) : newName;
        File file = new File(filePath, newName);
        if (!file.exists()) {
            Log.i(TAG, "exists: " + file.exists());
            return newName;
        }

        return getNextUnExistFileName(filePath, newName, endStr);
    }

    public static boolean createFile(String fileName) {
        File file = new File(fileName);
        Log.i(TAG, "checkFile: " + file.getPath());
        try {
            if (file.exists()) {
                Log.i(TAG, "exists: " + file.exists());
                return true;
            }
            if (file.createNewFile()) {
                Log.e(TAG, "checkFile: " + file.exists() );
            }
            Log.i(TAG, "checkFile: " + file.exists());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
