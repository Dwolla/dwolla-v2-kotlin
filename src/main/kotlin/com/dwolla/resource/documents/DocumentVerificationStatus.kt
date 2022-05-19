package com.dwolla.resource.documents

import com.google.gson.annotations.SerializedName

enum class DocumentVerificationStatus(@JvmField val value: String) {
    @SerializedName("pending")
    PENDING("pending"),

    @SerializedName("accepted")
    ACCEPTED("accepted"),

    @SerializedName("rejected")
    REJECTED("rejected")
}
