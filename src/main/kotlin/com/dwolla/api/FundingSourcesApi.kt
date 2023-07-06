package com.dwolla.api

import com.dwolla.DwollaClient
import com.dwolla.api.fundingsources.BankAccountType
import com.dwolla.api.fundingsources.FundingSourceChannel
import com.dwolla.exception.DwollaApiException
import com.dwolla.exception.DwollaAuthException
import com.dwolla.http.Headers
import com.dwolla.http.JsonBody
import com.dwolla.http.Query
import com.dwolla.resource.fundingsources.* // ktlint-disable no-wildcard-imports
import com.dwolla.util.Headers.Companion.IDEMPOTENCY_KEY
import com.dwolla.util.Paths
import com.dwolla.util.Paths.Companion.ACCOUNTS
import com.dwolla.util.Paths.Companion.BALANCE
import com.dwolla.util.Paths.Companion.CUSTOMERS
import com.dwolla.util.Paths.Companion.FUNDING_SOURCES
import com.dwolla.util.Paths.Companion.ON_DEMAND_AUTHORIZATIONS

class FundingSourcesApi(private val client: DwollaClient) {
    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun createBankForCustomer(
        customerId: String,
        routingNumber: String,
        accountNumber: String,
        bankAccountType: BankAccountType,
        name: String,
        channels: List<FundingSourceChannel>? = null,
        onDemandAuthorizationId: String? = null,
        verified: Boolean? = null,
        idempotencyKey: String? = null
    ): FundingSource {
        return client.postFollow(
            FundingSource::class.java,
            customerFundingSourcesUrl(customerId),
            JsonBody(
                "_links" to maybeCreateFsLinks(onDemandAuthorizationId),
                "routingNumber" to routingNumber,
                "accountNumber" to accountNumber,
                "bankAccountType" to bankAccountType,
                "name" to name,
                "channels" to channels,
                "verified" to verified
            ),
            Headers(IDEMPOTENCY_KEY to idempotencyKey)
        ).body
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun createPlaidBankForCustomer(
        customerId: String,
        name: String,
        plaidToken: String,
        channels: List<FundingSourceChannel>? = null,
        onDemandAuthorizationId: String? = null,
        idempotencyKey: String? = null
    ): FundingSource {
        return client.postFollow(
            FundingSource::class.java,
            customerFundingSourcesUrl(customerId),
            JsonBody(
                "_links" to maybeCreateFsLinks(onDemandAuthorizationId),
                "name" to name,
                "plaidToken" to plaidToken,
                "channels" to channels
            ),
            Headers(IDEMPOTENCY_KEY to idempotencyKey)
        ).body
    }

    @Deprecated(
        message = "Use createBankForCustomer and createPlaidBankForCustomer depending on use case",
        replaceWith = ReplaceWith("createBankForCustomer(customerId, routingNumber, accountNumber, bankAccountType, name, channels, onDemandAuthorizationId, verified, idempotencyKey)")
    )
    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun createForCustomer(
        customerId: String,
        routingNumber: String,
        accountNumber: String,
        bankAccountType: BankAccountType,
        name: String,
        plaidToken: String? = null,
        channels: List<FundingSourceChannel>? = null,
        onDemandAuthorizationId: String? = null,
        verified: Boolean? = null,
        idempotencyKey: String? = null
    ): FundingSource {

        return client.postFollow(
            FundingSource::class.java,
            customerFundingSourcesUrl(customerId),
            JsonBody(
                "_links" to maybeCreateFsLinks(onDemandAuthorizationId),
                "routingNumber" to routingNumber,
                "accountNumber" to accountNumber,
                "bankAccountType" to bankAccountType,
                "name" to name,
                "plaidToken" to plaidToken,
                "channels" to channels,
                "verified" to verified
            ),
            Headers(IDEMPOTENCY_KEY to idempotencyKey)
        ).body
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun createForAccount() {
        throw NotImplementedError()
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun get(id: String): FundingSource {
        return client.get(FundingSource::class.java, fundingSourceUrl(id)).body
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun getBalance(id: String): FundingSourceBalance {
        return client.get(FundingSourceBalance::class.java, fundingSourceBalanceUrl(id)).body
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun listByAccount(
        accountId: String,
        removed: Boolean? = null
    ): FundingSources {

        return client.get(
            FundingSources::class.java,
            accountFundingSourcesUrl(accountId),
            Query("removed" to removed)
        ).body
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun listByCustomer(
        customerId: String,
        removed: Boolean? = null
    ): FundingSources {

        return client.get(
            FundingSources::class.java,
            customerFundingSourcesUrl(customerId),
            Query("removed" to removed)
        ).body
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun update(
        id: String,
        name: String? = null,
        routingNumber: String? = null,
        accountNumber: String? = null,
        bankAccountType: BankAccountType? = null
    ): FundingSource {

        return client.post(
            FundingSource::class.java,
            fundingSourceUrl(id),
            JsonBody(
                "name" to name,
                "routingNumber" to routingNumber,
                "accountNumber" to accountNumber,
                "bankAccountType" to bankAccountType
            )
        ).body
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun remove(id: String): FundingSource {
        return client.post(
            FundingSource::class.java,
            fundingSourceUrl(id),
            JsonBody("removed" to true)
        ).body
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun initiateMicroDeposits(id: String): MicroDeposits {
        return client.postFollow(
            MicroDeposits::class.java,
            fundingSourceMicroDepositsUrl(id)
        ).body
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun getMicroDeposits(id: String): MicroDeposits {
        return client.get(
            MicroDeposits::class.java,
            fundingSourceMicroDepositsUrl(id)
        ).body
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun verifyMicroDeposits(id: String, amount1: String, amount2: String): MicroDepositsVerified {
        return client.post(
            MicroDepositsVerified::class.java,
            fundingSourceMicroDepositsUrl(id),
            JsonBody(
                "amount1" to amountMap(amount1),
                "amount2" to amountMap(amount2)
            )
        ).body
    }

    private fun maybeCreateFsLinks(onDemandAuthorizationId: String?): Map<String, Map<String, String>>? {
        return if (onDemandAuthorizationId != null) {
            mapOf(
                "on-demand-authorization" to mapOf(
                    "href" to onDemandAuthorizationUrl(onDemandAuthorizationId)
                )
            )
        } else null
    }

    private fun fundingSourceUrl(id: String): String {
        return client.urlBuilder.buildUrl(FUNDING_SOURCES, id)
    }

    private fun accountFundingSourcesUrl(accountId: String): String {
        return client.urlBuilder.buildUrl(ACCOUNTS, accountId, FUNDING_SOURCES)
    }

    private fun customerFundingSourcesUrl(customerId: String): String {
        return client.urlBuilder.buildUrl(CUSTOMERS, customerId, FUNDING_SOURCES)
    }

    private fun onDemandAuthorizationUrl(onDemandAuthorizationId: String): String {
        return client.urlBuilder.buildUrl(ON_DEMAND_AUTHORIZATIONS, onDemandAuthorizationId)
    }

    private fun fundingSourceMicroDepositsUrl(id: String): String {
        return client.urlBuilder.buildUrl(FUNDING_SOURCES, id, Paths.MICRO_DEPOSITS)
    }

    private fun fundingSourceBalanceUrl(id: String): String {
        return client.urlBuilder.buildUrl(FUNDING_SOURCES, id, BALANCE)
    }

    private fun amountMap(amount: String): Map<String, String> {
        return mapOf(
            "value" to amount,
            "currency" to "USD"
        )
    }
}
