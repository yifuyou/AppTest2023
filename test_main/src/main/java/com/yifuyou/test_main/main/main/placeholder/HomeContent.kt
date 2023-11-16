package com.yifuyou.test_main.main.main.placeholder

import com.yifuyou.test_main.R

object HomeContent {

    val cObjects : MutableList<String> = ArrayList()

    val cData : MutableMap<String, DataItem> = HashMap()

    var count : Int = 0

    init {
        for(i in 1..8){
            createNewData("功能${i}", "info${i}", R.drawable.main_notify)
        }
    }

    // 首页能力
    data class DataItem(var name : String, var info : String, var icon : Int) {
        override fun toString(): String {
            return super.toString()
        }
    }

    fun createNewData(name : String, info : String, icon : Int) {
        val item = DataItem(name, info, icon)

        if (cObjects.contains(name)) {
            cObjects.remove(name)
        }

        cObjects.add(name)
        cData[name] = item
        count = cObjects.size
    }
}