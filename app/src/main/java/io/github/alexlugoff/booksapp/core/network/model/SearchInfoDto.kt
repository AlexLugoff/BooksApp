package io.github.alexlugoff.booksapp.core.network.model

import com.google.gson.annotations.SerializedName


data class SearchInfoDto(

    @SerializedName("textSnippet")
    var textSnippet: String? = null

)
