package com.yifuyou.web.load;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.HashMap;

public class LoadHandler extends Handler {
    public static final String TAG = "LoadHandler";

    private static LoadHandler instance;

    private static final Object handleInitLock = new Object();

    private static HashMap<String, CallBack> callBacks;

    private static final Thread handlerThread = new WebLoadThread() {
        @Override
        public void run() {
            super.run();
            Looper.prepare();
            instance = new LoadHandler(Looper.myLooper());
            Looper.loop();
        }
    };

    private LoadHandler(Looper looper) {
        super(looper);
        callBacks = new HashMap<>();
    }

    public static void init() {
        if (instance != null) {
            return;
        }

        synchronized (handleInitLock) {
            if (instance == null) {
                handlerThread.start();
                try {
                    handleInitLock.wait(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                throw new NullPointerException("already init!");
            }
        }
    }

    public static LoadHandler getInstance(){
        if (instance != null) {
            return instance;
        }
        throw new NullPointerException("handler never init");
    }

    public static boolean addCallback(String loadId, CallBack callback) {
        if (callBacks == null) {
            Log.e(TAG, "addCallback:callBacks never init" );
            return false;
        }
        if (callBacks.containsKey(loadId)) {
            Log.e(TAG, "addCallback: already contain this loadId" );
            return false;
        }
        callBacks.put(loadId, callback);
        return true;
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
        Bundle data = msg.getData();
        String loadId = data.getString("loadId");

        Log.i(TAG, "handleMessage: " + msg.toString());
        if( !TextUtils.isEmpty(loadId)) {
            CallBack objectCallBack = callBacks.get(loadId);
            if (objectCallBack != null) {
                objectCallBack.callback(msg);
            }
        }

    }

    public interface CallBack {
        void callback(Message msg);
    }
}
