package com.example.booksapp.core.network.model

import com.google.gson.annotations.SerializedName


data class PanelizationSummaryDto(

    @SerializedName("containsEpubBubbles")
    var containsEpubBubbles: Boolean? = null,
    @SerializedName("containsImageBubbles")
    var containsImageBubbles: Boolean? = null

)
