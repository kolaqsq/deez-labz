package com.example.hw4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.hw4.databinding.PersonDetailsBinding

class PersonDetailsFragment : Fragment() {
    private lateinit var binding: PersonDetailsBinding

    private lateinit var person: Person

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PersonDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Отдаем данные в frame_item_detail если есть изменения
        if (this::person.isInitialized) {
            binding.cardName.text = person.Name
            binding.cardPhoto.load(person.Image) {
                transformations(
                    RoundedCornersTransformation(
                        20f
                    )
                )
            }
            binding.cardDates.text = person.Date
            binding.cardInfo.text = person.Description
            binding.cardSex.text = person.Gender
        }
    }

    // Получение picture
    fun getPicture(picture: Person) {
        person = picture
    }
}