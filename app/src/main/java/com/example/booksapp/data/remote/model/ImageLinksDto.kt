package com.example.booksapp

import com.google.gson.annotations.SerializedName


data class ImageLinksDto(

    @SerializedName("smallThumbnail")
    var smallThumbnail: String? = null,
    @SerializedName("thumbnail")
    var thumbnail: String? = null

)