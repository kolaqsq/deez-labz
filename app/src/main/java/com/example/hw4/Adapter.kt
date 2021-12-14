package com.example.hw4

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.hw4.databinding.PersonCardBinding
import kotlin.reflect.KFunction2

class Adapter(private val clickItem: KFunction2<Person, String, Unit>) :
    RecyclerView.Adapter<Adapter.Holder>() {

    var listItem: List<Person> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = PersonCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val picture = listItem[position]
        holder.bind(picture, clickItem)
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    inner class Holder internal constructor(private val binding: PersonCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(person: Person, clickItem: KFunction2<Person, String, Unit>) = binding.run {

            // Отдаем данные в person_list
            cardName.text = person.Name
            cardPhoto.load(person.Image) { transformations(RoundedCornersTransformation(20f)) }
            cardDates.text = person.Date
            cardInfo.text = person.Information
            cardSex.text = person.Gender

            // Подключаем слушателя на клик по карточке
            // На удержание карточки открывается страница с подробной информацией
            binding.cardLikeButton.setOnClickListener { clickItem.invoke(person, "like") }
            binding.personCard.setOnClickListener { clickItem.invoke(person, "itemInfo") }
            binding.personCard.setOnLongClickListener {
                clickItem.invoke(person, "details")
                true
            }
        }
    }
}