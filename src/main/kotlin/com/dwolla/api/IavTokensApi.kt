package com.dwolla.api

import com.dwolla.Dwolla
import com.dwolla.exception.DwollaException
import com.dwolla.exception.OAuthException
import com.dwolla.resource.iavtokens.IavToken
import com.dwolla.util.Paths.Companion.CUSTOMERS
import com.dwolla.util.Paths.Companion.IAV_TOKENS

class IavTokensApi(private val dwolla: Dwolla) {

    @Throws(DwollaException::class, OAuthException::class)
    fun createForCustomer(customerId: String): IavToken {
        return dwolla.post(IavToken::class.java, customerIavTokensUrl(customerId)).body
    }

    private fun customerIavTokensUrl(customerId: String): String {
        return dwolla.urlBuilder.buildUrl(CUSTOMERS, customerId, IAV_TOKENS)
    }
}
