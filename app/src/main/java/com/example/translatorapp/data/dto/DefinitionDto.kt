package com.example.translatorapp.data.dto


import com.google.gson.annotations.SerializedName

data class DefinitionDto(

    @SerializedName("definition")
    val definition: String?,
    @SerializedName("example")
    val example: String?,
)