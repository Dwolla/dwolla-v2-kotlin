package com.dwolla.api

import com.dwolla.Instances
import kotlin.test.Test

class FundingSourcesTokensApiTest : ApiTest() {

    private val c = Instances.dwolla.customers.createUnverified(
        firstName = string(),
        lastName = string(),
        email = email()
    )

    @Test
    fun testCreateForCustomer() {
        assertResponse { Instances.dwolla.fundingSourcesTokens.createForCustomer(c.id) }
    }
}
