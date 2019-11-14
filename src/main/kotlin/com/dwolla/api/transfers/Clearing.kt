package com.dwolla.api.transfers

internal data class Clearing(
    @JvmField val source: ClearingType? = null,
    @JvmField val destination: ClearingType? = null
)
