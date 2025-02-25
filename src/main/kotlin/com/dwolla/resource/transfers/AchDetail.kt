package com.dwolla.resource.transfers

data class AchDetail(
    @JvmField val addenda: AchAddenda?,
    @JvmField val traceId: String?
)
