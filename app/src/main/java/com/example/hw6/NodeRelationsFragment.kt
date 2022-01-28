package com.example.hw6

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hw6.database.NodeEntity
import com.example.hw6.database.NodeViewModel
import com.example.hw6.database.NodeViewModelFactory
import com.example.hw6.databinding.NodeRelationsFragmentBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class NodeRelationsFragment : Fragment() {
    private lateinit var binding: NodeRelationsFragmentBinding
    private lateinit var node: MutableLiveData<NodeEntity>
    private lateinit var adapter: NodeListAdapter
    private val nodeViewModel: NodeViewModel by activityViewModels {
        NodeViewModelFactory((activity?.application as HW6).repository)
    }

//    private val activityCallBack = requireActivity() as ActivityCallBack
//    private val nodeViewModel = activityCallBack.getViewModel()
//    private val node = nodeViewModel.currentNode
//    private val adapter = NodeRelationsAdapter(this::addRelations, node.value)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NodeRelationsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        node = nodeViewModel.currentNode
        adapter = NodeListAdapter(this::addRelations, node.value)

        nodeViewModel.getChildren(node.value, nodeViewModel.nodesForRelation)

        nodeViewModel.nodesForRelation.observe(viewLifecycleOwner) { nodes ->
            // Update the cached copy of the nodes in the adapter.
            nodes?.let { adapter.submitList(it) }
        }

        setupRecycleView(2)

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.title) {
                "Children" -> {
                    nodeViewModel.getChildren(node.value, nodeViewModel.nodesForRelation)
                    setupRecycleView(2)
                }
                "Parents" -> {
                    nodeViewModel.getChildren(node.value, nodeViewModel.nodesForRelation)
                    setupRecycleView(3)
                }
            }
            true
        }

    }

    private fun setupRecycleView(mode: Int) {
        adapter.mode = mode
        adapter.currentNode = node.value!!
        binding.recyclerView.layoutManager =
            LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        binding.recyclerView.adapter = adapter
    }

    private fun addRelations(node: NodeEntity, trigger: String): Unit {
        Log.d("addrel", "launch")
        when (trigger) {
            "add child" -> {
                context?.let {
                    Log.d("addrel", "launch")
                    MaterialAlertDialogBuilder(it)
                        .setTitle(resources.getString(R.string.add_relation_dialog_title))
                        .setMessage(resources.getString(R.string.add_relation_dialog_message))
                        .setNegativeButton(resources.getString(R.string.dialog_cancel)) { dialog, which ->
                            // Respond to negative button press
                        }
                        .setPositiveButton(resources.getString(R.string.dialog_add)) { dialog, which ->
                            nodeViewModel.currentNode.value?.let { it1 ->
                                nodeViewModel.addRelation(it1, node)
                            }
                        }
                        .show()
                }
                return
            }
        }
    }
}