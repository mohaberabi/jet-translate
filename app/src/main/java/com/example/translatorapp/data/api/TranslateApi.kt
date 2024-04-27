package com.example.translatorapp.data.api

import com.example.translatorapp.data.dto.WordResultDto
import retrofit2.http.GET
import retrofit2.http.Path

interface TranslateApi {


    @GET("{word}")

    suspend fun getWordResult(@Path("word") word: String): WordResultDto?

    companion object {


        const val BASE_URL = "https://api.dictionaryapi.dev/api/v2/entries/en/"
    }
}