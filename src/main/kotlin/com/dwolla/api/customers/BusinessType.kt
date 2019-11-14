package com.dwolla.api.customers

import com.google.gson.annotations.SerializedName

enum class BusinessType(
    @JvmField val value: String,
    @JvmField val displayName: String
) {

    @SerializedName("corporation")
    CORPORATION("corporation", "Corporation"),

    @SerializedName("llc")
    LLC("llc", "LLC"),

    @SerializedName("partnership")
    PARTNERSHIP("partnership", "Partnership"),

    @SerializedName("soleProprietorship")
    SOLE_PROPRIETORSHIP("soleProprietorship", "Sole Proprietorship"),
}
