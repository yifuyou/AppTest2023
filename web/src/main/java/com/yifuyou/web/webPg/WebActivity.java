/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Just for learn.
 */

package com.yifuyou.web.webPg;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.yifuyou.web.Constants;
import com.yifuyou.web.R;
import com.yifuyou.web.databinding.WebLayoutBinding;
import com.yifuyou.web.db.SharedPreferenceUtil;
import com.yifuyou.web.load.LoadHandler;
import com.yifuyou.web.loadPg.LoadPageActivity;
import com.yifuyou.web.util.DownloadUtil;
import com.yifuyou.web.util.FileUtils;

import java.util.Locale;

@Route(path = "/web/main/")
public class WebActivity extends AppCompatActivity {
    private static final String TAG = "WebActivity";

    public static final String DEFAULT_URL = "https://www.baidu.com/";

    @Autowired(name = "url")
    String intentUrl = "";

    WebLayoutBinding dataBinding;

    ProgressBar progressBar;

    WebView webView;

    WebSettings webSettings;

    String url = DEFAULT_URL;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.web_layout);
        progressBar = dataBinding.progressBar;
        progressBar.setVisibility(View.GONE);
        webView = new WebView(getApplicationContext());

        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.MATCH_PARENT);
        webView.setElevation(-1);
        addContentView(webView, layoutParams);


        webSettings = webView.getSettings();

        // 设置自适应屏幕
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);

        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//关闭webview中缓存
        webSettings.setAllowFileAccess(true);// 设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);//支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true);// 支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");

        webSettings.setDatabaseEnabled(true);   //开启 database storage API 功能
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);//开启 Application Caches 功能
        webSettings.setDomStorageEnabled(true);// 开启 DOM storage API 功能

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                String url = request.getUrl().toString();
                Log.i("TAG", "shouldOverrideUrlLoading: " + url);
                if (url.startsWith("http://") || url.startsWith("https://")) {
                    view.loadUrl(request.getUrl().toString());
                } else {
                    Toast.makeText(WebActivity.this, "已拒绝非网页请求", Toast.LENGTH_SHORT).show();
                }
                return true;
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
                Log.i("TAG", "onLoadResource: " + url);
            }

        });

        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                Log.i("TAG", "onConsoleMessage: "+ consoleMessage.message());
                return super.onConsoleMessage(consoleMessage);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                Log.i("TAG", "onProgressChanged: " + newProgress);
                super.onProgressChanged(view, newProgress);
                if (progressBar.getVisibility() == View.VISIBLE) {
                    progressBar.setProgress(newProgress);
                    if(newProgress>=100){
                        progressBar.setVisibility(View.GONE);
                    }
                } else if(newProgress<100){
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setProgress(newProgress);
                }
            }

        });

        checkPermission();

        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                Log.i("DownloadListener", "onDownloadStart: " + url);
                Log.i("DownloadListener", ": " + userAgent);
                Log.i("DownloadListener", ": " + contentDisposition);
                Log.i("DownloadListener", ": " + mimetype);
                Log.i("DownloadListener", ": " + contentLength);


                String fileName = getFileName(contentDisposition);
                Log.i("DownloadListener", "onDownloadStart: fName=" + fileName);

                fileName = FileUtils.checkAndGetNextFileName(FileUtils.getStoredPath(), fileName);

                Toast.makeText(WebActivity.this, "start load " + fileName, Toast.LENGTH_SHORT).show();

                String loadId = DownloadUtil.buildLoader(url, userAgent, contentDisposition, fileName, contentLength);
                loadAction(loadId, contentLength);
                DownloadUtil.start(loadId);
            }
        });

        ARouter.getInstance().inject(this);
        trySetUrl(intentUrl);

        dataBinding.downloadingBox.setVisibility(View.GONE);

        SharedPreferenceUtil.getOrCreateSp(getBaseContext(), DownloadUtil.TASK_RECORD, Context.MODE_PRIVATE);
    }

    private String getFileName(String contentDisposition) {
        String name_tag_str = "filename=";
        int start = contentDisposition.indexOf(name_tag_str);
        if (start<0) {
            return "";
        }
        return contentDisposition.substring(start+name_tag_str.length()).replace("\"", "");
    }

    private void trySetUrl(String urlStr) {
        if (TextUtils.isEmpty(urlStr)) {
            return;
        }
        String str = urlStr.toLowerCase(Locale.ROOT);
        if (str.startsWith("http://") || str.startsWith("https://")) {
            url = urlStr;
        } else {
            url = DEFAULT_URL + "s?ie=utf-8&wd=" + urlStr;
        }
    }

    private void loadStart() {
        dataBinding.downloadingBox.setVisibility(View.VISIBLE);
        dataBinding.downloadingPBar.setVisibility(View.VISIBLE);
        dataBinding.imgIcon.setColorFilter(Color.argb(0,0,0,0));
        dataBinding.imgIcon.setOnClickListener( v -> {
                Intent intent = new Intent(this, LoadPageActivity.class);
                startActivity(intent);
            }
        );
    }

    private void loading(int process) {
        dataBinding.imgIcon.setColorFilter(Color.argb(process%255, process%255, process%255, process%255));
        dataBinding.downloadingPBar.setProgress(process, false);
    }

    private void loadFinish(){
        dataBinding.imgIcon.setColorFilter(Color.alpha(0));
        dataBinding.imgIcon.setClickable(false);
        dataBinding.downloadingPBar.setVisibility(View.GONE);

    }

    private void loadAction(String loadId, long maxCount) {
        LoadHandler.addCallback(loadId, new LoadHandler.CallBack() {
            @Override
            public void callback(Message msg) {
                Log.i(TAG, "callback:" + msg.toString());
                Bundle data = msg.getData();
                String msgId = data.getString("loadId");
                if(TextUtils.isEmpty(msgId) || !msgId.equals(loadId)) {
                    Log.i(TAG, "callback: msgId" + msgId);
                    return;
                }

                switch (msg.what) {
                    case Constants.TAG_START_LOAD:
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                loadStart();
                            }
                        });
                        break;

                    case Constants.TAG_LOADING_FINISH:
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                loadFinish();
                            }
                        });
                        break;
                    case Constants.TAG_LOADING_REPORT:
                        long cc = data.getLong("now");
                        int deliver = maxCount > 10000 ? 1000 : 100;
                        int progress = (int)(cc < deliver ? 1:cc / (maxCount/deliver));
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                loading(progress);
                            }
                        });
                        break;
                    default:break;
                }

            }
        });
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i("TAG", "onRestoreInstanceState: ");
        String urlStr = savedInstanceState.getString("url");
        trySetUrl(urlStr);
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    public void checkPermission() {
        String[] permissions = {
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.MANAGE_EXTERNAL_STORAGE
        };
        for (String permission : permissions) {
            if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(permissions, 1002);
                Log.i("Permission", "checkPermission:" + permission);
                break;
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        for (int i = 0; i < permissions.length; i++) {
            Log.i("Permission", "onRequestPermissionsResult:" + permissions[i] + " : " + grantResults[i]);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // 支持javaScript
        webSettings.setJavaScriptEnabled(true);

        webView.loadUrl(url);
        Log.i("TAG", "onResume: " + url);
    }

    @Override
    protected void onStop() {
        // 关闭支持javaScript
        webSettings.setJavaScriptEnabled(false);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        webView.loadUrl(null);
        webView=null;
        super.onDestroy();
    }
}
