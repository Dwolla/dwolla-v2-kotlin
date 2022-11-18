package com.dwolla.resource.exchanges

import com.dwolla.resource.HalResource
import com.dwolla.resource.Links
import java.time.Instant

data class Exchange(
    @JvmField val _links: Links,
    @JvmField val id: String,
    @JvmField val status: ExchangeStatus,
    @JvmField val created: Instant
) : HalResource() {
    override fun getLinks(): Links {
        return _links
    }
}
