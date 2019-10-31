package com.dwolla.resource.fundingsources

import com.google.gson.annotations.SerializedName

enum class FundingSourceType(@JvmField val value: String) {
    @SerializedName("bank")
    BANK("bank"),

    @SerializedName("balance")
    BALANCE("balance")
}
