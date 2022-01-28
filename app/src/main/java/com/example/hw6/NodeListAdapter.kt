package com.example.hw6


import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.StyleRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hw6.database.NodeEntity
import com.example.hw6.databinding.NodesRecyclerBinding
import kotlin.reflect.KFunction2


class NodeListAdapter(
    private val clickItem: KFunction2<NodeEntity, String, Unit>,
    private val node: NodeEntity?
) :
    ListAdapter<NodeEntity, NodeListAdapter.NodeViewHolder>(NodesComparator()) {
    var mode: Int = 1
    lateinit var currentNode: NodeEntity
    lateinit var allNodes: List<NodeEntity>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NodeViewHolder {
        val binding =
            NodesRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NodeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NodeViewHolder, position: Int) {
        val current = getItem(position)
        var display: String = ""

        when (mode) {
            1 -> display = "id: ${current.id} | value: ${current.value}"
            2 -> display =
                "id: ${node?.id} | value: ${node?.value} ---- id: ${current.id} | value: ${current.value}"
            3 -> display =
                "id: ${current.id} | value: ${current.value} ---- id: ${node?.id} | value: ${node?.value}"
        }
        holder.bind(current, display, clickItem)
    }

    private fun coloredLinkNode(node: NodeEntity): Int? {
        var hasParents = false
        var hasChildren = false
        val allOtherNode: MutableList<NodeEntity> = allNodes.toMutableList()

        allOtherNode.remove(node)

        if (node.nodes.isNotEmpty()) {
            hasParents = true
        }
        for (item in allOtherNode) {
            if (node in item.nodes) {
                hasChildren = true
            }
        }

        Log.d("nodus", allOtherNode.toString())

        if (hasChildren && hasParents) {
            return Color.RED
        }
        if (hasParents) {
            return Color.BLUE
        }
        if (hasChildren) {
            return Color.YELLOW
        }
        return null
    }

    private fun coloredRelation(node: NodeEntity): Int? {
        var hasRelation = false
        if ((node in currentNode.nodes) or (currentNode in node.nodes)) {
            hasRelation = true
        }

        return if (hasRelation) {
            Color.GREEN
        } else null
    }

    inner class NodeViewHolder internal constructor(private val binding: NodesRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(node: NodeEntity, text: String?, clickItem: KFunction2<NodeEntity, String, Unit>) =
            binding.run {
                binding.cardText.text = text

                when (mode) {
                    1 -> coloredLinkNode(node)?.let {
                        binding.icon.setColorFilter(
                            it,
                            android.graphics.PorterDuff.Mode.SRC_IN
                        )
                    }
                    2 -> {
                        if (currentNode in node.nodes) {
                            binding.icon.setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN)
                        }
                    }
                    3 -> {
                        if (node in currentNode.nodes) {
                            binding.icon.setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN)
                        }
                    }
                }



                binding.nodeCard.setOnClickListener {
                    when (mode) {
                        1 -> clickItem.invoke(node, "relations")
                        2 -> clickItem.invoke(node, "add child")
                        3 -> clickItem.invoke(node, "add parent")
                    }
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

//package com.example.hw6
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
//class NodeListAdapter(private val clickItem: KFunction2<NodeEntity, String, Unit>) :
//    ListAdapter<NodeEntity, NodeListAdapter.NodeViewHolder>(NodesComparator()) {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NodeViewHolder {
//        val binding =
//            NodesRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return NodeViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: NodeViewHolder, position: Int) {
//        val current = getItem(position)
//        val display = "id: ${current.id} | value: ${current.value}"
//        holder.bind(current, display, clickItem)
//    }
//
//    class NodeViewHolder internal constructor(private val binding: NodesRecyclerBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//
//        fun bind(node: NodeEntity, text: String?, clickItem: KFunction2<NodeEntity, String, Unit>) =
//            binding.run {
//                binding.cardText.text = text
//
//                binding.nodeCard.setOnClickListener { clickItem.invoke(node, "relations") }
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