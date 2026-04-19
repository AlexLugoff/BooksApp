package com.example.booksapp.core.network.model

import com.google.gson.annotations.SerializedName


data class ReadingModesDto(

    @SerializedName("text")
    var text: Boolean? = null,
    @SerializedName("image")
    var image: Boolean? = null

)
