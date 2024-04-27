package com.example.translatorapp.domain.model

import com.example.translatorapp.data.dto.MeaningDto
import com.google.gson.annotations.SerializedName

data class WordItem(
    val meanings: List<Meaning>,
    val word: String,
    val phonetic: String
) {


    companion object {
        val empty = WordItem(emptyList(), "", "")
    }
}