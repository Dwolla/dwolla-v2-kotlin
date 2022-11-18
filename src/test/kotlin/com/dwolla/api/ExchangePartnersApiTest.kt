package com.dwolla.api

import com.dwolla.Dwolla
import com.dwolla.http.JsonBody
import com.dwolla.util.TokenManager
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test
import kotlin.test.BeforeTest
import com.github.kittinunf.fuel.core.Client as FuelClient

@Tags(Tag("api"), Tag("exchange-partners"))
class ExchangePartnersApiTest : ApiTest() {
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
    fun testList() {
        mockRequest(
            dwolla = dwolla,
            statusCode = 200,
            responseBody = JsonBody(
                "_links" to JsonBody(
                    "self" to JsonBody(
                        "href" to "https://api.dwolla.com/exchange-partners",
                        "type" to "application/vnd.dwolla.v1.hal+json",
                        "resource-type" to "exchange-partner"
                    )
                ),
                "_embedded" to JsonBody(
                    "exchange-partners" to listOf(
                        JsonBody(
                            "_links" to JsonBody(
                                "self" to JsonBody(
                                    "href" to "https://api.dwolla.com/exchange-partners/292317ec-e252-47d8-93c3-2d128e037aa4",
                                    "type" to "application/vnd.dwolla.v1.hal+json",
                                    "resource-type" to "exchange-partner"
                                ),
                                "funding-sources" to JsonBody(
                                    "href" to "https://api-sandbox.dwolla.com/funding-sources",
                                    "type" to "application/vnd.dwolla.v1.hal+json",
                                    "resource-type" to "funding-source"
                                )
                            ),
                            "id" to "292317ec-e252-47d8-93c3-2d128e037aa4",
                            "name" to "Finicity",
                            "status" to "active",
                            "created" to "2022-05-17T16:08:52.146Z"
                        )
                    )
                ),
                "total" to 1
            )
        )

        assertParsedResponse { dwolla.exchangePartners.list() }
    }

    @Test
    fun testGet() {
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
            dwolla.exchangePartners.get("https://api.dwolla.com/exchange-partners/e5e9f2d3-a96c-4abd-a097-8ec7ae28aa8a")
        }
    }
}
