package com.yifuyou.test_main.main.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yifuyou.test_main.R
import com.yifuyou.test_main.main.main.placeholder.MoreContent.MoreItem

class FindPgMoreAdapter(private val values: List<MoreItem>)
    : RecyclerView.Adapter<FindPgMoreAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(getLayoutByViewType(viewType), parent, false))
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.imgV.setImageResource(values[position].img)
        holder.titleNameTv.text = values[position].info
        holder.fromTv.text = values[position].from
    }

    override fun getItemCount(): Int {
        return values.size
    }

    private fun getLayoutByViewType(viewType: Int) : Int{
        return R.layout.fragment_item
    }

    inner class ViewHolder(var view: View) : RecyclerView.ViewHolder(view){
        var imgV : ImageView = view.findViewById(R.id.imageView)
        var titleNameTv : TextView = view.findViewById(R.id.titleName)
        var fromTv : TextView = view.findViewById(R.id.msgFrom)
    }
}