package com.dwolla.api

import com.dwolla.DwollaClient
import com.dwolla.resource.exchanges.ExchangePartner
import com.dwolla.resource.exchanges.ExchangePartners
import com.dwolla.util.Paths.Companion.EXCHANGE_PARTNERS

class ExchangePartnerApi(private val client: DwollaClient) {
    fun get(id: String): ExchangePartner {
        return client.get(
            ExchangePartner::class.java,
            exchangePartnerUrl(id)
        ).body
    }

    fun list(): ExchangePartners {
        return client.get(
            ExchangePartners::class.java,
            EXCHANGE_PARTNERS
        ).body
    }

    private fun exchangePartnerUrl(id: String): String {
        return client.urlBuilder.buildUrl(EXCHANGE_PARTNERS, id)
    }
}