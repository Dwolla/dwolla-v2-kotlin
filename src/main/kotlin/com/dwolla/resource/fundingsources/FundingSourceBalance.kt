package com.dwolla.resource.fundingsources

import com.dwolla.resource.Links
import com.google.gson.annotations.SerializedName
import java.time.Instant
import java.util.Optional

data class FundingSourceBalance(
    val _links: Links,
    val balance: Optional<Money>,
    val total: Optional<Money>,
    val lastUpdated: Optional<Instant>,
    val status: Optional<FundingSourceBalanceStatus>
)

enum class FundingSourceBalanceStatus(@JvmField val value: String) {
    @SerializedName("processing")
    PROCESSING("processing"),
}
