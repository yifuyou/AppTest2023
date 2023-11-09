package com.yifuyou.test_main.main.main.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yifuyou.test_main.R
import com.yifuyou.test_main.databinding.FragmentItemListBinding
import com.yifuyou.test_main.main.main.adapter.FindPgMoreAdapter
import com.yifuyou.test_main.main.main.placeholder.MoreContent

class FindPgMoreFragment : BaseFragment<FragmentItemListBinding>() {
    override fun getFragmentTag(): String {
        return "无限可能"
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_item_list
    }

    override fun initView() {
        dataBinding.listTag.text = getFragmentTag()
        val list = dataBinding.list
        with(list) {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = FindPgMoreAdapter(MoreContent.ITEMS)
        }
    }

    override fun initObject() {
        print("")
    }
}