package com.dwolla.resource.customers

import com.dwolla.resource.HalResource
import com.dwolla.resource.Links

data class Customers(
    @JvmField val _links: Links,
    @JvmField val _embedded: EmbeddedCustomers,
    @JvmField val total: Int
) : HalResource(_links)
