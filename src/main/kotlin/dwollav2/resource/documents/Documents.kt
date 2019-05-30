package dwollav2.resource.documents

import dwollav2.resource.Links

data class Documents(
    @JvmField val _links: Links,
    @JvmField val _embedded: EmbeddedDocuments,
    @JvmField val total: Int
)
