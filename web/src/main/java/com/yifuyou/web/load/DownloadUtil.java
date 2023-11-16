package com.yifuyou.web.load;


import java.util.HashMap;

public class DownloadUtil {
    private final static HashMap<String, LoadThread> LOAD_THREAD_HASH_MAP = new HashMap<>();

    public static String buildLoader(String url, String userAgent, String contentDisposition, String fileName, long contentLength) {
        LoadThread loadThread = LoadThread.Builder()
                .setUrl(url)
                .setUserAgent(userAgent)
                .setContentDisposition(contentDisposition)
                .setContentLength(contentLength);
        loadThread.setFileInfo(LoadFileInfo.Builder(fileName, contentLength, FileUtils.getStoredPath()));
        String loadId = loadThread.buildTask();
        LOAD_THREAD_HASH_MAP.put(loadId, loadThread);
        return loadId;
    }

    public static void start(String loadId) {
        if (LOAD_THREAD_HASH_MAP.containsKey(loadId)) {
            PoolFactory.postTask(LOAD_THREAD_HASH_MAP.get(loadId));
        }
    }


}
