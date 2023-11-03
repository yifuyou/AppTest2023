package com.yifuyou.test_main.main.main.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment(){

    abstract fun getFragmentTag():String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e("TAG", "onCreateView: ${getFragmentTag()}" )
        return super.onCreateView(inflater, container, savedInstanceState)
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