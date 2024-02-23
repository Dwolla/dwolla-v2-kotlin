package com.dwolla.resource.transfers

import com.dwolla.resource.HalResource
import com.dwolla.resource.Links
import com.dwolla.resource.fundingsources.EmbeddedFundingSources

data class Transfers(
    @JvmField val _links: Links,
    @JvmField val _embedded: EmbeddedTransfers,
    @JvmField val total: Int
) : HalResource() {
    override fun getLinks(): Links {
        return _links
    }
}
