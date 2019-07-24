package com.dwolla.resource.customers

import java.util.Optional

data class CustomerController(
    @JvmField val firstName: String,
    @JvmField val lastName: String,
    @JvmField val title: String,
    @JvmField val dateOfBirth: String, // TODO?
    @JvmField val address: CustomerControllerAddress,
    @JvmField val ssn: Optional<String>,
    @JvmField val passport: Optional<String>
)
