package com.dabolink.rickandmorty.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.dabolink.rickandmorty.databinding.ImageTextItemBinding

class TextAdapter: Adapter<TextAdapter.ViewHolder>() {

    private var _binding: ImageTextItemBinding? = null
    private val binding get() = _binding!!

    var items: ArrayList<TextItem> = ArrayList()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        lateinit var textView: TextView
        lateinit var imageView: ImageView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)

        _binding = ImageTextItemBinding.inflate(inflater, parent, false)
        val root = binding.root

        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.imageView = binding.image
        holder.textView = binding.text

        holder.textView.text = item.getText()
    }

    fun setItems(items: Collection<TextItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
