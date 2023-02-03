package com.dwolla.resource.beneficialowners

import com.google.gson.annotations.SerializedName

enum class BeneficialOwnershipStatus(@JvmField val value: String) {
    @SerializedName("certified")
    CERTIFIED("certified"),

    @SerializedName("exempt")
    EXEMPT("exempt"),

    @SerializedName("recertify")
    RECERTIFY("recertify"),

    @SerializedName("uncertified")
    UNCERTIFIED("uncertified")
}
