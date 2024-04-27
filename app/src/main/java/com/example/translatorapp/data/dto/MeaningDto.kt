package com.example.translatorapp.data.dto


import com.google.gson.annotations.SerializedName

data class MeaningDto(

    @SerializedName("definitions")
    val definitions: List<DefinitionDto>?,
    @SerializedName("partOfSpeech")
    val partOfSpeech: String?,
)