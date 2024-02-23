package com.dwolla.resource.transfers

data class AchDetails(
    @JvmField val source: AchSourceDetails?,
    @JvmField val destination: AchDestinationDetails?
)
