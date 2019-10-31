package com.dwolla.resource

data class DwollaError(
    @JvmField val _links: Links,
    @JvmField val code: String,
    @JvmField val message: String,
    @JvmField val _embedded: EmbeddedErrors?
) : HalResource() {
    override fun getLinks(): Links {
        return _links
    }
}
