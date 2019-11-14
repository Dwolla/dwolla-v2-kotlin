package com.dwolla.resource.shared

import com.dwolla.shared.USState

data class USAddress(
    @JvmField val address1: String,
    @JvmField val address2: String?,
    @JvmField val city: String,
    @JvmField val state: USState,
    @JvmField val postalCode: String
)
