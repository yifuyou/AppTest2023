package com.yifuyou.testapp3

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.yifuyou.web.WebInitUtil

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG) {
            ARouter.openDebug()
            ARouter.openLog()
        }
        ARouter.init(this)
        WebInitUtil.init(this)
    }

}