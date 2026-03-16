package com.example.booksapp

import com.google.gson.annotations.SerializedName


data class SearchInfoDto(

    @SerializedName("textSnippet")
    var textSnippet: String? = null

)