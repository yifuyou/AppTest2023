package com.yifuyou.test_main.main.main.fragment

import com.alibaba.android.arouter.facade.annotation.Route
import com.yifuyou.test_main.R
import com.yifuyou.test_main.databinding.FragmentComunityBinding
import com.yifuyou.test_main.main.main.FragmentItemName
import com.yifuyou.test_main.main.main.FragmentRouterPath

@Route(path = FragmentRouterPath.Home.PAGE_COMMUNICAT)
class CommunityFragment : BaseFragment<FragmentComunityBinding>() {

    override fun getFragmentTag(): String {
        return FragmentItemName.通知.toString()
    }

    override fun getLayoutId(): Int = R.layout.fragment_comunity

    override fun initView() {
        print("")
    }

    override fun initObject() {
        print("")
    }


}