package com.example.hw6.database

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class NodeViewModel(private val repository: NodeRepository) : ViewModel() {

    // Using LiveData and caching what allNodes returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allNodes: LiveData<List<NodeEntity>> = repository.allNodes.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(nodeEntity: NodeEntity) = viewModelScope.launch {
        repository.insert(nodeEntity)
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