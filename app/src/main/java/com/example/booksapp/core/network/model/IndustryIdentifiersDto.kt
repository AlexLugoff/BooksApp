package com.example.booksapp.core.network.model

import com.google.gson.annotations.SerializedName


data class IndustryIdentifiersDto(

    @SerializedName("type")
    var type: String? = null,
    @SerializedName("identifier")
    var identifier: String? = null

)
