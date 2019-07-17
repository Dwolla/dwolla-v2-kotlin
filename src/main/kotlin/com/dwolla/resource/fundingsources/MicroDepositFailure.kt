package com.dwolla.resource.fundingsources

data class MicroDepositFailure(
    @JvmField val code: MicroDepositFailureCode,
    @JvmField val description: String
)
