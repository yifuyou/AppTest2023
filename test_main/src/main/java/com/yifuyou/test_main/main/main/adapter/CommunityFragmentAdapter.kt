package com.yifuyou.test_main.main.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.launcher.ARouter
import com.yifuyou.test_main.R
import com.yifuyou.test_main.databinding.CommunityItemBinding
import com.yifuyou.test_main.main.main.placeholder.CommunityContent
import com.yifuyou.test_main.main.main.utls.TimeUtil

class CommunityFragmentAdapter : RecyclerView.Adapter<CommunityFragmentAdapter.ViewHolder>() {

    class ViewHolder(var binding: CommunityItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var root =
            LayoutInflater.from(parent.context).inflate(R.layout.community_item, parent, false)

        return ViewHolder(CommunityItemBinding.bind(root))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.binding) {
            var cObject = CommunityContent.cObjects[position]
            imageView.setImageResource(R.drawable.main_user)
            name.text=cObject
            val mutableList = CommunityContent.cData[cObject]
            if (mutableList == null) {
                msgTv.text= ""
                timeTv.text = TimeUtil.convertTimeToString(TimeUtil.getTimeString())
            } else {
                with(mutableList[mutableList.size-1]) {
                    msgTv.text= msg
                    timeTv.text = TimeUtil.convertTimeToString(time)
                }
            }
            root.setOnClickListener { view->
                ARouter.getInstance().build("/main/community")
                    .withString("name", cObject)
                    .navigation()
            }
        }
    }

    override fun getItemCount(): Int {
        return CommunityContent.count
    }



}