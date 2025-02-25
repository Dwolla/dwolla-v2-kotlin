package com.dwolla.api.transfers

import com.google.gson.annotations.SerializedName

enum class SourceClearingType(@JvmField val value: String) {
    @SerializedName("next-available")
    NEXT_AVAILABLE("next-available"),

    @SerializedName("standard")
    STANDARD("standard")
}

enum class DestinationClearingType(@JvmField val value: String) {
    @SerializedName("next-available")
    NEXT_AVAILABLE("next-available")
}

