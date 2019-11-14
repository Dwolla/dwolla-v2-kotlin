package com.dwolla.resource.beneficialowners

import com.dwolla.resource.HalResource
import com.dwolla.resource.Links

data class BeneficialOwners(
    @JvmField val _links: Links,
    @JvmField val _embedded: EmbeddedBeneficialOwners,
    @JvmField val total: Int
) : HalResource() {
    override fun getLinks(): Links {
        return _links
    }
}
