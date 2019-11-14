package com.dwolla.resource

import com.google.gson.annotations.SerializedName

data class DwollaAuthError(
    @JvmField val error: String,

    @JvmField @SerializedName("error_description")
    val errorDescription: String?,

    @JvmField @SerializedName("error_uri")
    val errorUri: String?
)
