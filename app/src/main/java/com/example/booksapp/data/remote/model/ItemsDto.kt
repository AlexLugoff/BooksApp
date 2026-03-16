package com.example.booksapp

import com.google.gson.annotations.SerializedName


data class ItemsDto(

    @SerializedName("kind")
    var kind: String? = null,
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("etag")
    var etag: String? = null,
    @SerializedName("selfLink")
    var selfLink: String? = null,
    @SerializedName("volumeInfo")
    var volumeInfo: VolumeInfoDto? = VolumeInfoDto(),
    @SerializedName("saleInfo")
    var saleInfo: SaleInfoDto? = SaleInfoDto(),
    @SerializedName("accessInfo")
    var accessInfo: AccessInfoDto? = AccessInfoDto(),
    @SerializedName("searchInfo")
    var searchInfo: SearchInfoDto? = SearchInfoDto()

)