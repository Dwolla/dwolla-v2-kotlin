package com.dwolla.resource.exchanges

import com.dwolla.resource.HalResource
import com.dwolla.resource.Links

data class ExchangePartners(
    @JvmField val _links: Links,
    @JvmField val _embedded: EmbeddedExchangePartners
) : HalResource() {
    override fun getLinks(): Links {
        return _links
    }
}
