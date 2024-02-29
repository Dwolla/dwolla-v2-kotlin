package com.dwolla.api.transfers

import com.google.gson.annotations.SerializedName

enum class ClearingType(@JvmField val value: String) {
    @SerializedName("standard")
    STANDARD("standard"),

    @SerializedName("next-available")
    NEXT_AVAILABLE("next-available"),

    @SerializedName("next-day")
    NEXT_DAY("next-day"), // TODO: separate enum for source and destination types?

    @SerializedName("same-day")
    SAME_DAY("same-day"),
}
