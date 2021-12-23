package com.example.hw5

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DataModel : ViewModel() {
    val itemList: MutableLiveData<List<String>> by lazy {
        MutableLiveData<List<String>>().also {
            createItems()
        }
    }

    private val list: MutableList<String> = mutableListOf()

    private fun createItems() {
        viewModelScope.launch {
            for (i in 1..100) {
                delay(2000L)
                list.add("Сообщение №$i")
                itemList.value = list
            }
        }
    }
}