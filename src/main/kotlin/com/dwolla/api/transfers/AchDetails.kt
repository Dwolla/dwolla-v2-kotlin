package com.dwolla.api.transfers

internal data class AchDetails(
    @JvmField val source: AchDetail? = null,
    @JvmField val destination: AchDetail? = null
)
