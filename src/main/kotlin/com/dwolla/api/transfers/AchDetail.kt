package com.dwolla.api.transfers

data class AchDetail(
    @JvmField val addenda: Addenda,
    @JvmField val traceId: String
)
