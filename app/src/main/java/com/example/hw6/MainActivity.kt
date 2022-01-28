package com.example.hw6

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.hw6.database.NodeEntity
import com.example.hw6.database.NodeViewModel
import com.example.hw6.database.NodeViewModelFactory
import com.example.hw6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ActivityCallBack {
    private lateinit var binding: ActivityMainBinding
    private val fragmentList: MutableList<Fragment> = mutableListOf()
    private var position: Int = 0
    private val nodeViewModel: NodeViewModel by viewModels {
        NodeViewModelFactory((application as HW6).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fragmentList.add(AllNodesFragment())
        fragmentList.add(NodeRelationsFragment())

        val transactionInitialization = supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragmentList[0])
//            .add(R.id.fragment_container, fragmentList[1])
//            .detach(fragmentList[1])
            .addToBackStack("initialization fragment")
        transactionInitialization.commit()
    }

//    override fun onBackPressed() {
//        if (position == 1) {
//            prev()
//        } else finishAfterTransition()
//    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        } else moveTaskToBack(true)
    }

    override fun editRelations(node: NodeEntity) {
        nodeViewModel.currentNode.value = node
        val transactionInitialization = supportFragmentManager
            .beginTransaction()
            .detach(fragmentList[0])
            .attach(fragmentList[1])
//            .remove(fragmentList[0])
//            .replace(R.id.fragment_container, fragmentList[1])
            .addToBackStack("swap fragment")
        transactionInitialization.commit()
        position = 1
        Log.d("navigation", "success")
    }

    override fun prev() {
        val transactionInitialization = supportFragmentManager
            .beginTransaction()
            .detach(fragmentList[1])
            .attach(fragmentList[0])
            .addToBackStack("swap fragment")
        transactionInitialization.commit()
        position = 0
    }

    override fun getViewModel(): NodeViewModel {
        return nodeViewModel
    }
}

interface ActivityCallBack {
    fun editRelations(node: NodeEntity)
    fun prev()
    fun getViewModel(): NodeViewModel
}