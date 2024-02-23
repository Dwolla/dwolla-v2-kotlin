package com.dwolla.api

import com.dwolla.DwollaClient
import com.dwolla.api.transfers.AchDetails
import com.dwolla.api.transfers.Clearing
import com.dwolla.api.transfers.Fee
import com.dwolla.exception.DwollaApiException
import com.dwolla.exception.DwollaAuthException
import com.dwolla.http.Headers
import com.dwolla.http.JsonBody
import com.dwolla.http.Query
import com.dwolla.resource.fundingsources.FundingSource
import com.dwolla.resource.transfers.* // ktlint-disable no-wildcard-imports
import com.dwolla.shared.Amount
import com.dwolla.util.Keys.IDEMPOTENCY_KEY
import com.dwolla.util.Keys.SELF
import com.dwolla.util.Paths
import com.dwolla.util.Paths.Companion.ACCOUNTS
import com.dwolla.util.Paths.Companion.CUSTOMERS
import com.dwolla.util.Paths.Companion.FEES
import com.dwolla.util.Paths.Companion.FUNDING_SOURCES
import com.dwolla.util.Paths.Companion.TRANSFERS

class TransfersApi(@JvmField val client: DwollaClient) {

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun create(
        sourceFundingSource: FundingSource,
        destinationFundingSource: FundingSource,
        amount: Amount,
        metadata: Map<String, Any>? = null,
        fees: Array<Fee>? = null,
        clearing: Clearing? = null,
        achDetails: AchDetails? = null,
        rtpDetails: RtpDetails? = null,
        correlationId: String? = null,
        processingChannel: ProcessingChannel? = null,
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
            rtpDetails = rtpDetails,
            correlationId = correlationId,
            processingChannel = processingChannel,
            idempotencyKey = idempotencyKey
        )
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun create(
        sourceFundingSource: FundingSource,
        destinationFundingSourceId: String,
        amount: Amount,
        metadata: Map<String, Any>? = null,
        fees: Array<Fee>? = null,
        clearing: Clearing? = null,
        achDetails: AchDetails? = null,
        rtpDetails: RtpDetails? = null,
        correlationId: String? = null,
        processingChannel: ProcessingChannel? = null,
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
            rtpDetails = rtpDetails,
            correlationId = correlationId,
            processingChannel = processingChannel,
            idempotencyKey = idempotencyKey
        )
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun create(
        sourceFundingSourceId: String,
        destinationFundingSource: FundingSource,
        amount: Amount,
        metadata: Map<String, Any>? = null,
        fees: Array<Fee>? = null,
        clearing: Clearing? = null,
        achDetails: AchDetails? = null,
        rtpDetails: RtpDetails? = null,
        correlationId: String? = null,
        processingChannel: ProcessingChannel? = null,
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
            rtpDetails = rtpDetails,
            correlationId = correlationId,
            processingChannel = processingChannel,
            idempotencyKey = idempotencyKey
        )
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun create(
        sourceFundingSourceId: String,
        destinationFundingSourceId: String,
        amount: Amount,
        metadata: Map<String, Any>? = null,
        fees: Array<Fee>? = null,
        clearing: Clearing? = null,
        achDetails: AchDetails? = null,
        rtpDetails: RtpDetails? = null,
        correlationId: String? = null,
        processingChannel: ProcessingChannel? = null,
        idempotencyKey: String
    ): Transfer {
        return client.postFollow(
            Transfer::class.java,
            TRANSFERS,
            JsonBody(
                "_links" to JsonBody(
                    "source" to JsonBody("href" to fundingSourceUrl(sourceFundingSourceId)),
                    "destination" to JsonBody("href" to fundingSourceUrl(destinationFundingSourceId))
                ),
                "amount" to amount,
                "metadata" to metadata,
                "fees" to fees,
                "clearing" to clearing,
                "achDetails" to achDetails,
                "rtpDetails" to rtpDetails,
                "correlationId" to correlationId,
                "processingChannel" to processingChannel
            ),
            Headers(IDEMPOTENCY_KEY to idempotencyKey)
        ).body
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun get(id: String): Transfer {
        return client.get(Transfer::class.java, transferUrl(id)).body
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun getFailureReason(id: String): FailureReason {
        return client.get(FailureReason::class.java, transferFailureUrl(id)).body
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun listFees(id: String): FacilitatorFees {
        return client.get(FacilitatorFees::class.java, facilitatorFeesUrl(id)).body
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun listForAccount(
        accountId: String,
        search: String? = null,
        startAmount: String? = null,
        endAmount: String? = null,
        startDate: String? = null,
        endDate: String? = null,
        status: String? = null,
        correlationId: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): Transfers {
        val queryParams = mutableListOf<Pair<String, Any?>>()

        search?.let { queryParams.add("search" to it) }
        startAmount?.let { queryParams.add("startAmount" to it) }
        endAmount?.let { queryParams.add("endAmount" to it) }
        startDate?.let { queryParams.add("startDate" to it) }
        endDate?.let { queryParams.add("endDate" to it) }
        status?.let { queryParams.add("status" to it) }
        correlationId?.let { queryParams.add("correlationId" to it) }
        limit?.let { queryParams.add("limit" to it) }
        offset?.let { queryParams.add("offset" to it) }

        val queryString = Query(*queryParams.toTypedArray())

        return client.get(
            Transfers::class.java,
            accountTransfersUrl(accountId),
            queryString
        ).body
    }


    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun listForCustomer(
        customerId: String,
        search: String? = null,
        startAmount: String? = null,
        endAmount: String? = null,
        startDate: String? = null,
        endDate: String? = null,
        status: String? = null,
        correlationId: String? = null,
        limit: Int? = null,
        offset: Int? = null
    ): Transfers {
        val queryParams = mutableListOf<Pair<String, Any?>>()

        search?.let { queryParams.add("search" to it) }
        startAmount?.let { queryParams.add("startAmount" to it) }
        endAmount?.let { queryParams.add("endAmount" to it) }
        startDate?.let { queryParams.add("startDate" to it) }
        endDate?.let { queryParams.add("endDate" to it) }
        status?.let { queryParams.add("status" to it) }
        correlationId?.let { queryParams.add("correlationId" to it) }
        limit?.let { queryParams.add("limit" to it) }
        offset?.let { queryParams.add("offset" to it) }

        val queryString = Query(*queryParams.toTypedArray())

        return client.get(
            Transfers::class.java,
            customerTransfersUrl(customerId),
            queryString
        ).body
    }


    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun cancel(id: String): Transfer {
        return client.post(
            Transfer::class.java,
            transferUrl(id),
            JsonBody("status" to "cancelled")
        ).body
    }

    private fun accountTransfersUrl(accountId: String): String {
        return client.urlBuilder.buildUrl(ACCOUNTS, accountId, TRANSFERS)
    }

    private fun customerTransfersUrl(customerId: String): String {
        return client.urlBuilder.buildUrl(CUSTOMERS, customerId, TRANSFERS)
    }
    private fun facilitatorFeesUrl(id: String): String {
        return client.urlBuilder.buildUrl(TRANSFERS, id, FEES)
    }
    private fun fundingSourceUrl(fundingSourceId: String): String {
        return client.urlBuilder.buildUrl(FUNDING_SOURCES, fundingSourceId)
    }

    private fun transferUrl(id: String): String {
        return client.urlBuilder.buildUrl(TRANSFERS, id)
    }
    private fun transferFailureUrl(id: String): String {
        return client.urlBuilder.buildUrl(TRANSFERS, id, Paths.FAILURE)
    }

}
