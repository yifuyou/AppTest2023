/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Just for learn.
 */

package com.yifuyou.test_main.main.main.fragment.find

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yifuyou.test_main.R
import com.yifuyou.test_main.databinding.FragmentPopularKindItemListBinding
import com.yifuyou.test_main.main.main.adapter.FindPgPopularKindItemRvAdapter
import com.yifuyou.test_main.main.main.fragment.BaseFragment
import com.yifuyou.test_main.main.main.placeholder.PlaceholderContent

/**
 * A fragment representing a list of Items.
 */
class FindPgPopularKindItemFragment : BaseFragment<FragmentPopularKindItemListBinding>(){

    override fun getFragmentTag(): String = "热门分类"

    override fun getLayoutId(): Int = R.layout.fragment_popular_kind_item_list
    private var columnCount = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun initView() {
        // 设置标签
        dataBinding.listTag.text = getFragmentTag()
        dataBinding.listTagAppend.visibility = View.VISIBLE

        val view = dataBinding.ddList
        with(view) {
            layoutManager = when {
                columnCount <= 1 -> LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
                else -> GridLayoutManager(context, columnCount, RecyclerView.HORIZONTAL, false)
            }
            adapter = FindPgPopularKindItemRvAdapter(childFragmentManager, PlaceholderContent.ITEMS)
        }
    }

    override fun initObject() {
        print("")
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            FindPgPopularKindItemFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}