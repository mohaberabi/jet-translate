package com.example.translatorapp.data.dto


import com.google.gson.annotations.SerializedName

data class WordDto(
    @SerializedName("meanings")
    val meanings: List<MeaningDto>?,
    @SerializedName("phonetic")
    val phonetic: String?,
    @SerializedName("word")
    val word: String?,
)