package com.dwolla.shared

data class Amount @JvmOverloads constructor(
    @JvmField val value: String,
    @JvmField val currency: Currency = Currency.USD
)
