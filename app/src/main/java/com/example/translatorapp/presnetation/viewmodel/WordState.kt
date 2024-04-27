package com.example.translatorapp.presnetation.viewmodel

import com.example.translatorapp.domain.model.WordItem


enum class WordStatus {
    LOADING, ERROR, DONE, INITIAL
}

data class WordState(
    val word: String = "",
    val wordItem: WordItem = WordItem.empty,
    val wordStatus: WordStatus = WordStatus.INITIAL,
    val error: String = ""
)
