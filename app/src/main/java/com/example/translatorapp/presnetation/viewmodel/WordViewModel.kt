package com.example.translatorapp.presnetation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.translatorapp.domain.model.WordItem
import com.example.translatorapp.domain.repository.WordRepository
import com.example.translatorapp.util.AppResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.lang.Thread.State
import javax.inject.Inject

@HiltViewModel
class WordViewModel @Inject constructor(
    private val wordRepository: WordRepository
) : ViewModel() {


    private val _state = MutableStateFlow(WordState())
    val state: StateFlow<WordState>
        get() = _state

    private var searchJob: Job? = null

    fun onEvent(event: WordEvent) {
        when (event) {
            is WordEvent.OnSearchWordChangedEvent -> {

                _state.update {
                    it.copy(
                        word = event.word
                    )
                }
            }

            is WordEvent.OnSearchClicked -> {
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    searchWord()
                }
            }
        }
    }

    private fun searchWord() {
        viewModelScope.launch {
            wordRepository.getWordResult(_state.value.word).collect { res ->
                when (res) {
                    is AppResult.Done -> {
                        _state.update {
                            it.copy(
                                wordStatus = WordStatus.DONE,
                                wordItem = res.data ?: WordItem.empty
                            )
                        }
                    }

                    is AppResult.Loading -> {
                        _state.update {
                            it.copy(
                                wordStatus = WordStatus.LOADING
                            )
                        }
                    }

                    is AppResult.Error -> {
                        _state.update {
                            it.copy(
                                wordStatus = WordStatus.ERROR,
                                error = res.message ?: ""
                            )
                        }
                    }
                }
            }
        }
    }
}
