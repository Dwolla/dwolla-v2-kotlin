package dwollav2.resource.businessclassifications

import dwollav2.resource.Links

data class BusinessClassification(
    @JvmField val _links: Links,
    @JvmField val _embedded: EmbeddedIndustryClassifications,
    @JvmField val id: String,
    @JvmField val name: String
)
