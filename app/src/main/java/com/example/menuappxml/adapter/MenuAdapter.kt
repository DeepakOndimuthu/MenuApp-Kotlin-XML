package com.example.menuappxml.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.menuappxml.R
import com.example.menuappxml.data.model.MenuItem

class MenuAdapter(
    private val onClick: (MenuItem) -> Unit
) : ListAdapter<MenuItem, MenuAdapter.ViewHolder>(Diff()) {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.image)
        val title: TextView = view.findViewById(R.id.title)
        val desc: TextView = view.findViewById(R.id.desc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_menu, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.title.text = item.title
        holder.desc.text = item.description

        Glide.with(holder.itemView)
            .load(item.imageUrl)
            .into(holder.image)

        holder.itemView.setOnClickListener {
            onClick(item)
        }
    }

    class Diff : DiffUtil.ItemCallback<MenuItem>() {
        override fun areItemsTheSame(a: MenuItem, b: MenuItem) = a.id == b.id
        override fun areContentsTheSame(a: MenuItem, b: MenuItem) = a == b
    }
}