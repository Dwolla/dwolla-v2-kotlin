package com.dwolla.resource.transfers

import com.dwolla.resource.HalResource
import com.dwolla.resource.Links
import com.dwolla.shared.Amount
import java.time.Instant

data class Transfer(
    @JvmField val _links: Links,
    @JvmField val id: String,
    @JvmField val status: String,
    @JvmField val amount: TransferAmount,
    @JvmField val created: Instant,
    @JvmField val clearing: ClearingDetails?,
    @JvmField val metadata: Map<String, Any>?,
    @JvmField val achDetails: AchDetails?,
    @JvmField val rtpDetails: RtpDetails?,
    @JvmField val correlationId: String?,
    @JvmField val individualACHId: String?,
    @JvmField val processingChannel: ProcessingChannel?
) : HalResource() {
    override fun getLinks(): Links {
        return _links
    }
}
