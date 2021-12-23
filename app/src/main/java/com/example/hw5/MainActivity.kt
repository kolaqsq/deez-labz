package com.example.hw5

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hw5.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), FragmentManager {
    private lateinit var binding: ActivityMainBinding
    private var fragmentMain = FragmentMain()
    private var fragmentAsync: FragmentAsync = FragmentAsync()
    private val adapter = fragmentAsync.getAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val lastFragmentAsyncTask =
            supportFragmentManager.findFragmentByTag(FragmentAsync.MyTag)

        if (lastFragmentAsyncTask == null) {
            val transactionInitialization = supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragmentMain)
                .add(R.id.fragment_container, fragmentAsync, FragmentAsync.MyTag)
                .addToBackStack("added fragment")
            transactionInitialization.commit()
        }
    }

    override fun setAdapter(): Adapter {
        return adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun setList(list: List<String>) {
        adapter.itemList = list
        adapter.notifyDataSetChanged()
    }
}

interface TaskCallbacks {
    fun onPreExecuted()
    fun onCancelled()
    fun onPostExecute(i: String)
}

interface FragmentManager {
    fun setList(list: List<String>)
    fun setAdapter(): Adapter
}

