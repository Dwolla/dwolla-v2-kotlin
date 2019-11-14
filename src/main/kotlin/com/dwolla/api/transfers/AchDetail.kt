package com.dwolla.api.transfers

internal data class AchDetail(
    @JvmField val addenda: Addenda,
    @JvmField val traceId: String
)
