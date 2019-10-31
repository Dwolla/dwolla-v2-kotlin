package com.dwolla.api.fundingsources

import com.google.gson.annotations.SerializedName

enum class FundingSourceChannel(@JvmField val value: String) {
    @SerializedName("ach")
    ACH("ach"),

    @SerializedName("wire")
    WIRE("wire"),
}
