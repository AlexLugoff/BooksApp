package com.example.booksapp

import com.google.gson.annotations.SerializedName


data class ReadingModesDto(

    @SerializedName("text")
    var text: Boolean? = null,
    @SerializedName("image")
    var image: Boolean? = null

)