package com.dwolla.resource.fundingsources

import com.google.gson.annotations.SerializedName

enum class FundingSourceStatus(@JvmField val value: String) {
    @SerializedName("verified")
    VERIFIED("verified"),

    @SerializedName("unverified")
    UNVERIFIED("unverified")
}
