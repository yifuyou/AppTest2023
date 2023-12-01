package com.yifuyou.test_main.main.main.placeholder

import android.app.Application
import com.google.gson.GsonBuilder
import com.yifuyou.test_main.main.main.fragment.home.PowerDataItem
import org.json.JSONObject


object HomeContent {

    val cObjects : MutableList<String> = ArrayList()

    val cData : MutableMap<String, PowerDataItem> = HashMap()

    var count : Int = 0

    fun initData(context: Application) {
        val jsonAss = context.assets.open("PowerComponent.json")
        val available = jsonAss.available()
        val bytes : ByteArray = ByteArray(available)

        val readNBytes = jsonAss.read(bytes)
        if (readNBytes <= 0) return
        val jsonObject = JSONObject(String(bytes))
        val count = jsonObject.getInt("listCount")

        val array = jsonObject.getJSONArray("list")
        if (array.length() >= count)
        for (index in 0 until count) {
            addNewItem(GsonBuilder().create().fromJson(array.get(index).toString(), PowerDataItem::class.java))
        }
    }

    fun addNewItem(item : PowerDataItem) {
        if (cObjects.contains(item.name)) {
            cObjects.remove(item.name)
        }

        cObjects.add(item.name)
        cData[item.name] = item
        count = cObjects.size
    }
}