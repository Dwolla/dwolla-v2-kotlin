package com.dwolla.api.transfers

import com.dwolla.shared.Amount

data class Fee(
    @JvmField val chargeTo: String,
    @JvmField val amount: Amount
) // TODO: overload chargeTo: HalResource?
