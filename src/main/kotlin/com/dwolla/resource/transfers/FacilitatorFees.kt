package com.dwolla.resource.transfers

import com.dwolla.resource.HalResource
import com.dwolla.resource.Links

data class FacilitatorFees(
    @JvmField val _links: Links,
    @JvmField val fees: List<Transfer>,
    @JvmField val total: Int
) : HalResource() {
    override fun getLinks(): Links {
        return _links
    }
}
