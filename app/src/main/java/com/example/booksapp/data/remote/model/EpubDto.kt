package com.example.booksapp

import com.google.gson.annotations.SerializedName


data class EpubDto(

    @SerializedName("isAvailable")
    var isAvailable: Boolean? = null

)