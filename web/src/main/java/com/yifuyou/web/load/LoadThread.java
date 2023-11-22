package com.yifuyou.web.load;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.yifuyou.web.load.db.DataBaseUtil;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 *
 * @author Administrator
 * @since 2023/11/10
 */
public class LoadThread {
    private static final String TAG = "LoadThread";
    
    // 下载任务的id, 用于区分下载事件
    private final String loadId = CommandUtil.getRandomId(20);

    private String url="";

    private String userAgent = "";

    private String contentDisposition = "";

    private String mimetype = "";

    private long contentLength = 0L;

    //上次下载进度
    private long lastLength = 0L;

    //上次上报时间
    private long lastReportTime = 0L;

    //进度上报间隔时间
    private final long reportApartTime = 1000L;

    private Runnable runnable;

    private LoadFileRecord fileInfo;

    private boolean hasInit = false;

    private boolean isReLoad = false;

    private LoadThread(){}

    public static LoadThread Builder(){
        return new LoadThread();
    }

    public LoadFileRecord getFileInfo() {
        return fileInfo;
    }

    public void setFileInfo(LoadFileRecord fileInfo) {
        this.fileInfo = fileInfo;
        if (fileInfo.getUrl().isEmpty()) {
            this.fileInfo.setUrl(url);
        } else {
            url = fileInfo.getUrl();
        }

    }

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

    public long getLatestTime() {
        return lastReportTime;
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
                Log.i(TAG, "run: start loading " + fileInfo.getName());
                if (!FileUtils.createFile(fileInfo.getPath())) {
                    Log.e(TAG, "run: file create fail");
                    return;
                }
                fileInfo.setsTime(System.currentTimeMillis());
                DataBaseUtil.insertLoadFileRecord(fileInfo);
                File file = new File(fileInfo.getPath());
                OkHttpClient httpClient = new OkHttpClient();

                Request request = new Request.Builder().url(url)
                        .header("Range", "bytes=" + fileInfo.getLoadLength() + "-" + fileInfo.getFileLength())
                        .get()
                        .build();
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

    private void sendMessage(int what, String loadId, long now) {
        if (what == Constants.TAG_LOADING_REPORT) {
            fileInfo.setLoadLength(lastLength + now);
            if (System.currentTimeMillis() - lastReportTime < reportApartTime) {
                return;
            }
        } else if(what == Constants.TAG_START_LOAD) {
            isReLoad = fileInfo.getLoadLength() != 0L;
            lastLength = fileInfo.getLoadLength();
            fileInfo.setState(Constants.STATE_STRING_LOADING);
        } else if(what == Constants.TAG_LOADING_FINISH) {
            fileInfo.setState(Constants.STATE_STRING_LOAD_SUCCESS);
        }

        lastReportTime = System.currentTimeMillis();
        fileInfo.setlTime(lastReportTime);
        Message msg = Message.obtain(LoadHandler.getInstance());
        msg.what = what;
        Bundle bundle = msg.getData();
        bundle.putCharSequence("loadId", loadId);
        bundle.putLong("now", now);
        msg.setData(bundle);
        Log.i(TAG, "sendMessage: " + msg.toString());
        LoadHandler.getInstance().sendMessage(msg);
        DataBaseUtil.updateLoadFileRecordTime(fileInfo);
    }
}
