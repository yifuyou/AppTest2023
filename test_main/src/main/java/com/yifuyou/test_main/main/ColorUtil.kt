package com.yifuyou.test_main.main

import android.content.Context
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat

class ColorUtil {
    @ColorInt
    fun getColor(context: Context?, colorId: Int): Int {
        return ContextCompat.getColor(context!!, colorId)
    }
}