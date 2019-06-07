package com.dwolla.resource.documents

import com.google.gson.annotations.SerializedName

enum class DocumentType(@JvmField val value: String) {
    @SerializedName("passport")
    PASSPORT("passport"),

    @SerializedName("license")
    LICENSE("license"),

    @SerializedName("idCard")
    ID_CARD("idCard"),

    @SerializedName("other")
    OTHER("other")
}
