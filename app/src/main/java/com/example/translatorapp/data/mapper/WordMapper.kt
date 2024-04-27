package com.example.translatorapp.data.mapper

import com.example.translatorapp.data.dto.DefinitionDto
import com.example.translatorapp.data.dto.MeaningDto
import com.example.translatorapp.data.dto.WordDto
import com.example.translatorapp.domain.model.Defination
import com.example.translatorapp.domain.model.Meaning
import com.example.translatorapp.domain.model.WordItem


fun WordDto.toWordItem(): WordItem {
    return WordItem(
        word = word ?: "",
        meanings = meanings?.map {
            it.toMean()
        } ?: emptyList(),
        phonetic = phonetic ?: ""
    )
}


fun MeaningDto.toMean(): Meaning {
    return Meaning(
        definitions = definitions?.map { it.toDefination() } ?: emptyList(),
        partOfSpeech = partOfSpeech ?: ""
    )
}

fun DefinitionDto.toDefination(): Defination {
    return Defination(definition = definition ?: "", example = example ?: "")
}