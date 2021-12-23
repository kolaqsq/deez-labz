package com.example.hw5

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hw5.databinding.ItemCardBinding


class Adapter: RecyclerView.Adapter<Adapter.Holder>() {
    var itemList: List<String> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val text = itemList[position]
        holder.bind(text)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class Holder internal constructor(private val binding: ItemCardBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(text: String) = binding.run{
            cardName.text = text
        }
    }
}