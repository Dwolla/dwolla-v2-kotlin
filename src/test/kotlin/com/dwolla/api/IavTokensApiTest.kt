package com.dwolla.api

import com.dwolla.Instances.dwolla
import kotlin.test.Test

class IavTokensApiTest : ApiTest() {

    private val c = dwolla.customers.createUnverified(
        firstName = string(),
        lastName = string(),
        email = email()
    )

    @Test
    fun testCreateForCustomer() {
        assertResponse { dwolla.iavTokens.createForCustomer(c.id) }
    }
}
