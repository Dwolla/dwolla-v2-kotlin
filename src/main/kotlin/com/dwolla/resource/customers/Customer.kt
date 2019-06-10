package com.dwolla.resource.customers

import com.dwolla.resource.Links
import com.dwolla.resource.HalResource
import java.time.Instant
import java.util.Optional

data class Customer(
    @JvmField val _links: Links,
    @JvmField val id: String,
    @JvmField val firstName: String,
    @JvmField val lastName: String,
    @JvmField val email: Optional<String>,
    @JvmField val type: CustomerType,
    @JvmField val status: CustomerStatus,
    @JvmField val created: Instant,
    @JvmField val address1: Optional<String>,
    @JvmField val address2: Optional<String>,
    @JvmField val city: Optional<String>,
    @JvmField val state: Optional<String>,
    @JvmField val postalCode: Optional<String>,
    @JvmField val phone: Optional<String>,
    @JvmField val businessName: Optional<String>,
    @JvmField val doingBusinessAs: Optional<String>,
    @JvmField val website: Optional<String>,
    @JvmField val correlationId: String,
    @JvmField val controller: Optional<CustomerController>
) : HalResource(_links)
