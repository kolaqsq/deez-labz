package com.example.hw3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.hw3.databinding.FragmentThirdBinding


class ThirdFragment : Fragment() {
    private lateinit var binding: FragmentThirdBinding

    private val dataModel: DataModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Добавляем тригерр на изменения editText
        // Передаем данные через ViewModels
        binding.textInput.doAfterTextChanged {
            dataModel.operation.value = binding.textInput.text.toString()
        }
        // Добавляем тригерр для перехода на следующий fragment
        binding.buttonNext.setOnClickListener {
            val activityCallBack = requireActivity() as ActivityCallBack
            activityCallBack.next()
        }
        // Добавляем тригерр для перехода на предыдущий fragment
        binding.buttonPrev.setOnClickListener {
            val activityCallBack = requireActivity() as ActivityCallBack
            activityCallBack.prev()
        }
    }
}