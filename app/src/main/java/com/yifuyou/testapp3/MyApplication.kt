package com.yifuyou.testapp3

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.yifuyou.test_main.main.main.placeholder.HomeContent
import com.yifuyou.web.WebInitUtil

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG) {
            ARouter.openDebug()
            ARouter.openLog()
        }
        Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler(ExceptionHandler())
        ARouter.init(this)
        WebInitUtil.init(this)
        HomeContent.initData(this)
    }

}