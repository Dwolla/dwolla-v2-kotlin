package com.dwolla.api

import com.dwolla.Instances.dwolla
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import kotlin.test.Test

@Tags(Tag("api"))
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
