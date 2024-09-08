package com.example.myappl5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class listAdapter (val list: List<ListItem>) : RecyclerView.Adapter<listAdapter.ListViewHolder>() {

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val keyTextView : TextView = itemView.findViewById(R.id.textKey)
        val valueTextView : TextView = itemView.findViewById(R.id.textValue)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
          val  itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return ListViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
            holder.keyTextView.text = list[position].KeyText
            holder.valueTextView.text = list[position].valueText


    }
}