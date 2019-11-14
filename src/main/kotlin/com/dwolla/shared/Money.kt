package com.dwolla.shared

data class Money(
    @JvmField val value: String,
    @JvmField val currency: Currency = Currency.USD
)
