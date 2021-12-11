package com.example.hw3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        // Функция для выбора оператора и переходна на следующий экран
        fun select(operator: String) {
            dataModel.operation.value = operator
            val activityCallBack = requireActivity() as ActivityCallBack
            activityCallBack.next()
        }

        // Добавляем триггеры для кнопок выбора операторов
        binding.inputAddition.setOnClickListener {
            select("+")
        }

        binding.inputSubtraction.setOnClickListener {
            select("-")
        }

        binding.inputDivision.setOnClickListener {
            select("/")
        }

        binding.inputMultiplication.setOnClickListener {
            select("*")
        }
    }
}