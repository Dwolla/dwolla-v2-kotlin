package com.dwolla.resource.beneficialowners

import com.google.gson.annotations.SerializedName

enum class BeneficialOwnerVerificationStatus(@JvmField val value: String) {
    @SerializedName("unverified")
    UNVERIFIED("unverified"),

    @SerializedName("document")
    DOCUMENT("document"),

    @SerializedName("verified")
    VERIFIED("verified"),

    @SerializedName("incomplete")
    INCOMPLETE("incomplete"),
}
