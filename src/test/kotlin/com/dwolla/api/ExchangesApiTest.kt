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
    fun testCreateExchangeForCustomer() {
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
            dwolla.exchanges.createForCustomer(
                customerId = "https://api.dwolla.com/customers/74a207b2-b7b7-4efa-8bf8-582148e7b980/exchanges",
                exchangePartnerId = "https://api.dwolla.com/exchange-partners/292317ec-e252-47d8-93c3-2d128e037aa4",
                token = "someMXProcessorToken"
            )
        }
    }

    @Test
    fun testGetExchange() {
        mockRequest(
            dwolla = dwolla,
            statusCode = 200,
            responseBody = JsonBody(
                "_links" to JsonBody(
                    "self" to JsonBody(
                        "href" to "https://api-sandbox.dwolla.com/exchanges/e5e9f2d3-a96c-4abd-a097-8ec7ae28aa8a",
                        "type" to "application/vnd.dwolla.v1.hal+json",
                        "resource-type" to "exchange"
                    )
                ),
                "id" to "e5e9f2d3-a96c-4abd-a097-8ec7ae28aa8a",
                "status" to "active",
                "created" to "2022-10-21T21:41:03.283Z"
            )
        )

        assertParsedResponse {
            dwolla.exchanges.get("https://api.dwolla.com/exchanges/fcd15e5f-8d13-4570-a9b7-7fb49e55941d")
        }
    }

    @Test
    fun testListExchangesByAccount() {
        mockRequest(
            dwolla = dwolla,
            statusCode = 200,
            responseBody = JsonBody(
                "_links" to JsonBody(
                    "self" to JsonBody(
                        "href" to "https://api.dwolla.com/exchanges",
                        "type" to "application/vnd.dwolla.v1.hal+json",
                        "resource-type" to "exchange"
                    )
                ),
                "_embedded" to JsonBody(
                    "exchanges" to listOf(
                        JsonBody(
                            "_links" to JsonBody(
                                "self" to JsonBody(
                                    "href" to "https://api-sandbox.dwolla.com/exchanges/8df845fc-80e0-404a-8912-b02603627e29",
                                    "type" to "application/vnd.dwolla.v1.hal+json",
                                    "resource-type" to "exchange"
                                )
                            ),
                            "id" to "8df845fc-80e0-404a-8912-b02603627e29",
                            "status" to "active",
                            "created" to "2022-05-25T16:07:34.906Z"
                        ),
                        JsonBody(
                            "_links" to JsonBody(
                                "self" to JsonBody(
                                    "href" to "https://api-sandbox.dwolla.com/exchanges/80764434-5391-4c6e-89fa-2f96662e9a3a",
                                    "type" to "application/vnd.dwolla.v1.hal+json",
                                    "resource-type" to "exchange"
                                )
                            ),
                            "id" to "80764434-5391-4c6e-89fa-2f96662e9a3a",
                            "status" to "active",
                            "created" to "2022-05-25T15:57:18.209Z"
                        )
                    )
                ),
                "total" to 2
            )
        )

        assertParsedResponse { dwolla.exchanges.listByAccount() }
    }

    @Test
    fun testListExchangesByCustomer() {
        mockRequest(
            dwolla = dwolla,
            statusCode = 200,
            responseBody = JsonBody(
                "_links" to JsonBody(
                    "self" to JsonBody(
                        "href" to "https://api.dwolla.com/exchanges",
                        "type" to "application/vnd.dwolla.v1.hal+json",
                        "resource-type" to "exchange"
                    )
                ),
                "_embedded" to JsonBody(
                    "exchanges" to listOf(
                        JsonBody(
                            "_links" to JsonBody(
                                "self" to JsonBody(
                                    "href" to "https://api-sandbox.dwolla.com/exchanges/92822961-3a7f-42c0-b0cc-7ffef05717fa",
                                    "type" to "application/vnd.dwolla.v1.hal+json",
                                    "resource-type" to "exchange"
                                ),
                                "exchange-partner" to JsonBody(
                                    "href" to "https://api-sandbox.dwolla.com/exchange-partners/bca8d065-49a5-475b-a6b4-509bc8504d22",
                                    "type" to "application/vnd.dwolla.v1.hal+json",
                                    "resource-type" to "exchange-partner"
                                ),
                                "funding-sources" to JsonBody(
                                    "href" to "https://api-sandbox.dwolla.com/funding-sources",
                                    "type" to "application/vnd.dwolla.v1.hal+json",
                                    "resource-type" to "funding-source"
                                )
                            ),
                            "id" to "92822961-3a7f-42c0-b0cc-7ffef05717fa",
                            "status" to "active",
                            "created" to "2022-10-19T17:44:44.864Z"
                        )
                    )
                ),
                "total" to 1
            )
        )

        assertParsedResponse {
            dwolla.exchanges.listByCustomer(
                customerId = "https://api.dwolla.com/customers/9fc74373-a5c7-40e4-aa59-d5f4c86a24ea/exchanges"
            )
        }
    }
}
