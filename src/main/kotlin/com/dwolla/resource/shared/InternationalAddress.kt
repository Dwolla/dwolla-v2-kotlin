package com.dwolla.resource.shared

import com.dwolla.shared.Country

data class InternationalAddress(
    @JvmField val address1: String,
    @JvmField val address2: String?,
    @JvmField val address3: String?,
    @JvmField val city: String,
    @JvmField val stateProvinceRegion: String,
    @JvmField val country: Country,
    @JvmField val postalCode: String?
)
