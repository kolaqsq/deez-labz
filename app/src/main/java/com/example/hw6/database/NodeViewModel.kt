package com.example.hw6.database

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch

class NodeViewModel(private val repository: NodeRepository) : ViewModel() {

    // Using LiveData and caching what allNodes returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allNodes: LiveData<List<NodeEntity>> = repository.allNodes.asLiveData()
//    val allChildren: LiveData<List<NodeEntity>> = repository.allNodes.asLiveData()

    val currentNode: MutableLiveData<NodeEntity> by lazy {
        MediatorLiveData()
    }

    val nodesForRelation: MutableLiveData<List<NodeEntity>> by lazy {
        MediatorLiveData()
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(nodeEntity: NodeEntity) = viewModelScope.launch {
        repository.insert(nodeEntity)
    }

    fun addRelation(parent: NodeEntity, child: NodeEntity) = viewModelScope.launch {
        Log.d("addrel", child.nodes.toString())
        val childNodes: MutableList<NodeEntity> = child.nodes.toMutableList()
        childNodes.add(parent)
        child.nodes = childNodes.toList()
        Log.d("addrel", child.nodes.toString())
        repository.update(child)
    }

//    fun updateModels() = viewModelScope.launch {
//        allNodes = repository.loadAll().asLiveData()
//    }

    fun getChildren(node: NodeEntity?, list: MutableLiveData<List<NodeEntity>>) {
        val parents: MutableList<NodeEntity> = mutableListOf()
        val children: MutableList<NodeEntity> = mutableListOf()

        if (node != null) {
            removeParents(node, parents)
        }

        for (item in allNodes.value!!) {
            if (item !in parents) {
                children.add(item)
            }
        }

        list.value = children

        Log.d("removeParent_children", parents.toString())
        Log.d("removeParent_allNodes", allNodes.value.toString())
    }

    private fun removeParents(node: NodeEntity, parents: MutableList<NodeEntity>) {
        parents.add(node)

        for (item in node.nodes) {
            removeParents(item, parents)
        }
        Log.d("removeParent", node.value.toString())
    }
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