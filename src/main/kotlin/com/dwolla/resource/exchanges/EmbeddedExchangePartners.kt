package com.dwolla.resource.exchanges

import com.google.gson.annotations.SerializedName

data class EmbeddedExchangePartners(
    @JvmField
    @SerializedName("exchange-partners")
    val exchangePartners: List<ExchangePartner>
)
