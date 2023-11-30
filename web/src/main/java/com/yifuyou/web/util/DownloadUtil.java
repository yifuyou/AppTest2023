/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Just for learn.
 */

package com.yifuyou.web.util;

import android.util.Log;

import androidx.annotation.NonNull;

import com.yifuyou.web.Constants;
import com.yifuyou.web.db.DataBaseUtil;
import com.yifuyou.web.db.SharedPreferenceUtil;
import com.yifuyou.web.load.LoadFileRecord;
import com.yifuyou.web.load.LoadThread;
import com.yifuyou.web.load.PoolFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DownloadUtil {
    private static final String TAG = "DownloadUtil";

    public static final String TASK_RECORD = "SP_LOAD_FILE_RECORD";

    private final static HashMap<String, LoadThread> LOAD_THREAD_HASH_MAP = new HashMap<>();

    public static String buildLoader(String url, String userAgent, String contentDisposition, String fileName, long contentLength) {
        LoadThread loadThread = LoadThread.Builder()
                .setUrl(url)
                .setUserAgent(userAgent)
                .setContentDisposition(contentDisposition)
                .setContentLength(contentLength);
        loadThread.setFileInfo(LoadFileRecord.Builder(fileName, contentLength, FileUtils.getStoredPath()+fileName));
        String loadId = loadThread.buildTask();
        LOAD_THREAD_HASH_MAP.put(loadId, loadThread);
        return loadId;
    }

    public static void start(String loadId) {
        if (LOAD_THREAD_HASH_MAP.containsKey(loadId)) {
            LoadThread runnable = LOAD_THREAD_HASH_MAP.get(loadId);
            PoolFactory.postTask(runnable);
        }
    }

    public static boolean resumeLoad(@NonNull LoadFileRecord loadFileRecord) {
        if (loadFileRecord.isLoading() || loadFileRecord.isFinished()) {
            Log.i(TAG, "resumeLoad: not need resume.");
            return false;
        }

        LoadThread loadThread = LoadThread.Builder()
                .setUrl(loadFileRecord.getUrl())
                .setContentLength(loadFileRecord.getFileLength());
        String loadId = loadThread.buildTask();
        loadThread.setFileInfo(loadFileRecord);
        LOAD_THREAD_HASH_MAP.put(loadId, loadThread);

        PoolFactory.postTask(loadThread);
        return true;
    }

    public static List<LoadFileRecord> getLoadingRecord() {
        ArrayList<LoadFileRecord> list = new ArrayList<>();
        LOAD_THREAD_HASH_MAP.forEach((id, loadT)-> {
            list.add(loadT.getFileInfo());
        });
        return list;
    }

    public static void release() {
        LOAD_THREAD_HASH_MAP.forEach((id, loadT)-> {
            loadT.getFileInfo().setState(Constants.STATE_STRING_LOAD_SUCCESS);
            DataBaseUtil.updateLoadFileRecordTime(loadT.getFileInfo());
        });

        SharedPreferenceUtil.release();
    }
}
