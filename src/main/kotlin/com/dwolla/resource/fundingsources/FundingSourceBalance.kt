package com.dwolla.resource.fundingsources

import com.dwolla.resource.Links
import com.dwolla.shared.Money
import com.google.gson.annotations.SerializedName
import java.time.Instant

data class FundingSourceBalance(
    val _links: Links,
    val balance: Money?,
    val total: Money?,
    val lastUpdated: Instant?,
    val status: FundingSourceBalanceStatus?
)

enum class FundingSourceBalanceStatus(@JvmField val value: String) {
    @SerializedName("processing")
    PROCESSING("processing"),
}
