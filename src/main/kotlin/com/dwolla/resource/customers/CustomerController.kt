package com.dwolla.resource.customers

import com.dwolla.resource.shared.InternationalAddress

data class CustomerController(
    @JvmField val firstName: String,
    @JvmField val lastName: String,
    @JvmField val title: String,
    @JvmField val dateOfBirth: String,
    @JvmField val address: InternationalAddress,
    @JvmField val ssn: String?,
    @JvmField val passport: String?
)
