package com.dwolla.api.transfers

data class Clearing(
    @JvmField val source: ClearingType? = null,
    @JvmField val destination: ClearingType? = null
)
