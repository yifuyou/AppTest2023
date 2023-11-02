package com.yifuyou.test_main.main.main.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment(){

    lateinit var fragmentTag:String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e("TAG", "onCreateView: $fragmentTag" )
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e("TAG", "onAttach: $fragmentTag" )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("TAG", "onCreate: $fragmentTag" )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.e("TAG", "onActivityCreated: $fragmentTag" )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("TAG", "onDestroyView: $fragmentTag" )
    }

    override fun onDetach() {
        super.onDetach()
        Log.e("TAG", "onDetach: $fragmentTag" )
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("TAG", "onDestroy: $fragmentTag" )
    }
}