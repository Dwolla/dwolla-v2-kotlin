package com.dwolla.resource.transfers

import com.google.gson.annotations.SerializedName

enum class ProcessingTypes(@JvmField val destination: String) {
    @SerializedName("real-time-payments")
    REAL_TIME_PAYMENTS("real-time-payments")
}
