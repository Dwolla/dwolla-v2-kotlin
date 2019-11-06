package com.dwolla.api

import com.dwolla.Instances
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import kotlin.test.Test

@Tags(Tag("api"))
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
