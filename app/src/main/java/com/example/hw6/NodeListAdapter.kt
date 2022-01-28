//package com.example.hw6
//
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.ListAdapter
//import androidx.recyclerview.widget.RecyclerView
//import com.example.hw6.database.NodeEntity
//import com.example.hw6.databinding.NodesRecyclerBinding
//import kotlin.reflect.KFunction2
//
//
//class NodeListAdapter(
//    private val clickItem: KFunction2<NodeEntity, String, Unit>,
//    private val node: NodeEntity?
//) :
//    ListAdapter<NodeEntity, NodeListAdapter.NodeViewHolder>(NodesComparator()) {
//    var mode: Int = 1
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NodeViewHolder {
//        val binding =
//            NodesRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return NodeViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: NodeViewHolder, position: Int) {
//        val current = getItem(position)
//        var display: String = ""
//
//        when (mode) {
//            1 -> display = "id: ${current.id} | value: ${current.value}"
//            2 -> display =
//                "id: ${node?.id} | value: ${node?.value} ---- id: ${current.id} | value: ${current.value}"
//            3 -> display =
//                "id: ${current.id} | value: ${current.value} ---- id: ${node?.id} | value: ${node?.value}"
//        }
//        holder.bind(current, display, clickItem)
//    }
//
//    inner class NodeViewHolder internal constructor(private val binding: NodesRecyclerBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//
//
//        fun bind(node: NodeEntity, text: String?, clickItem: KFunction2<NodeEntity, String, Unit>) =
//            binding.run {
//                binding.cardText.text = text
//
//
//                binding.nodeCard.setOnClickListener {
//                    when (mode) {
//                        1 -> clickItem.invoke(node, "relations")
//                        2 -> clickItem.invoke(node, "add child")
//                        3 -> clickItem.invoke(node, "add parent")
//                    }
//                }
//            }
//    }
//
//    class NodesComparator : DiffUtil.ItemCallback<NodeEntity>() {
//        override fun areItemsTheSame(oldItem: NodeEntity, newItem: NodeEntity): Boolean {
//            return oldItem === newItem
//        }
//
//        override fun areContentsTheSame(oldItem: NodeEntity, newItem: NodeEntity): Boolean {
//            return oldItem.value.toString() == newItem.value.toString()
//        }
//    }
//}

package com.example.hw6

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hw6.database.NodeEntity
import com.example.hw6.databinding.NodesRecyclerBinding
import kotlin.reflect.KFunction2


class NodeListAdapter(private val clickItem: KFunction2<NodeEntity, String, Unit>) :
    ListAdapter<NodeEntity, NodeListAdapter.NodeViewHolder>(NodesComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NodeViewHolder {
        val binding =
            NodesRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NodeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NodeViewHolder, position: Int) {
        val current = getItem(position)
        val display = "id: ${current.id} | value: ${current.value}"
        holder.bind(current, display, clickItem)
    }

    class NodeViewHolder internal constructor(private val binding: NodesRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(node: NodeEntity, text: String?, clickItem: KFunction2<NodeEntity, String, Unit>) =
            binding.run {
                binding.cardText.text = text

                binding.nodeCard.setOnClickListener { clickItem.invoke(node, "relations") }
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