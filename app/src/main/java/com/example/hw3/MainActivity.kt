package com.example.hw3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.hw3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var bindingMain: ActivityMainBinding

    private val buttonList: MutableList<android.widget.Button> = mutableListOf()
    private val fragmentList: MutableList<Fragment> = mutableListOf()
    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMain.root)

        // Инициализируем фрагменты
        fragmentList.add(FirstFragment())
        fragmentList.add(SecondFragment())
        fragmentList.add(ThirdFragment())
        fragmentList.add(FourthFragment())

        // Инициализируем кнопки меню
        buttonList.add(bindingMain.include1.button1)
        buttonList.add(bindingMain.include1.button2)
        buttonList.add(bindingMain.include1.button3)
        buttonList.add(bindingMain.include1.button4)

        // Добавляем фрагменты в менеджер и отключаем ненужные через detach
        val transactionInitialization = supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, fragmentList[0])
            .add(R.id.fragment_container, fragmentList[1])
            .add(R.id.fragment_container, fragmentList[2])
            .add(R.id.fragment_container, fragmentList[3])
            .detach(fragmentList[1])
            .detach(fragmentList[2])
            .detach(fragmentList[3])
            .addToBackStack("initialization fragment")
        transactionInitialization.commit()
    }

    override fun onStart() {
        super.onStart()
        // Добавляем тригерры на клик всем кнопкам в меню
        for (i in 0 until buttonList.size) {
            buttonList[i].setOnClickListener {
                val transaction = supportFragmentManager
                    .beginTransaction()
                    .detach(fragmentList[position])
                    .attach(fragmentList[i])
                transaction.commit()
                position = i
            }
        }
    }
}
