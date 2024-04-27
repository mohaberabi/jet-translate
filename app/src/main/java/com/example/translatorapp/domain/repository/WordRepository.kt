package com.example.translatorapp.domain.repository

import com.example.translatorapp.domain.model.WordItem
import com.example.translatorapp.util.AppResult
import kotlinx.coroutines.flow.Flow

interface WordRepository {


    suspend fun getWordResult(word: String): Flow<AppResult<WordItem>>
}