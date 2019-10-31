package com.dwolla.api.shared

import com.dwolla.shared.Country

data class Passport(
    @JvmField val number: String,
    @JvmField val country: Country
)
