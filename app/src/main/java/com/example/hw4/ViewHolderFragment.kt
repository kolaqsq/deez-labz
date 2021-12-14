package com.example.hw4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hw4.databinding.PersonListBinding
import com.google.android.material.snackbar.Snackbar


class ViewHolderFragment : Fragment() {
    private lateinit var binding: PersonListBinding

    var adapter: Adapter = Adapter(this::showSnackbar)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PersonListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // иницилизация recycleView
        setupRecycleView()
    }

    // Подключение и настройка recycleView
    private fun setupRecycleView() {
        binding.recycleView.layoutManager =
            LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        binding.recycleView.adapter = adapter
    }

    // Обработка нажатий на карточки в ViewHolder
    private fun showSnackbar(person: Person, trigger: String): Unit {
        var text: String = "Произошла ошибка"
        when (trigger) {
            "itemInfo" -> text =
                "Нажата карточка: " + person.Name + "\nДля подробной информации нажмите и удержите карточку"
            "like" -> text = "Нажат лайк: " + person.Name
            "details" -> {
                val activityCallBack = requireActivity() as ActivityCallBack
                activityCallBack.showDetails(person)
                return
            }
        }
        Snackbar.make(binding.root, text, 3000).show()
    }
}