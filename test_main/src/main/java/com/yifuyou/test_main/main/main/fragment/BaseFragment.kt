package com.yifuyou.test_main.main.main.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<T : ViewDataBinding> : Fragment(){

    abstract fun getFragmentTag():String
    abstract fun getLayoutId():Int
    abstract fun initView():Unit
    abstract fun initObject():Unit

    var firstInit : Boolean = true

    lateinit var dataBinding : T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e("TAG", "onCreateView: ${getFragmentTag()}" )
        if(!firstInit) {
            return dataBinding.root
        }
        firstInit = false
        dataBinding = DataBindingUtil.inflate<T>(
            inflater,
            getLayoutId(),
            container,
            false
        )
        initView()
        initObject()
        return dataBinding.root
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e("TAG", "onAttach: ${getFragmentTag()}" )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("TAG", "onCreate: ${getFragmentTag()}" )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.e("TAG", "onActivityCreated: ${getFragmentTag()}" )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("TAG", "onDestroyView: ${getFragmentTag()}" )
    }

    override fun onDetach() {
        super.onDetach()
        Log.e("TAG", "onDetach: ${getFragmentTag()}" )
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("TAG", "onDestroy: ${getFragmentTag()}" )
    }
}