package com.dwolla.shared

import com.google.gson.annotations.SerializedName

enum class Currency(@JvmField val value: String) {
    @SerializedName("USD")
    USD("USD")
}
