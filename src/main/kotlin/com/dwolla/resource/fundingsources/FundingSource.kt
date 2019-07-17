package com.dwolla.resource.fundingsources

import com.dwolla.resource.HalResource
import com.dwolla.resource.Links
import java.time.Instant
import java.util.Optional

data class FundingSource(
    @JvmField val _links: Links,
    @JvmField val id: String,
    @JvmField val status: FundingSourceStatus,
    @JvmField val type: FundingSourceType,
    @JvmField val bankAccountType: Optional<BankAccountType>,
    @JvmField val name: String,
    @JvmField val created: Instant,
    @JvmField val balance: Optional<Money>,
    @JvmField val removed: Boolean,
    @JvmField val channels: List<FundingSourceChannel>,
    @JvmField val bankName: Optional<String>,
    @JvmField val iavAccountHolders: Optional<IavAccountHolders>,
    @JvmField val fingerprint: Optional<String>
) : HalResource(_links)
