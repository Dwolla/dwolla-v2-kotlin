package com.dwolla.api

import com.dwolla.DwollaClient
import com.dwolla.exception.DwollaApiException
import com.dwolla.exception.DwollaAuthException
import com.dwolla.resource.customers.IavToken
import com.dwolla.util.Paths

class IavTokensApi(private val client: DwollaClient) {

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun createForCustomer(customerId: String): IavToken {
        return client.post(IavToken::class.java, customerIavTokenUrl(customerId)).body
    }

    private fun customerIavTokenUrl(customerId: String): String {
        return client.urlBuilder.buildUrl(Paths.CUSTOMERS, customerId, Paths.IAV_TOKEN)
    }
}
