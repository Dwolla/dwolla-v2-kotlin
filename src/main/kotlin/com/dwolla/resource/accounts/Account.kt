package com.dwolla.resource.accounts

import com.dwolla.resource.HalResource
import com.dwolla.resource.Links
import com.dwolla.resource.shared.USAddress
import java.time.Instant

data class Account(
    @JvmField val _links: Links,
    @JvmField val id: String,
    @JvmField val name: String,
    @JvmField val address: USAddress?,
    @JvmField val timezoneOffset: Int?,
    @JvmField val type: AccountType?,
    @JvmField val phone: String?,
    @JvmField val website: String?,
    @JvmField val authorizedRep: String?,
    @JvmField val preferredBusinessName: String?,
    @JvmField val created: Instant?
) : HalResource() {
    override fun getLinks(): Links {
        return _links
    }
}
