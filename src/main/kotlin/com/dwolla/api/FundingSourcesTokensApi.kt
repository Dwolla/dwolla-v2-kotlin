package com.dwolla.api

import com.dwolla.Dwolla
import com.dwolla.exception.DwollaException
import com.dwolla.exception.OAuthException
import com.dwolla.resource.fundingsourcestokens.FundingSourcesToken
import com.dwolla.util.Paths.Companion.CUSTOMERS
import com.dwolla.util.Paths.Companion.FUNDING_SOURCES_TOKENS

class FundingSourcesTokensApi(private val dwolla: Dwolla) {

    @Throws(DwollaException::class, OAuthException::class)
    fun createForCustomer(customerId: String): FundingSourcesToken {
        return dwolla.post(FundingSourcesToken::class.java, customerFundingSourcesTokensUrl(customerId)).body
    }

    private fun customerFundingSourcesTokensUrl(customerId: String): String {
        return dwolla.urlBuilder.buildUrl(CUSTOMERS, customerId, FUNDING_SOURCES_TOKENS)
    }
}
