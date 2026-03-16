package com.example.booksapp

import com.google.gson.annotations.SerializedName


data class SaleInfoDto(

    @SerializedName("country")
    var country: String? = null,
    @SerializedName("saleability")
    var saleability: String? = null,
    @SerializedName("isEbook")
    var isEbook: Boolean? = null

)