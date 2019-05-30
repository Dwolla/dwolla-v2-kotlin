package dwollav2.resource.documents

import dwollav2.resource.Links
import java.time.Instant

data class Document(
    @JvmField val _links: Links,
    @JvmField val id: String,
    @JvmField val status: DocumentStatus,
    @JvmField val type: DocumentType,
    @JvmField val created: Instant,
    @JvmField val failureReason: DocumentFailureReason
)
