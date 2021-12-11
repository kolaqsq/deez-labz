package com.example.hw3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.hw3.databinding.FragmentFirstBinding


class FirstFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding

    private val dataModel: DataModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Добавляем тригерр на изменения editText
        // Передаем данные через ViewModels
        binding.textInput.doAfterTextChanged {
            if (binding.textInput.text.toString() != "") {
                dataModel.number1.value = binding.textInput.text.toString().toInt()
            } else dataModel.number1.value = null
        }
        // Добавляем тригерры для перехода на следующий fragment
        binding.button.setOnClickListener {
            val activityCallBack = requireActivity() as ActivityCallBack
            activityCallBack.next()
        }
    }
}