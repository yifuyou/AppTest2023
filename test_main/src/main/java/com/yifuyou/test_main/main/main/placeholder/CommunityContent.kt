package com.yifuyou.test_main.main.main.placeholder

import com.yifuyou.test_main.main.main.utls.TimeUtil

object CommunityContent {
    val cObjects : MutableList<String> = ArrayList()

    val cData : MutableMap<String, MutableList<DataItem>> = HashMap()

    var count : Int = 0

    init {
        for (ac in 1..30) {
            for (dc in 1..60) {
                createNewData("某某${ac}", TimeUtil.getTimeString(), "收到消息11", 0)
            }
        }
        count = cObjects.size
    }

    private fun addItem(objId:String, item:DataItem) {
        var dataItems : MutableList<DataItem>? = null
        if (cObjects.contains(objId)) {
            cObjects.remove(objId)
            dataItems = cData[objId]
        }
        if (dataItems == null) {
            dataItems = ArrayList()
        }
        dataItems.add(item)
        cObjects.add(objId)
        cData[objId] = dataItems
    }

    fun createNewData(objId: String, time : Long, msg : String, type : Int) {
        addItem(objId, DataItem(time, msg, type))
    }

    data class DataItem(var time : Long, var msg : String, var type : Int) {
        override fun toString(): String {
            return super.toString()
        }
    }
}