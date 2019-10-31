package com.dwolla.resource.fundingsources

data class IavAccountHolders(
    @JvmField val selected: String?,
    @JvmField val other: List<String>?
)
