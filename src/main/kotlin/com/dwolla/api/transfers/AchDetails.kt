package com.dwolla.api.transfers

data class AchDetails(
    @JvmField val source: AchDetail? = null,
    @JvmField val destination: AchDetail? = null
)
