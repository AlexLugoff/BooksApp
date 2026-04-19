package com.example.booksapp.core.network.model

import com.google.gson.annotations.SerializedName


data class EpubDto(

    @SerializedName("isAvailable")
    var isAvailable: Boolean? = null

)
