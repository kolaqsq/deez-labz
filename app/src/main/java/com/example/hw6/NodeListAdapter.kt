package com.example.hw6

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hw6.database.NodeEntity

class NodeListAdapter : ListAdapter<NodeEntity, NodeListAdapter.NodeViewHolder>(NodesComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NodeViewHolder {
        return NodeViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: NodeViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.value.toString())
    }

    class NodeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nodeItemView: TextView = itemView.findViewById(R.id.cardText)

        fun bind(text: String?) {
            nodeItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): NodeViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.nodes_recycler, parent, false)
                return NodeViewHolder(view)
            }
        }
    }

    class NodesComparator : DiffUtil.ItemCallback<NodeEntity>() {
        override fun areItemsTheSame(oldItem: NodeEntity, newItem: NodeEntity): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: NodeEntity, newItem: NodeEntity): Boolean {
            return oldItem.value.toString() == newItem.value.toString()
        }
    }
}