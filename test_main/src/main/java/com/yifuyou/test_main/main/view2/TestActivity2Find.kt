package com.yifuyou.test_main.main.view2

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.yifuyou.test_main.R
import com.yifuyou.test_main.main.main.utls.ColorUtil

@Route(path = "/test/activity2/find", name = "ARouter 测试页")
class TestActivity2Find  : AppCompatActivity(){
    enum class NavTag{
        HOME, FIND, USER
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_activity_layout_2_find)

        val nav = MyNavComponent(findViewById<LinearLayout>(R.id.bottom_nav))

        nav.addItem( NavTag.HOME.toString(),"首页", R.drawable.main_home,
            ColorUtil().getColor(this, R.color.color_1)
        ) {
            ARouter.getInstance().build("/test/activity2/home").navigation()
        }
        nav.addItem( NavTag.FIND.toString(),"发现", R.drawable.main_community,
            ColorUtil().getColor(this, R.color.color_2), null)
        nav.addItem(NavTag.USER.toString(),"我的",  R.drawable.main_user,
            ColorUtil().getColor(this, R.color.color_1))
        {
            ARouter.getInstance().build("/test/activity2/user").navigation()

        }

    }
}