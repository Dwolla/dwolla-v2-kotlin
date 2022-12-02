package com.dwolla.api

import com.dwolla.DwollaClient
import com.dwolla.exception.DwollaApiException
import com.dwolla.exception.DwollaAuthException
import com.dwolla.resource.exchanges.ExchangePartner
import com.dwolla.resource.exchanges.ExchangePartners
import com.dwolla.util.Paths.Companion.EXCHANGE_PARTNERS

class ExchangePartnersApi(private val client: DwollaClient) {
    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun get(id: String): ExchangePartner {
        return client.get(ExchangePartner::class.java, exchangePartnerUrl(id)).body
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun list(): ExchangePartners {
        return client.get(ExchangePartners::class.java, EXCHANGE_PARTNERS).body
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    private fun exchangePartnerUrl(id: String): String {
        return client.urlBuilder.buildUrl(EXCHANGE_PARTNERS, id)
    }
}
