package com.example.hw5

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hw5.databinding.FrameAsyncBinding

class FragmentMain : Fragment(), FragmentManager {
    private lateinit var binding: FrameAsyncBinding

    private var adapter: Adapter = Adapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FrameAsyncBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activityCallBack = requireActivity() as FragmentManager
        adapter = activityCallBack.setAdapter()
        setupRecycleView()
    }

    private fun setupRecycleView() {
        binding.recyclerView.layoutManager =
            LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        binding.recyclerView.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun setList(list: List<String>) {
        adapter.itemList = list
        adapter.notifyDataSetChanged()
    }

    override fun setAdapter(): Adapter {
        return adapter
    }
}