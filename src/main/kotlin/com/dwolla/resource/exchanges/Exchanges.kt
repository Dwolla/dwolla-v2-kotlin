package com.dwolla.resource.exchanges

import com.dwolla.resource.HalResource
import com.dwolla.resource.Links

data class Exchanges(
    @JvmField val _links: Links,
    @JvmField val _embedded: EmbeddedExchanges
) : HalResource() {
    override fun getLinks(): Links {
        return _links
    }
}
