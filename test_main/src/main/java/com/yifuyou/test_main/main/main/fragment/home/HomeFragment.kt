/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Just for learn.
 */

package com.yifuyou.test_main.main.main.fragment.home

import android.util.Log
import android.view.LayoutInflater
import android.widget.GridLayout
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.yifuyou.test_main.R
import com.yifuyou.test_main.databinding.FragmentHomeBinding
import com.yifuyou.test_main.databinding.HomeComponentLayoutBinding
import com.yifuyou.test_main.main.main.FragmentItemName
import com.yifuyou.test_main.main.main.FragmentRouterPath
import com.yifuyou.test_main.main.main.fragment.BaseFragment
import com.yifuyou.test_main.main.main.placeholder.HomeContent

@Route(path = FragmentRouterPath.Home.PAGE_HOME)
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun getFragmentTag(): String {
        return FragmentItemName.首页.toString()
    }

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun initView() {
        val maxCount = HomeContent.count
        with(dataBinding.gridLayout) {
            columnCount = 4
            rowCount = when(HomeContent.count % 4 == 0) {
                true -> maxCount / 4

                else -> maxCount / 4 + 1
            }

            for(index in 1..rowCount) {
                for(jIndex in 1..columnCount) {
                    val count = (index-1)*4+jIndex-1
                    if (count >= maxCount) {
                        Log.e("TAG", "initView: $count" )
                        break
                    }
                    val model = ComponentModel()
                    val name = HomeContent.cObjects[count]
                    val dataItem = HomeContent.cData[name]
                    model.setDataItem(dataItem!!)
                    model.context = context

                    val componentBinding =
                        HomeComponentLayoutBinding.inflate(LayoutInflater.from(context), this, false)
                    with(componentBinding) {
                        item = model
                        val params = GridLayout.LayoutParams()
                        params.columnSpec = GridLayout.spec(jIndex-1)
                        params.rowSpec = GridLayout.spec(index-1, 1f)
                        addView(root, params)
                    }
                }
            }

        }
        dataBinding.searchBtn.setOnClickListener {
            val text :String = dataBinding.searchEt.text.toString()
            ARouter.getInstance().build("/web/main/")
                .withString("url", text)
                .navigation()
        }
    }

    override fun initObject() {

    }


}