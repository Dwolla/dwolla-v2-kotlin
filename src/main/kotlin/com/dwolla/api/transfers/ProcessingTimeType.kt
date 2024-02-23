package com.dwolla.api.transfers

import com.google.gson.annotations.SerializedName

enum class ProcessingTimeType(@JvmField val value: String) {
    @SerializedName("real-time-payments")
    REAL_TIME_PAYMENTS("real-time-payments"),
}

