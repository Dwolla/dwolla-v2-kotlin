package com.dwolla.resource.businessclassifications

import com.dwolla.resource.HalResource
import com.dwolla.resource.Links

data class BusinessClassifications(
    @JvmField val _links: Links,
    @JvmField val _embedded: EmbeddedBusinessClassifications
) : HalResource(_links)
