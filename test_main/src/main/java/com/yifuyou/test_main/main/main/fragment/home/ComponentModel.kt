/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Just for learn.
 */

package com.yifuyou.test_main.main.main.fragment.home

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alibaba.android.arouter.launcher.ARouter
import com.yifuyou.test_main.main.main.utls.CommandUtil


/**
 * info
 *
 * @author  Administrator
 * @since 2023/12/1
 */
class ComponentModel : ViewModel(){
    var dataItem : MutableLiveData<PowerDataItem> = MutableLiveData()

    var context : Context?
        get() {return context?.applicationContext}
        set(value) {
        }

    var icon : Int = 0

    companion object {
        @BindingAdapter("iconSrc")
        @JvmStatic
        fun loadImage(view : ImageView,  icon : Int?) {
            Log.i("TAG", "loadImage: $icon")
            icon?.let {
                view.setColorFilter(Color.BLUE)
                view.setImageResource(icon)
            }
        }
    }

    fun setDataItem(item: PowerDataItem) {
        dataItem.value = item
        setIcon()
    }

    private fun setIcon() {
        if(dataItem.value != null) {
            icon = CommandUtil.getDrawableId(dataItem.value!!.icon)
        }
    }

    fun longClick() : Boolean {
        if(context == null) return false
        Toast.makeText(context, dataItem.value!!.info, Toast.LENGTH_SHORT).show()
        return true
    }

    fun click() {
        if ("self" == dataItem.value!!.type) {
            ARouter.getInstance().build(dataItem.value!!.url)
                .navigation()
        } else {
            ARouter.getInstance().build("/web/main/")
                .withString("url", dataItem.value!!.url)
                .navigation()
        }
    }

    override fun onCleared() {
        context = null
        super.onCleared()
    }
}