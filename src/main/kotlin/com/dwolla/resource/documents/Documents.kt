package com.dwolla.resource.documents

import com.dwolla.resource.HalResource
import com.dwolla.resource.Links

data class Documents(
    @JvmField val _links: Links,
    @JvmField val _embedded: EmbeddedDocuments,
    @JvmField val total: Int
) : HalResource() {
    override fun getLinks(): Links {
        return _links
    }
}
