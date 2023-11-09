package com.yifuyou.test_main.main.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yifuyou.test_main.R
import com.yifuyou.test_main.databinding.RecycleItemContainerBinding
import com.yifuyou.test_main.main.main.placeholder.CommunityContent

class CommunityActivityAdapter(var name:String) : RecyclerView.Adapter<CommunityActivityAdapter.ViewHolder>() {
    class ViewHolder(var dataBinding: RecycleItemContainerBinding) :RecyclerView.ViewHolder(dataBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // TODO viewType判断
        return ViewHolder(RecycleItemContainerBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.dataBinding) {
            imgMsg.setImageResource(R.drawable.main_user)
            textName.text = name
            with(textMsg) {
                setBackgroundResource(R.drawable.communit_msg_bg)
                text = CommunityContent.cData[name]!![position].msg
            }
        }
    }

    override fun getItemCount(): Int {
        if(CommunityContent.cData[name]!=null) {
            return CommunityContent.cData[name]!!.size
        }
        return 0
    }

}