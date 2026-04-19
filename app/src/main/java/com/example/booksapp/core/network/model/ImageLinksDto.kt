package com.example.booksapp.core.network.model

import com.google.gson.annotations.SerializedName


data class ImageLinksDto(

    @SerializedName("smallThumbnail")
    var smallThumbnail: String? = null,
    @SerializedName("thumbnail")
    var thumbnail: String? = null

)
