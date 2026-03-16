package com.example.booksapp

import com.google.gson.annotations.SerializedName


data class BookShelfDto(

    @SerializedName("kind")
    var kind: String? = null,
    @SerializedName("totalItems")
    var totalItems: Int? = null,
    @SerializedName("items")
    var items: ArrayList<ItemsDto> = arrayListOf()

)