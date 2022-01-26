package com.example.hw6

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.hw6.database.NodeViewModel
import com.example.hw6.database.NodeViewModelFactory
import com.example.hw6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val fragmentList: MutableList<Fragment> = mutableListOf()
    val nodeViewModel: NodeViewModel by viewModels {
        NodeViewModelFactory((application as HW6).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fragmentList.add(AllNodesFragment())

        val transactionInitialization = supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragmentList[0])
            .addToBackStack("initialization fragment")
        transactionInitialization.commit()
    }
}