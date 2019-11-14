package com.dwolla.resource.fundingsources

import com.dwolla.resource.Links
import java.time.Instant

data class MicroDeposits(
    @JvmField val _links: Links,
    @JvmField val created: Instant,
    @JvmField val status: MicroDepositStatus,
    @JvmField val failure: MicroDepositFailure?
)
