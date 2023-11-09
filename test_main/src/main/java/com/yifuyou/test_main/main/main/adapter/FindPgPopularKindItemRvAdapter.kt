package com.yifuyou.test_main.main.main.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.yifuyou.test_main.R
import com.yifuyou.test_main.databinding.FragmentPopularKindItemBinding
import com.yifuyou.test_main.main.main.placeholder.PlaceholderContent.PlaceholderItem

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class FindPgPopularKindItemRvAdapter(private val fragmentManager: FragmentManager,
    private val values: List<PlaceholderItem>
) : RecyclerView.Adapter<FindPgPopularKindItemRvAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentPopularKindItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.nameView.text = item.content
        holder.infoView.text = item.details
        holder.imageView.setImageResource(item.img)
        holder.imageView.setColorFilter(R.color.color_3)
        holder.imageView.setBackgroundColor(Color.WHITE)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentPopularKindItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val imageView: ImageView = binding.itemImg
        val nameView: TextView = binding.itemName
        val infoView: TextView = binding.itemInfo

        override fun toString(): String {
            return super.toString() + " '" + nameView.text + "'"
        }
    }

}