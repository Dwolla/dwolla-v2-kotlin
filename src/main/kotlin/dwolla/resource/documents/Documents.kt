package dwolla.resource.documents

import dwolla.resource.HalResource
import dwolla.resource.Links

data class Documents(
    @JvmField val _links: Links,
    @JvmField val _embedded: EmbeddedDocuments,
    @JvmField val total: Int
) : HalResource(_links)
