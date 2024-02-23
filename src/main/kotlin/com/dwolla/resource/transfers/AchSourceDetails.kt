package com.dwolla.resource.transfers

data class AchSourceDetails(
    @JvmField val addenda: AchAddenda?,
    @JvmField val traceId: String?

)
