package com.example.hw3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.hw3.databinding.FragmentFourthBinding

class FourthFragment : Fragment() {
    private lateinit var binding: FragmentFourthBinding

    private val dataModel: DataModel by activityViewModels()
    private var num1: Int? = null
    private var num2: Int? = null
    private var operator: String = ""
    private var result: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFourthBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Включаем наблюдателя после создания View
        initialization()

        // Добавляем тригерр для перехода на предыдущий fragment
        binding.buttonPrev.setOnClickListener {
            val activityCallBack = requireActivity() as ActivityCallBack
            activityCallBack.prev()
        }
    }

    // Подключаем наблюдателя для данных с DataModel и записываем в переменные
    private fun initialization() {
        dataModel.number1.observe(viewLifecycleOwner) {
            num1 = it
        }
        dataModel.number2.observe(viewLifecycleOwner) {
            num2 = it
        }
        dataModel.operation.observe(viewLifecycleOwner) {
            operator = it
            calculator()
        }
    }

    // Переопределяем переменные
    private fun calculator() {
        // Проверяем все переменные
        if (num1 == null || num2 == null) return
        result = when (operator) {
            "+" -> num1!!.plus(num2!!)
            "-" -> num1!!.minus(num2!!)
            "*" -> num1!!.times(num2!!)
            "/" -> num1!!.div(num2!!)
            else -> return
        }
        // Подсчет результата
        binding.textOutputInfo.text = "$num1 $operator $num2 = $result"
        binding.textOutputInt.text = result.toString()
    }
}