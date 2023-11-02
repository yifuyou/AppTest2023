package com.yifuyou.test_main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.alibaba.android.arouter.launcher.ARouter
import com.yifuyou.test_main.main.view1.TestActivity1

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState != null) {
            return
        }
        findViewById<Button>(R.id.main_btn_1).setOnClickListener(this)
        findViewById<Button>(R.id.main_btn_2).setOnClickListener(this)
        findViewById<Button>(R.id.main_btn_3).setOnClickListener(this)

    }

    override fun onClick(btn: View?) {
        println("button is click ${btn?.id}")
        when(btn?.id) {
            R.id.main_btn_1->{
                val intent = Intent(applicationContext, TestActivity1::class.java)
                startActivity(intent)
            }
            R.id.main_btn_2->{
                println("try ARouter>>>>>>>>>>")
                ARouter.getInstance().build("/test/activity2/home").navigation()
//                ARouter.getInstance().build("/test2/activity2").navigation()
//                ARouter.getInstance().build("/error/activity").navigation()
                println("try ARouter>>>>>>>>>>")
            }
            R.id.main_btn_3->{
                ARouter.getInstance().build("/main/splash").navigation()
            }
        }
    }
}