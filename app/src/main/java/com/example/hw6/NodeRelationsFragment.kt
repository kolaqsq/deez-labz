package com.example.hw6

import android.os.Bundle
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

class NodeRelationsFragment : Fragment() {
    private lateinit var binding: NodeRelationsFragmentBinding
//    private lateinit var node: MutableLiveData<NodeEntity>
//    private lateinit var adapter: NodeRelationsAdapter
//    private val nodeViewModel: NodeViewModel by activityViewModels {
//        NodeViewModelFactory((activity?.application as HW6).repository)
//    }

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

//        node = nodeViewModel.currentNode
//        adapter = NodeRelationsAdapter(this::addRelations, node.value)

//        nodeViewModel.allChildren.value = nodeViewModel.getChildren(node.value)

//        nodeViewModel.allNodes.observe(viewLifecycleOwner) { nodes ->
//            // Update the cached copy of the words in the adapter.
//            nodes?.let { adapter.submitList(it) }
//        }

//        setupRecycleView()

    }

//    private fun setupRecycleView() {
////        adapter.mode = mode
//        binding.recyclerView.layoutManager =
//            LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
//        binding.recyclerView.adapter = adapter
//    }

    private fun addRelations(node: NodeEntity, trigger: String): Unit {
        when (trigger) {
            "add relation" -> {
                return
            }
        }
    }
}