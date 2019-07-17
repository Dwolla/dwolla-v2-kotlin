package com.dwolla.resource.fundingsources

import java.util.Optional

data class IavAccountHolders(
    @JvmField val selected: Optional<String>,
    @JvmField val other: Optional<List<String>>
)
