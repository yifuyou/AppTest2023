package com.yifuyou.test_main.main.main.placeholder

import com.yifuyou.test_main.R
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.MutableList
import kotlin.collections.MutableMap
import kotlin.collections.set

object MoreContent {

    val ITEMS : MutableList<MoreItem> = ArrayList()

    val ITEM_MAP: MutableMap<String, MoreItem> = HashMap()

    private val COUNT = 16

    init {
        // Add some sample items.
        for (i in 1..COUNT) {
            addItem(createMoreItem(i))
        }
    }

    private fun addItem(item: MoreItem) {
        ITEMS.add(item)
        ITEM_MAP[item.id] = item
    }

    private fun createMoreItem(position: Int): MoreItem {
        return MoreItem(
            position.toString(),
            R.drawable.img_ic_3, "震惊！！ *本国竟然.......... " + position, "虚拟时报", ""
        )
    }


    data class MoreItem(var id:String, var img:Int, var info:String, var from:String, var url:String) {
        override fun toString(): String {
            return MoreItem::class.simpleName.toString() + "[" + "id : " + id + ", info : " + info + ", from : " + from +  ", url : " + url +"]"
        }
    }

}