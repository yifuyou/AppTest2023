package com.yifuyou.test_main.main.main

import androidx.databinding.ViewDataBinding
import com.yifuyou.test_main.main.main.fragment.BaseFragment
import com.yifuyou.test_main.main.main.fragment.find.FindPgMoreFragment
import com.yifuyou.test_main.main.main.fragment.find.FindPgPopularKindItemFragment

class FindFragmentProvider {
    val allFragments : HashMap<Int, BaseFragment<out ViewDataBinding>>
    init {
        allFragments = HashMap()
        allFragments.put(0, FindPgPopularKindItemFragment())
        allFragments.put(1, FindPgMoreFragment())
    }

    var count : Int = allFragments.size

}