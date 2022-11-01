package com.dwolla.api

import com.dwolla.Dwolla
import com.dwolla.http.JsonBody
import com.dwolla.util.TokenManager
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test
import kotlin.test.BeforeTest
import com.github.kittinunf.fuel.core.Client as FuelClient

@Tags(Tag("api"), Tag("exchanges"))
class ExchangesApiTest : ApiTest() {
    private val dwolla = Dwolla("key", "secret")
    private val mockFuelClient = mockk<FuelClient>()
    private val mockTokenManager = mockk<TokenManager>()

    @BeforeTest
    fun setup() {
        dwolla.fuelManager.client = mockFuelClient
        dwolla.tokenManager = mockTokenManager

        every { mockTokenManager.getAccessToken() } returns "access-token"
    }

    @Test
    fun testCreateExchangeForAccount() {
        mockRequest(
            dwolla = dwolla,
            statusCode = 200,
            responseBody = JsonBody(
                "_links" to JsonBody(
                    "self" to JsonBody(
                        "href" to "https://api.dwolla.com/exchange-partners/e5e9f2d3-a96c-4abd-a097-8ec7ae28aa8a",
                        "type" to "application/vnd.dwolla.v1.hal+json",
                        "resource-type" to "exchange-partner"
                    )
                ),
                "id" to "e5e9f2d3-a96c-4abd-a097-8ec7ae28aa8a",
                "name" to "MX",
                "status" to "active",
                "created" to "2022-08-30T19:31:59.106Z"
            )
        )

        assertParsedResponse {
            dwolla.exchanges.createForAccount(
                exchangePartnerId = "https://api.dwolla.com/exchange-partners/292317ec-e252-47d8-93c3-2d128e037aa4",
                token = "someMXProcessorToken"
            )
        }
    }

    @Test
    @Disabled
    fun testCreateExchangeForCustomer() {

    }

    @Test
    @Disabled
    fun testGetExchange() {

    }

    @Test
    @Disabled
    fun testListExchangesByAccount() {

    }

    @Test
    @Disabled
    fun testListExchangesByCustomer() {

    }
}
