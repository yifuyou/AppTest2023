package com.yifuyou.test_main.main.main.fragment

import com.alibaba.android.arouter.facade.annotation.Route
import com.yifuyou.test_main.R
import com.yifuyou.test_main.databinding.FragmentFindBinding
import com.yifuyou.test_main.main.main.FindFragmentProvider
import com.yifuyou.test_main.main.main.FragmentItemName
import com.yifuyou.test_main.main.main.FragmentRouterPath

@Route(path = FragmentRouterPath.Home.PAGE_FIND)
class FindFragment : BaseFragment<FragmentFindBinding>() {

    override fun getFragmentTag(): String {
        return FragmentItemName.发现.toString()
    }

    override fun getLayoutId(): Int = R.layout.fragment_find

    override fun initView() {
        val beginTransaction = childFragmentManager.beginTransaction()
        FindFragmentProvider().allFragments[0]?.let {
            beginTransaction.add(R.id.fragment_place_1, it)
        }
        FindFragmentProvider().allFragments[1]?.let {
            beginTransaction.add(R.id.fragment_place_2, it)
        }
        beginTransaction.commit()
    }

    override fun initObject() {
        print("")
    }

}