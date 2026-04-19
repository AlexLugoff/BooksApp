package com.example.booksapp.core.network.model

import com.google.gson.annotations.SerializedName


data class VolumeInfoDto(

    @SerializedName("title")
    var title: String? = null,
    @SerializedName("authors")
    var authors: ArrayList<String> = arrayListOf(),
    @SerializedName("publisher")
    var publisher: String? = null,
    @SerializedName("publishedDate")
    var publishedDate: String? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("industryIdentifiers")
    var industryIdentifiers: ArrayList<IndustryIdentifiersDto> = arrayListOf(),
    @SerializedName("readingModes")
    var readingModes: ReadingModesDto? = ReadingModesDto(),
    @SerializedName("pageCount")
    var pageCount: Int? = null,
    @SerializedName("printType")
    var printType: String? = null,
    @SerializedName("categories")
    var categories: ArrayList<String> = arrayListOf(),
    @SerializedName("maturityRating")
    var maturityRating: String? = null,
    @SerializedName("allowAnonLogging")
    var allowAnonLogging: Boolean? = null,
    @SerializedName("contentVersion")
    var contentVersion: String? = null,
    @SerializedName("panelizationSummary")
    var panelizationSummary: PanelizationSummaryDto? = PanelizationSummaryDto(),
    @SerializedName("imageLinks")
    var imageLinks: ImageLinksDto? = ImageLinksDto(),
    @SerializedName("language")
    var language: String? = null,
    @SerializedName("previewLink")
    var previewLink: String? = null,
    @SerializedName("infoLink")
    var infoLink: String? = null,
    @SerializedName("canonicalVolumeLink")
    var canonicalVolumeLink: String? = null

)
