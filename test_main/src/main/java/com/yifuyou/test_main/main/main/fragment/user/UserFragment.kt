/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Just for learn.
 */

package com.yifuyou.test_main.main.main.fragment.user

import com.alibaba.android.arouter.facade.annotation.Route
import com.yifuyou.test_main.R
import com.yifuyou.test_main.databinding.FragmentUserBinding
import com.yifuyou.test_main.main.main.FragmentItemName
import com.yifuyou.test_main.main.main.FragmentRouterPath
import com.yifuyou.test_main.main.main.fragment.BaseFragment

@Route(path = FragmentRouterPath.Home.PAGE_USER)
class UserFragment : BaseFragment<FragmentUserBinding>() {

    override fun getFragmentTag(): String {
        return FragmentItemName.我的.toString()
    }

    override fun getLayoutId(): Int = R.layout.fragment_user

    override fun initView() {
        print("")
    }

    override fun initObject() {
        print("")
    }



}