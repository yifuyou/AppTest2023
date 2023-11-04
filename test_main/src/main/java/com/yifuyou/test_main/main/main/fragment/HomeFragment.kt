package com.yifuyou.test_main.main.main.fragment

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.yifuyou.test_main.BR
import com.yifuyou.test_main.R
import com.yifuyou.test_main.databinding.FragmentHomeBinding
import com.yifuyou.test_main.main.main.FragmentItemName
import com.yifuyou.test_main.main.main.FragmentRouterPath
import kotlinx.coroutines.*

@Route(path = FragmentRouterPath.Home.PAGE_HOME)
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun getFragmentTag(): String {
        return FragmentItemName.首页.toString()
    }

    override fun getLayoutId(): Int = R.layout.fragment_home

    var job : Job? = null

    var ic : Int = 0

    override fun initView() {
        dataBinding.myText = "set text"
    }

    override fun initObject() {
        job = CoroutineScope(Dispatchers.Unconfined).launch {

                for (id in 1..100000 step 100) {
                    ic = id
                    dataBinding.setVariable(BR.myText, "set dataBind value ${ic}")
                    delay(250L)
                }
            }
    }

    override fun onResume() {
        super.onResume()
        if (job != null && !job !!.isActive) {
            job!!.start()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        if (job != null && job !!.isActive) {
            job!!.cancel()
        }
        outState.putInt("ic", ic)
        super.onSaveInstanceState(outState)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState != null)
        ic = savedInstanceState.getInt("ic")

    }


}