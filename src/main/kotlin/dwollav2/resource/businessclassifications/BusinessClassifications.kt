package dwollav2.resource.businessclassifications

import dwollav2.resource.Links

data class BusinessClassifications(
    @JvmField val _links: Links,
    @JvmField val _embedded: EmbeddedBusinessClassifications
)
