package com.dwolla.resource.transfers

import com.dwolla.resource.HalResource
import com.dwolla.resource.Links

data class FacilitatorFees(
    @JvmField val _links: Links,
    @JvmField val _embedded: EmbeddedFees,
    @JvmField val transactions: List<Transfer>,
    @JvmField val total: Int
) : HalResource() {
    override fun getLinks(): Links {
        return _links
    }
}

data class EmbeddedFees(
    @JvmField val fees: List<Transfer>
)
