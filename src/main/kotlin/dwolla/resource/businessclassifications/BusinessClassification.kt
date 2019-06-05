package dwolla.resource.businessclassifications

import dwolla.resource.HalResource
import dwolla.resource.Links

data class BusinessClassification(
    @JvmField val _links: Links,
    @JvmField val _embedded: EmbeddedIndustryClassifications,
    @JvmField val id: String,
    @JvmField val name: String
) : HalResource(_links)
