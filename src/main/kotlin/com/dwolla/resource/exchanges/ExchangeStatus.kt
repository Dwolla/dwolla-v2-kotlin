package com.dwolla.resource.exchanges

import com.google.gson.annotations.SerializedName

enum class ExchangeStatus {
    @SerializedName("active")
    ACTIVE,

    @SerializedName("deactivated")
    DEACTIVATED,

    @SerializedName("removed")
    REMOVED
}

typealias ExchangePartnerStatus = ExchangeStatus;
