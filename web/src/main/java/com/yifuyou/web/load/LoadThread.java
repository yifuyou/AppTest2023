package com.yifuyou.web.load;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoadThread {
    private static final String TAG = "LoadThread";
    
    // 下载任务的id, 用于区分下载事件
    private final String loadId = CommandUtil.getRandomId(20);

    private String url="";

    private String userAgent = "";

    private String contentDisposition = "";

    private String mimetype = "";

    private long contentLength = 0L;

    //上次上报时间
    private long lastReportTime = 0L;

    //进度上报间隔时间
    private final long reportApartTime = 1000L;

    private Runnable runnable;

    private LoadThread(){}

    public static LoadThread Builder(){
        return new LoadThread();
    }

    public LoadFileInfo getFileInfo() {
        return fileInfo;
    }

    public void setFileInfo(LoadFileInfo fileInfo) {
        this.fileInfo = fileInfo;
    }

    private LoadFileInfo fileInfo;

    private boolean hasInit = false;

    public String getUrl() {
        return url;
    }

    public LoadThread setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public LoadThread setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    public String getContentDisposition() {
        return contentDisposition;
    }

    public LoadThread setContentDisposition(String contentDisposition) {
        this.contentDisposition = contentDisposition;
        return this;
    }

    public String getMimetype() {
        return mimetype;
    }

    public LoadThread setMimetype(String mimetype) {
        this.mimetype = mimetype;
        return this;
    }

    public long getContentLength() {
        return contentLength;
    }

    public LoadThread setContentLength(long contentLength) {
        this.contentLength = contentLength;
        return this;
    }

    public Runnable getRunnable() {
        return runnable;
    }

    public String buildTask() {
        if(hasInit) {
            Log.e(TAG, "buildTask has init");
            return loadId;
        }
        hasInit = true;
        runnable = new Runnable() {
            @Override
            public void run() {
                File file = new File(fileInfo.filePath + fileInfo.fileName);
                checkFile(file);
                OkHttpClient httpClient = new OkHttpClient();
                Request request = new Request.Builder().url(url).get().build();
                Call call = httpClient.newCall(request);

                Response execute = null;
                FileOutputStream fileWriter = null;
                try {
                    execute = call.execute();
                    if (execute.body() == null) {
                        throw new NullPointerException("execute.body");
                    }
                    fileWriter = new FileOutputStream(file);

                    long length = execute.body().contentLength();
                    long cl = 0;

                    sendMessage(Constants.TAG_START_LOAD, loadId, 0);
                    while (cl<length) {
                        InputStream is = execute.body().byteStream();
                        byte[] data = new byte[10000];
                        int readCount = is.read(data);

                        fileWriter.write(data, 0, readCount);

                        cl += readCount;
                        sendMessage(Constants.TAG_LOADING_REPORT, loadId, cl);
                    }

                    sendMessage(Constants.TAG_LOADING_FINISH, loadId, -1);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (fileWriter != null) {
                            fileWriter.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        };
        return loadId;
    }

    private void checkFile(File file) {
        Log.i(TAG, "checkFile: " + file.getPath());
        try {
            if (file.exists()) {
                Log.i(TAG, "exists: " + file.exists());
                return;
            }
            if (file.createNewFile()) {
                Log.e(TAG, "checkFile: " + file.exists() );
            }
            Log.i(TAG, "checkFile: " + file.exists());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(int what, String loadId, long now) {
        if (what == Constants.TAG_LOADING_REPORT) {
            if (System.currentTimeMillis() - lastReportTime < reportApartTime) {
                return;
            }
            lastReportTime = System.currentTimeMillis();
        }

        Message msg = Message.obtain(LoadHandler.getInstance());
        msg.what = what;
        Bundle bundle = msg.getData();
        bundle.putCharSequence("loadId", loadId);
        bundle.putLong("now", now);
        msg.setData(bundle);
        Log.i(TAG, "sendMessage: " + msg.toString());
        LoadHandler.getInstance().sendMessage(msg);
    }
}
