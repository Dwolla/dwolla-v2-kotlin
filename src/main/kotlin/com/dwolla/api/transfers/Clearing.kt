package com.dwolla.api.transfers

data class Clearing(
    @JvmField val source: SourceClearingType? = null,
    @JvmField val destination: DestinationClearingType? = null
)
