package com.dwolla.resource.documents

import com.google.gson.annotations.SerializedName

data class AllFailureReasons(
    @JvmField @SerializedName("reason")
    val reason: String,

    @JvmField @SerializedName("description")
    val description: String
)