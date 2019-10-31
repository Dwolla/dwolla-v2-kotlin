package com.dwolla.resource.beneficialowners

import com.dwolla.resource.HalResource
import com.dwolla.resource.Links
import com.dwolla.resource.shared.InternationalAddress
import java.time.Instant

data class BeneficialOwner(
    @JvmField val _links: Links,
    @JvmField val id: String,
    @JvmField val firstName: String,
    @JvmField val lastName: String,
    @JvmField val address: InternationalAddress,
    @JvmField val verificationStatus: BeneficialOwnerVerificationStatus,
    @JvmField val created: Instant
) : HalResource() {
    override fun getLinks(): Links {
        return _links
    }
}
