/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Just for learn.
 */

package com.yifuyou.test_main.main.main.fragment.community

import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.yifuyou.test_main.R
import com.yifuyou.test_main.databinding.FragmentComunityBinding
import com.yifuyou.test_main.main.main.FragmentItemName
import com.yifuyou.test_main.main.main.FragmentRouterPath
import com.yifuyou.test_main.main.main.adapter.CommunityFragmentAdapter
import com.yifuyou.test_main.main.main.fragment.BaseFragment

@Route(path = FragmentRouterPath.Home.PAGE_COMMUNICAT)
class CommunityFragment : BaseFragment<FragmentComunityBinding>() {

    override fun getFragmentTag(): String {
        return FragmentItemName.通知.toString()
    }

    override fun getLayoutId(): Int = R.layout.fragment_comunity

    override fun initView() {
        with(dataBinding.communityList) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = CommunityFragmentAdapter()
        }
    }

    override fun initObject() {
        print("")
    }

}