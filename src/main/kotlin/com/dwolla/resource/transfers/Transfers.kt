package com.dwolla.resource.transfers

import com.dwolla.resource.HalResource
import com.dwolla.resource.Links

data class Transfers(
    @JvmField val _links: Links,
    @JvmField val _embedded: EmbeddedTransfers,
    @JvmField val total: Int
) : HalResource() {
    override fun getLinks(): Links {
        return _links
    }
}

data class EmbeddedTransfers(
    @JvmField val transfers: List<Transfer>
)

