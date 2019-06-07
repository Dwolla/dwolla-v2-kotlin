package com.dwolla.resource.businessclassifications

import com.dwolla.resource.HalResource
import com.dwolla.resource.Links

data class BusinessClassification(
        @JvmField val _links: Links,
        @JvmField val _embedded: EmbeddedIndustryClassifications,
        @JvmField val id: String,
        @JvmField val name: String
) : HalResource(_links)
