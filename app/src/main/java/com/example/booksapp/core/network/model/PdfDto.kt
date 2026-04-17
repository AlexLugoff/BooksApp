package com.example.booksapp.core.network.model

import com.google.gson.annotations.SerializedName


data class PdfDto(

    @SerializedName("isAvailable")
    var isAvailable: Boolean? = null,
    @SerializedName("acsTokenLink")
    var acsTokenLink: String? = null

)
