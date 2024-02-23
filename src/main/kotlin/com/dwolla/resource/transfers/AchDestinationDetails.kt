package com.dwolla.resource.transfers

data class AchDestinationDetails(
    @JvmField val addenda: AchAddenda?,
    @JvmField val traceId: String?
)
