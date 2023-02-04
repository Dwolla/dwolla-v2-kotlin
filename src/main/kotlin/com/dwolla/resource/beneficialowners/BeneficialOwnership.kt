package com.dwolla.resource.beneficialowners

import com.dwolla.resource.HalResource
import com.dwolla.resource.Links

data class BeneficialOwnership(
    @JvmField val _links: Links,
    @JvmField val status: BeneficialOwnershipStatus
) : HalResource() {
    override fun getLinks(): Links {
        return _links
    }
}
