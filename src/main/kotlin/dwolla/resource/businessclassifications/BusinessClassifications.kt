package dwolla.resource.businessclassifications

import dwolla.resource.HalResource
import dwolla.resource.Links

data class BusinessClassifications(
    @JvmField val _links: Links,
    @JvmField val _embedded: EmbeddedBusinessClassifications
) : HalResource(_links)
