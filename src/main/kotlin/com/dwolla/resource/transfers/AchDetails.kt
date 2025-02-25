package com.dwolla.resource.transfers

data class AchDetails(
    @JvmField val source: AchDetail?,
    @JvmField val destination: AchDetail?
)
