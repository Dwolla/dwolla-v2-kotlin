package com.dwolla.resource.fundingsources

import com.dwolla.resource.HalResource
import com.dwolla.resource.Links
import com.dwolla.shared.Money
import java.time.Instant

data class FundingSource(
    @JvmField val _links: Links,
    @JvmField val id: String,
    @JvmField val status: FundingSourceStatus,
    @JvmField val type: FundingSourceType,
    @JvmField val bankAccountType: BankAccountType?,
    @JvmField val name: String,
    @JvmField val created: Instant,
    @JvmField val balance: Money?,
    @JvmField val removed: Boolean,
    @JvmField val channels: List<FundingSourceChannel>,
    @JvmField val bankName: String?,
    @JvmField val iavAccountHolders: IavAccountHolders?,
    @JvmField val fingerprint: String?,
    @JvmField val verified: Boolean
) : HalResource() {
    override fun getLinks(): Links {
        return _links
    }
}
