package com.yifuyou.web.load;

import com.yifuyou.web.load.db.DataBaseUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DownloadUtil {
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
            LoadFileRecord fileInfo = runnable.getFileInfo();
            PoolFactory.postTask(runnable);

            fileInfo.addObserver((o, arg)->{
                if ((long)arg==fileInfo.getFileLength()) {
                    DataBaseUtil.updateLoadFileRecordTime(fileInfo.getName(),
                            Constants.STATE_INT_LOAD_SUCCESS, fileInfo.getlTime());
                }
            });
        }
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
            DataBaseUtil.updateLoadFileRecordTime(loadT.getFileInfo().getName(),
                    Constants.STATE_INT_LOAD_UNFINISH, loadT.getLatestTime());
        });

        SharedPreferenceUtil.release();
    }
}
