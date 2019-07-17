package com.dwolla.resource.fundingsources

import com.dwolla.resource.HalResource
import com.dwolla.resource.Links

data class FundingSources(
    @JvmField val _links: Links,
    @JvmField val _embedded: EmbeddedFundingSources,
    @JvmField val total: Int
) : HalResource(_links)
