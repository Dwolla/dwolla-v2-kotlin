package com.dwolla.resource.transfers

import com.dwolla.resource.HalResource
import com.dwolla.resource.Links
import java.time.Instant

data class FailureReason(
    @JvmField val _links: Links,
    @JvmField val code: String,
    @JvmField val description: String,
    @JvmField val explanation: String,
    @JvmField val created: Instant
    ) : HalResource() {
    override fun getLinks(): Links {
        return _links
    }
}
