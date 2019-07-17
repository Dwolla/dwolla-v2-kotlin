package com.dwolla.resource.fundingsources

import com.google.gson.annotations.SerializedName

enum class MicroDepositStatus(@JvmField val value: String) {
    @SerializedName("pending")
    PENDING("pending"),

    @SerializedName("processed")
    PROCESSED("processed"),

    @SerializedName("failed")
    FAILED("failed"),
}
