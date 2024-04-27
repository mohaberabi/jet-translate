package com.example.translatorapp.presnetation.viewmodel

sealed class WordEvent {
    data class OnSearchWordChangedEvent(val word: String) : WordEvent()
    data object OnSearchClicked : WordEvent()
}