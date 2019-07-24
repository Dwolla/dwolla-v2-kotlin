package com.dwolla.api.customers

import com.dwolla.shared.Country

data class ControllerAddress(
    @JvmField val address1: String,
    @JvmField val address2: String? = null,
    @JvmField val address3: String? = null,
    @JvmField val city: String,
    @JvmField val stateProvinceRegion: String,
    @JvmField val postalCode: String? = null,
    @JvmField val country: Country
)
