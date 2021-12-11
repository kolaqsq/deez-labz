package com.example.hw3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.hw3.databinding.FragmentSecondBinding


class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding

    private val dataModel: DataModel by activityViewModels()
    private var numList: List<android.widget.Button> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Инициализируем кнопки-цифры
        numList = listOf(
            binding.includeNum.input0,
            binding.includeNum.input1,
            binding.includeNum.input2,
            binding.includeNum.input3,
            binding.includeNum.input4,
            binding.includeNum.input5,
            binding.includeNum.input6,
            binding.includeNum.input7,
            binding.includeNum.input8,
            binding.includeNum.input9
        )

        // Добавляем тригерр на изменения editText
        // Передаем данные через ViewModels
        binding.textInput.doAfterTextChanged {
            if (binding.textInput.text.toString() != "") {
                dataModel.number2.value = binding.textInput.text.toString().toInt()
            } else dataModel.number2.value = null
        }

        // Добавляем тригерры для работы кнопок-цифр
        for (i in numList.indices) {
            numList[i].setOnClickListener {
                binding.textInput.append(i.toString())
            }
        }

        // Добавляем триггеры для работы кнопки удаления
        binding.includeNum.inputDelete.setOnClickListener {
            val text = binding.textInput.text.toString()
            if (text.isNotEmpty()) {
                binding.textInput.setText(text.substring(0, text.length - 1))
            }
        }

        // Добавляем тригерры для перехода на следующий fragment
        binding.includeNum.inputEnter.setOnClickListener {
            val activityCallBack = requireActivity() as ActivityCallBack
            activityCallBack.next()
        }
    }
}