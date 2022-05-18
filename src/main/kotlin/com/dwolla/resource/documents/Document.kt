package com.dwolla.resource.documents

import com.dwolla.resource.HalResource
import com.dwolla.resource.Links
import java.time.Instant

data class Document(
    @JvmField val _links: Links,
    @JvmField val id: String,
    @JvmField val status: DocumentStatus,
    @JvmField val documentVerificationStatus: DocumentVerificationStatus,
    @JvmField val type: DocumentType,
    @JvmField val created: Instant,
    @JvmField val failureReason: DocumentFailureReason,
    @JvmField val allFailureReasons: Array<AllFailureReasons>
) : HalResource() {
    override fun getLinks(): Links {
        return _links
    }
}
