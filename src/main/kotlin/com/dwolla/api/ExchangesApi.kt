package com.dwolla.api

import com.dwolla.DwollaClient
import com.dwolla.exception.DwollaApiException
import com.dwolla.exception.DwollaAuthException
import com.dwolla.http.Headers
import com.dwolla.http.JsonBody
import com.dwolla.resource.exchanges.Exchange
import com.dwolla.resource.exchanges.Exchanges
import com.dwolla.util.Headers.Companion.IDEMPOTENCY_KEY
import com.dwolla.util.Paths.Companion.CUSTOMERS
import com.dwolla.util.Paths.Companion.EXCHANGES
import com.dwolla.util.Paths.Companion.EXCHANGE_PARTNERS

class ExchangesApi(private val client: DwollaClient) {
    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun createForAccount(
        exchangePartnerId: String,
        finicity: Any? = null,
        token: String? = null,
        idempotencyKey: String? = null
    ): Exchange {
        return client.postFollow(
            Exchange::class.java,
            EXCHANGES,
            JsonBody(
                "_links" to createExchangePartnerLinks(exchangePartnerId),
                createFinicityOrToken(finicity, token)
            ),
            Headers(IDEMPOTENCY_KEY to idempotencyKey)
        ).body
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun createForCustomer(
        customerId: String,
        exchangePartnerId: String,
        finicity: Any? = null,
        token: String? = null,
        idempotencyKey: String? = null
    ): Exchange {
        return client.postFollow(
            Exchange::class.java,
            customerExchangesUrl(customerId),
            JsonBody(
                "_links" to createExchangePartnerLinks(exchangePartnerId),
                createFinicityOrToken(finicity, token)
            ),
            Headers(IDEMPOTENCY_KEY to idempotencyKey)
        ).body
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun get(id: String): Exchange {
        return client.get(Exchange::class.java, exchangesUrl(id)).body
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun listByAccount(): Exchanges {
        return client.get(Exchanges::class.java, EXCHANGES).body
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun listByCustomer(customerId: String): Exchanges {
        return client.get(Exchanges::class.java, customerExchangesUrl(customerId)).body
    }

    private fun createExchangePartnerLinks(exchangePartnerId: String): Map<String, Map<String, String>> {
        return mapOf(
            "exchange-partner" to mapOf(
                "href" to exchangePartnerUrl(exchangePartnerId)
            )
        )
    }

    private fun createFinicityOrToken(finicity: Any? = null, token: String? = null): Pair<String, Any?> {
        return if (finicity != null) "finicity" to finicity else "token" to token
    }

    private fun customerExchangesUrl(customerId: String): String {
        return client.urlBuilder.buildUrl(CUSTOMERS, customerId, EXCHANGES)
    }

    private fun exchangesUrl(id: String): String {
        return client.urlBuilder.buildUrl(EXCHANGES, id)
    }

    private fun exchangePartnerUrl(exchangePartnerId: String): String {
        return client.urlBuilder.buildUrl(EXCHANGE_PARTNERS, exchangePartnerId)
    }
}
