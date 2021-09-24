package com.example.mystore.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mystore.R
import kotlinx.android.synthetic.main.item_layout.view.*

class ItemAdapter(var list: MutableList<Item>) : RecyclerView.Adapter<ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        var item = list[position]
        holder.setData(item)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun setData(item: Item) {
        itemView.apply {
            tvNameDesc.text = item.name
            tvPrice.text = item.price
        }
    }
}