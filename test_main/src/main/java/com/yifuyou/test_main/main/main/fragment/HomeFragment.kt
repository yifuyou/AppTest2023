package com.yifuyou.test_main.main.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Route
import com.yifuyou.test_main.BR
import com.yifuyou.test_main.R
import com.yifuyou.test_main.databinding.FragmentHomeBinding
import com.yifuyou.test_main.main.main.FragmentItemName
import com.yifuyou.test_main.main.main.FragmentRouterPath

@Route(path = FragmentRouterPath.Home.PAGE_HOME)
class HomeFragment : BaseFragment() {

    override fun getFragmentTag(): String {
        return FragmentItemName.首页.toString()
    }

    lateinit var dataBinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate<FragmentHomeBinding>(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )
        dataBinding.setVariable(BR.myText,"first init")
        return dataBinding.root
//        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onResume() {
        super.onResume()
        Thread {
            for (ic in 1..100000 step 100) {
                dataBinding.setVariable(BR.myText, "set dataBind value ${ic}")
                Thread.sleep(150L)
            }
        }.start()


    }
}