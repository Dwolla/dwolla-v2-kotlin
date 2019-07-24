package com.dwolla.api

import com.dwolla.Dwolla
import com.dwolla.api.fundingsources.BankAccountType
import com.dwolla.api.fundingsources.FundingSourceChannel
import com.dwolla.exception.DwollaException
import com.dwolla.exception.OAuthException
import com.dwolla.http.Headers
import com.dwolla.http.JsonBody
import com.dwolla.http.Query
import com.dwolla.resource.fundingsources.FundingSource
import com.dwolla.resource.fundingsources.FundingSourceBalance
import com.dwolla.resource.fundingsources.FundingSources
import com.dwolla.resource.fundingsources.MicroDeposits
import com.dwolla.resource.fundingsources.MicroDepositsVerified
import com.dwolla.util.Headers.Companion.IDEMPOTENCY_KEY
import com.dwolla.util.Paths
import com.dwolla.util.Paths.Companion.BALANCE
import com.dwolla.util.Paths.Companion.CUSTOMERS
import com.dwolla.util.Paths.Companion.FUNDING_SOURCES
import com.dwolla.util.Paths.Companion.ON_DEMAND_AUTHORIZATIONS

class FundingSourcesApi(private val dwolla: Dwolla) {

    @Throws(DwollaException::class, OAuthException::class)
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

        return dwolla.postFollow(
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

    @Throws(DwollaException::class, OAuthException::class)
    fun get(id: String): FundingSource {
        return dwolla.get(FundingSource::class.java, fundingSourceUrl(id)).body
    }

    @Throws(DwollaException::class, OAuthException::class)
    fun getBalance(id: String): FundingSourceBalance {
        return dwolla.get(FundingSourceBalance::class.java, fundingSourceBalanceUrl(id)).body
    }

    @Throws(DwollaException::class, OAuthException::class)
    fun listByCustomer(
        customerId: String,
        removed: Boolean? = null
    ): FundingSources {

        return dwolla.get(
            FundingSources::class.java,
            customerFundingSourcesUrl(customerId),
            Query("removed" to removed)
        ).body
    }

    @Throws(DwollaException::class, OAuthException::class)
    fun update(
        id: String,
        name: String? = null,
        routingNumber: String? = null,
        accountNumber: String? = null,
        bankAccountType: BankAccountType? = null
    ): FundingSource {

        return dwolla.post(
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

    @Throws(DwollaException::class, OAuthException::class)
    fun remove(id: String): FundingSource {
        return dwolla.post(
            FundingSource::class.java,
            fundingSourceUrl(id),
            JsonBody("removed" to true)
        ).body
    }

    @Throws(DwollaException::class, OAuthException::class)
    fun initiateMicroDeposits(id: String): MicroDeposits {
        return dwolla.postFollow(
            MicroDeposits::class.java,
            fundingSourceMicroDepositsUrl(id)
        ).body
    }

    @Throws(DwollaException::class, OAuthException::class)
    fun getMicroDeposits(id: String): MicroDeposits {
        return dwolla.get(
            MicroDeposits::class.java,
            fundingSourceMicroDepositsUrl(id)
        ).body
    }

    @Throws(DwollaException::class, OAuthException::class)
    fun verifyMicroDeposits(id: String, amount1: String, amount2: String): MicroDepositsVerified {
        return dwolla.post(
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
        return dwolla.urlBuilder.buildUrl(FUNDING_SOURCES, id)
    }

    private fun customerFundingSourcesUrl(customerId: String): String {
        return dwolla.urlBuilder.buildUrl(CUSTOMERS, customerId, FUNDING_SOURCES)
    }

    private fun onDemandAuthorizationUrl(onDemandAuthorizationId: String): String {
        return dwolla.urlBuilder.buildUrl(ON_DEMAND_AUTHORIZATIONS, onDemandAuthorizationId)
    }

    private fun fundingSourceMicroDepositsUrl(id: String): String {
        return dwolla.urlBuilder.buildUrl(FUNDING_SOURCES, id, Paths.MICRO_DEPOSITS)
    }

    private fun fundingSourceBalanceUrl(id: String): String {
        return dwolla.urlBuilder.buildUrl(FUNDING_SOURCES, id, BALANCE)
    }

    private fun amountMap(amount: String): Map<String, String> {
        return mapOf(
            "value" to amount,
            "currency" to "USD"
        )
    }
}
