package com.dwolla.api

import com.dwolla.DwollaClient
import com.dwolla.api.transfers.AchDetails
import com.dwolla.api.transfers.Clearing
import com.dwolla.api.transfers.Fee
import com.dwolla.exception.DwollaApiException
import com.dwolla.exception.DwollaAuthException
import com.dwolla.http.Headers
import com.dwolla.http.JsonBody
import com.dwolla.resource.fundingsources.FundingSource
import com.dwolla.resource.transfers.Transfer
import com.dwolla.shared.Amount
import com.dwolla.util.Keys.IDEMPOTENCY_KEY
import com.dwolla.util.Keys.SELF
import com.dwolla.util.Paths.Companion.FUNDING_SOURCES
import com.dwolla.util.Paths.Companion.TRANSFERS

internal class TransfersApi(@JvmField val client: DwollaClient) {

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun create(
        sourceFundingSource: FundingSource,
        destinationFundingSource: FundingSource,
        amount: Amount,
        metadata: Map<String, Any>? = null, // TODO: maybe different type?,
        fees: Array<Fee>? = null,
        clearing: Clearing? = null,
        achDetails: AchDetails? = null,
        correlationId: String? = null,
        idempotencyKey: String
    ): Transfer {
        return create(
            sourceFundingSourceId = sourceFundingSource.getHref(SELF),
            destinationFundingSourceId = destinationFundingSource.getHref(SELF),
            amount = amount,
            metadata = metadata,
            fees = fees,
            clearing = clearing,
            achDetails = achDetails,
            correlationId = correlationId,
            idempotencyKey = idempotencyKey
        )
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun create(
        sourceFundingSource: FundingSource,
        destinationFundingSourceId: String,
        amount: Amount,
        metadata: Map<String, Any>? = null, // TODO: maybe different type?,
        fees: Array<Fee>? = null,
        clearing: Clearing? = null,
        achDetails: AchDetails? = null,
        correlationId: String? = null,
        idempotencyKey: String
    ): Transfer {
        return create(
            sourceFundingSourceId = sourceFundingSource.getHref(SELF),
            destinationFundingSourceId = destinationFundingSourceId,
            amount = amount,
            metadata = metadata,
            fees = fees,
            clearing = clearing,
            achDetails = achDetails,
            correlationId = correlationId,
            idempotencyKey = idempotencyKey
        )
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun create(
        sourceFundingSourceId: String,
        destinationFundingSource: FundingSource,
        amount: Amount,
        metadata: Map<String, Any>? = null, // TODO: maybe different type?,
        fees: Array<Fee>? = null,
        clearing: Clearing? = null,
        achDetails: AchDetails? = null,
        correlationId: String? = null,
        idempotencyKey: String
    ): Transfer {
        return create(
            sourceFundingSourceId = sourceFundingSourceId,
            destinationFundingSourceId = destinationFundingSource.getHref(SELF),
            amount = amount,
            metadata = metadata,
            fees = fees,
            clearing = clearing,
            achDetails = achDetails,
            correlationId = correlationId,
            idempotencyKey = idempotencyKey
        )
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun create(
        sourceFundingSourceId: String,
        destinationFundingSourceId: String,
        amount: Amount,
        metadata: Map<String, Any>? = null, // TODO: maybe different type?,
        fees: Array<Fee>? = null,
        clearing: Clearing? = null,
        achDetails: AchDetails? = null,
        correlationId: String? = null,
        idempotencyKey: String
    ): Transfer {
        return client.postFollow(
            Transfer::class.java,
            TRANSFERS,
            JsonBody(
                "_links" to mapOf(
                    "source" to mapOf("href" to fundingSourceUrl(sourceFundingSourceId)),
                    "destination" to mapOf("href" to fundingSourceUrl(destinationFundingSourceId))
                ),
                "amount" to amount,
                "metadata" to metadata,
                "fees" to fees,
                "clearing" to clearing,
                "achDetails" to achDetails,
                "correlationId" to correlationId
            ),
            Headers(IDEMPOTENCY_KEY to idempotencyKey)
        ).body
    }

    private fun fundingSourceUrl(fundingSourceId: String): String {
        return client.urlBuilder.buildUrl(FUNDING_SOURCES, fundingSourceId)
    }
}
