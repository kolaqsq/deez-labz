package com.example.hw6.database

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch

class NodeViewModel(private val repository: NodeRepository) : ViewModel() {

    // Using LiveData and caching what allNodes returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    var allNodes: LiveData<List<NodeEntity>> = repository.allNodes.asLiveData()

    val currentNode: MutableLiveData<NodeEntity> by lazy {
        MediatorLiveData()
    }

//    val allChildren: MutableLiveData<List<NodeEntity>> by lazy {
//        MediatorLiveData()
//    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(nodeEntity: NodeEntity) = viewModelScope.launch {
        repository.insert(nodeEntity)
    }

//    fun updateModels() = viewModelScope.launch {
//        allNodes = repository.loadAll().asLiveData()
//    }

//    fun getChildren(node: NodeEntity?): List<NodeEntity>? {
//        val children: List<NodeEntity>? = allNodes.value
//
//        if (node != null && children != null) {
//            removeParents(node, children as MutableList<NodeEntity>)
//        }
//
//        Log.d("removeParent_children", children.toString())
//        Log.d("removeParent_allNodes", allNodes.value.toString())
//        return children
//    }
//
//    private fun removeParents(node: NodeEntity, children: MutableList<NodeEntity>) {
//        children.remove(node)
//
//        for (item in node.nodes) {
//            removeParents(item, children)
//        }
//        Log.d("removeParent", node.value.toString())
//    }
}

class NodeViewModelFactory(private val repository: NodeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NodeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NodeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}