package com.dabolink.rickandmorty.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.dabolink.rickandmorty.databinding.TextItemBinding

class TextAdapter(onItemClicked: OnItemClicked? = null): Adapter<TextAdapter.ViewHolder>() {
    var mItems: ArrayList<TextItem> = ArrayList()
    val mOnItemClicked = onItemClicked

    inner class ViewHolder(private val binding: TextItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TextItem) {
            binding.text.text = item.getText()
            binding.root.setOnClickListener {
                mOnItemClicked?.onItemClicked(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TextItemBinding.inflate(inflater)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mItems[position])
    }

    fun setItems(items: Collection<TextItem>) {
        val oldSize = items.size
        mItems.clear()
        mItems.addAll(items)
        notifyItemRangeChanged(oldSize, mItems.size)
    }

    override fun getItemCount(): Int {
        return mItems.size
    }
}
