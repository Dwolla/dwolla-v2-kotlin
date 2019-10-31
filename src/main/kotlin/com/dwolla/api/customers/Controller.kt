package com.dwolla.api.customers

import com.dwolla.api.shared.DateOfBirth
import com.dwolla.api.shared.InternationalAddress
import com.dwolla.api.shared.Passport

data class Controller(
    @JvmField val firstName: String,
    @JvmField val lastName: String,
    @JvmField val title: String,
    @JvmField val dateOfBirth: DateOfBirth,
    @JvmField val address: InternationalAddress,
    @JvmField val ssn: String? = null,
    @JvmField val passport: Passport? = null
)
