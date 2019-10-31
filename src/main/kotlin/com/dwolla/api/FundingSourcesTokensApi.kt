package com.dwolla.api

import com.dwolla.DwollaClient
import com.dwolla.exception.DwollaApiException
import com.dwolla.exception.DwollaAuthException
import com.dwolla.resource.customers.FundingSourcesToken
import com.dwolla.util.Paths

class FundingSourcesTokensApi(private val client: DwollaClient) {

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun createForCustomer(customerId: String): FundingSourcesToken {
        return client.post(FundingSourcesToken::class.java, customerFundingSourcesTokenUrl(customerId)).body
    }

    private fun customerFundingSourcesTokenUrl(customerId: String): String {
        return client.urlBuilder.buildUrl(Paths.CUSTOMERS, customerId, Paths.FUNDING_SOURCES_TOKEN)
    }
}
