package com.dwolla.resource.customers

import com.dwolla.resource.HalResource
import com.dwolla.resource.Links
import com.dwolla.shared.USState
import java.time.Instant

data class Customer(
    @JvmField val _links: Links,
    @JvmField val id: String,
    @JvmField val firstName: String,
    @JvmField val lastName: String,
    @JvmField val email: String?,
    @JvmField val type: CustomerType,
    @JvmField val status: CustomerStatus,
    @JvmField val created: Instant,
    @JvmField val address1: String?,
    @JvmField val address2: String?,
    @JvmField val city: String?,
    @JvmField val state: USState?,
    @JvmField val postalCode: String?,
    @JvmField val phone: String?,
    @JvmField val businessName: String?,
    @JvmField val doingBusinessAs: String?,
    @JvmField val website: String?,
    @JvmField val correlationId: String,
    @JvmField val controller: CustomerController?
) : HalResource() {
    override fun getLinks(): Links {
        return _links
    }
}
