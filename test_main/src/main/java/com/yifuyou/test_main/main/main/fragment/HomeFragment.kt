package com.yifuyou.test_main.main.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.facade.annotation.Route
import com.yifuyou.test_main.R
import com.yifuyou.test_main.main.main.FragmentItemName
import com.yifuyou.test_main.main.main.FragmentRouterPath

@Route(path = FragmentRouterPath.Home.PAGE_HOME)
class HomeFragment : BaseFragment() {

    override fun getFragmentTag(): String {
        return FragmentItemName.首页.toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

}