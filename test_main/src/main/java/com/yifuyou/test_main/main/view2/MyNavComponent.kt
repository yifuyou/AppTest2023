package com.yifuyou.test_main.main.view2

import android.graphics.Color
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.Nullable
import androidx.constraintlayout.widget.ConstraintLayout

class MyNavComponent(var layout:LinearLayout) {
    init {
    }

    var items = HashMap<String, Item>()

    fun addItem(tag:String, text:String) {
        this.addItem(tag, text, 0, 0, null)
    }
    fun addItem(tag:String, text:String, @DrawableRes srcId: Int, @ColorInt drawableId:Int, clickListener: View.OnClickListener?) {
        val item = Item(tag, text, srcId)
        if (drawableId == 0) {
            item.isDefaultDrawable =  true
            item.drawableId = 0
        } else {
            item.drawableId = drawableId
            item.isDefaultDrawable = false
        }
        item.clickListener = clickListener
        items[item.tag] = item
        initView(item)
    }

    private fun initView(item: Item) {
        val itemView = ConstraintLayout(layout.context)
        itemView.tag = item.tag

        // 添加图片
        if (!item.isDefaultDrawable) {
            val imbBtnLp = ConstraintLayout.LayoutParams(0,0)
            imbBtnLp.topToTop = ConstraintLayout.LayoutParams.PARENT_ID
            imbBtnLp.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
            imbBtnLp.matchConstraintPercentHeight = 0.8F
            imbBtnLp.dimensionRatio="1:1"
            imbBtnLp.constrainedHeight = true
            imbBtnLp.constrainedWidth= true
            imbBtnLp.verticalBias = 0.14F
            imbBtnLp.startToStart = ConstraintLayout.LayoutParams.PARENT_ID
            imbBtnLp.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID

            val imgBtn = ImageButton(layout.context)
            imgBtn.setImageResource(item.srcId)
            imgBtn.setColorFilter(item.drawableId)
            imgBtn.setBackgroundColor(Color.TRANSPARENT)
            imgBtn.scaleType= ImageView.ScaleType.FIT_CENTER
            itemView.addView(imgBtn, imbBtnLp)
            imgBtn.isClickable = false
        }

        // 添加标签
        val textView = TextView(layout.context)
        textView.text = item.text
        val textLayoutParam = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT,0)
        textLayoutParam.topToTop = ConstraintLayout.LayoutParams.PARENT_ID
        textLayoutParam.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
        textLayoutParam.matchConstraintPercentHeight = 0.2F
        textLayoutParam.constrainedHeight = true
        textLayoutParam.constrainedWidth= true
        textLayoutParam.verticalBias = 0.85F
        textLayoutParam.startToStart = ConstraintLayout.LayoutParams.PARENT_ID
        textLayoutParam.endToEnd = ConstraintLayout.LayoutParams.PARENT_ID
        itemView.addView(textView, textLayoutParam)

        val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT)
        layoutParams.weight = 1.0F
        layout.addView(itemView, layoutParams)
        itemView.setOnClickListener(item.clickListener)
    }

    fun removeItem(tag: String) {
        if (!items.containsKey(tag)) {
            return
        }
        val findViewWithTag = layout.findViewWithTag<ConstraintLayout>(tag)
        layout.removeView(findViewWithTag)
        items.remove(tag)
    }

    class Item(var tag:String, var text:String, @DrawableRes var srcId: Int) {
        var drawableId = 0
        var isDefaultDrawable = true
        var clickListener: View.OnClickListener? = null
    }
}