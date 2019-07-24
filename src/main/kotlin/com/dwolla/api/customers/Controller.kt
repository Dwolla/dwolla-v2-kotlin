package com.dwolla.api.customers

import com.dwolla.shared.DateOfBirth

data class Controller(
    @JvmField val firstName: String,
    @JvmField val lastName: String,
    @JvmField val title: String,
    @JvmField val dateOfBirth: DateOfBirth,
    @JvmField val address: ControllerAddress,
    @JvmField val ssn: String? = null,
    @JvmField val passport: Passport? = null
)
