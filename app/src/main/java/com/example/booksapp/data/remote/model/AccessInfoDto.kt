package com.example.booksapp

import com.google.gson.annotations.SerializedName


data class AccessInfoDto(

    @SerializedName("country")
    var country: String? = null,
    @SerializedName("viewability")
    var viewability: String? = null,
    @SerializedName("embeddable")
    var embeddable: Boolean? = null,
    @SerializedName("publicDomain")
    var publicDomain: Boolean? = null,
    @SerializedName("textToSpeechPermission")
    var textToSpeechPermission: String? = null,
    @SerializedName("epub")
    var epub: EpubDto? = EpubDto(),
    @SerializedName("pdf")
    var pdf: PdfDto? = PdfDto(),
    @SerializedName("webReaderLink")
    var webReaderLink: String? = null,
    @SerializedName("accessViewStatus")
    var accessViewStatus: String? = null,
    @SerializedName("quoteSharingAllowed")
    var quoteSharingAllowed: Boolean? = null

)