package com.example.headsup

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*

class RecyclerViewAdapter(var celebrityData:List<Celebrity>) : RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>() {

    val TAG = "Adapter"
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent,false))
    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val info = celebrityData[position]
        Log.d(TAG, "onBindViewHolder: ${info.name}")

        holder.itemView.apply {
           nameTv.text = info.name
            taboo1.text = info.taboo1
            taboo2.text = info.taboo2
            taboo3.text = info.taboo3

        }
    }

    override fun getItemCount() = celebrityData.size
}

