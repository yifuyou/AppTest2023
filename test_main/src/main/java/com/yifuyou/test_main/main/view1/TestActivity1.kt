package com.yifuyou.test_main.main.view1

import android.animation.ValueAnimator
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.yifuyou.test_main.R

class TestActivity1 : AppCompatActivity()  {

    var menuViews = ArrayList<ImageButton>()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_activity_layout)

        val img_1 = findViewById<ImageButton>(R.id.imageButton_main_act1)
        val img_2 = findViewById<ImageButton>(R.id.imageButton_main_act2)
        val img_3 = findViewById<ImageButton>(R.id.imageButton_main_act3)
        menuViews.add(img_1)
        menuViews.add(img_2)
        menuViews.add(img_3)

        findViewById<ImageButton>(R.id.imageButton_main).setOnClickListener {
            Log.i("zjf", "click ${it.id}")
            imgClicked()
        }

    }
    var isOpen = true;
    fun imgClicked() : Unit{
        val startRadius = dpToPixel(if (isOpen) 90 else 0)
        val endRadius = dpToPixel(if (isOpen) 0 else 90)
        if (endRadius != 0) {
            menuViews.forEach{ view ->
                view.visibility = View.VISIBLE
            }
        }
        val anim = ValueAnimator.ofInt(startRadius, endRadius)
        anim.duration = 300
        anim.addUpdateListener { valueAnimator ->
            val radius: Int = valueAnimator.animatedValue as Int
            menuViews.forEach { view ->
                val lp = view.layoutParams as ConstraintLayout.LayoutParams
                lp.circleRadius = radius
                view.layoutParams = lp
            }
            if (endRadius == 0 && radius < 20) {
                menuViews.forEach{ view ->
                    view.visibility = View.GONE
                }
            }
        }
        anim.start()
        isOpen = !isOpen
    }

    fun dpToPixel(dp: Int): Int {
        val displayMetrics = this.resources.displayMetrics
        return if (dp < 0) dp else Math.round(dp * displayMetrics.density)
    }

}