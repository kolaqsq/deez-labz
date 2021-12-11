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
        // Добавляем тригерр на изменение editText
        // Передаем данные через ViewModels
        binding.textInput.doAfterTextChanged {
            if (binding.textInput.text.toString() != "") {
                dataModel.number2.value = binding.textInput.text.toString().toInt()
            } else dataModel.number2.value = null
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