package com.dwolla.api

import com.dwolla.Dwolla
import com.dwolla.api.transfers.*
import com.dwolla.http.JsonBody
import com.dwolla.resource.transfers.Transfer
import com.dwolla.shared.Amount
import com.dwolla.util.TokenManager
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test
import kotlin.test.BeforeTest
import com.github.kittinunf.fuel.core.Client as FuelClient

@Tags(Tag("api"), Tag("transfers"))
class TransfersApiTest : ApiTest() {
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
    fun testCreate() {
        mockRequest(
            dwolla = dwolla,
            statusCode = 200,
            responseBody = transferResponse
        )

        assertParsedResponse { createTransfer() }
    }

    @Test
    fun testCreateWithOptionalParams() {
        mockRequest(
            dwolla = dwolla,
            statusCode = 200,
            responseBody = transferResponse
        )

        assertParsedResponse { createTransferWithOptionalParams() }
    }

    @Test
    fun testGet() {
        mockRequest(
            dwolla = dwolla,
            statusCode = 200,
            responseBody = transferResponse
        )

        assertParsedResponse {
            dwolla.transfers.get("f03790e2-44d6-ee11-ac47-06f818744a9d")
        }
    }

    @Test
    fun testListFees() {
        mockRequest(
            dwolla = dwolla,
            statusCode = 200,
            responseBody = JsonBody(
                "_links" to JsonBody(
                    "self" to JsonBody(
                        "href" to "https://api.dwolla.com/transfers/9b5e99fd-2e8d-ed11-814b-f79e44742551/fees",
                        "type" to "application/vnd.dwolla.v1.hal+json",
                        "resource-type" to "fee"
                    )
                ),
                "_embedded" to JsonBody(
                    "fees" to listOf(
                        JsonBody(
                            "_links" to JsonBody(
                                "self" to JsonBody(
                                    "href" to "https://api.dwolla.com/transfers/9d84dc93-ffc4-4e32-96ad-bfd10155594f",
                                    "type" to "application/vnd.dwolla.v1.hal+json",
                                    "resource-type" to "transfer"
                                ),
                                "source" to JsonBody(
                                    "href" to "https://api.dwolla.com/accounts/0ee84069-47c5-455c-b425-633523291dc3",
                                    "type" to "application/vnd.dwolla.v1.hal+json",
                                    "resource-type" to "account"
                                ),
                                "destination" to JsonBody(
                                    "href" to "https://api.dwolla.com/accounts/0ee84069-47c5-455c-b425-633523291dc3",
                                    "type" to "application/vnd.dwolla.v1.hal+json",
                                    "resource-type" to "account"
                                ),
                                "created-from-transfer" to JsonBody(
                                    "href" to "https://api.dwolla.com/transfers/9b5e99fd-2e8d-ed11-814b-f79e44742551",
                                    "type" to "application/vnd.dwolla.v1.hal+json",
                                    "resource-type" to "transfer"
                                )
                            ),
                            "id" to "9d84dc93-ffc4-4e32-96ad-bfd10155594f",
                            "status" to "processed",
                            "amount" to JsonBody(
                                "value" to "2.00",
                                "currency" to "USD"
                            ),
                            "created" to "2023-01-05T19:27:36.377Z"
                        )
                    )
                ),
                "transactions" to listOf(
                    JsonBody(
                        "_links" to JsonBody(
                            "self" to JsonBody(
                                "href" to "https://api.dwolla.com/transfers/9d84dc93-ffc4-4e32-96ad-bfd10155594f",
                                "type" to "application/vnd.dwolla.v1.hal+json",
                                "resource-type" to "transfer"
                            ),
                            "source" to JsonBody(
                                "href" to "https://api.dwolla.com/accounts/0ee84069-47c5-455c-b425-633523291dc3",
                                "type" to "application/vnd.dwolla.v1.hal+json",
                                "resource-type" to "account"
                            ),
                            "destination" to JsonBody(
                                "href" to "https://api.dwolla.com/accounts/0ee84069-47c5-455c-b425-633523291dc3",
                                "type" to "application/vnd.dwolla.v1.hal+json",
                                "resource-type" to "account"
                            ),
                            "created-from-transfer" to JsonBody(
                                "href" to "https://api.dwolla.com/transfers/9b5e99fd-2e8d-ed11-814b-f79e44742551",
                                "type" to "application/vnd.dwolla.v1.hal+json",
                                "resource-type" to "transfer"
                            )
                        ),
                        "id" to "9d84dc93-ffc4-4e32-96ad-bfd10155594f",
                        "status" to "processed",
                        "amount" to JsonBody(
                            "value" to "2.00",
                            "currency" to "USD"
                        ),
                        "created" to "2023-01-05T19:27:36.377Z"
                    )
                ),
                "total" to 1
            )
        )

        assertParsedResponse {
            dwolla.transfers.listFees("9b5e99fd-2e8d-ed11-814b-f79e44742551")
        }
    }

    @Test
    fun testListForAccount() {
        mockRequest(
            dwolla = dwolla,
            statusCode = 200,
            responseBody = JsonBody(
                "_links" to JsonBody(
                    "self" to JsonBody(
                        "href" to "https://api.dwolla.com/accounts/f03790e2-44d6-ee11-ac47-06f818744a9d/transfers",
                        "type" to "application/vnd.dwolla.v1.hal+json",
                        "resource-type" to "fee"
                    )
                ),
                "_embedded" to JsonBody(
                    "transfers" to listOf(
                        JsonBody(
                            "_links" to JsonBody(
                                "source" to JsonBody(
                                    "href" to "https://api.dwolla.com/accounts/f03790e2-44d6-ee11-ac47-06f818744a9d",
                                    "type" to "application/vnd.dwolla.v1.hal+json",
                                    "resource-type" to "account"
                                ),
                                "destination-funding-source" to JsonBody(
                                    "href" to "https://api.dwolla.com/funding-sources/6cee847c-ee6a-4368-97aa-130fd2a8ea5e",
                                    "type" to "application/vnd.dwolla.v1.hal+json",
                                    "resource-type" to "funding-source"
                                ),
                                "self" to JsonBody(
                                    "href" to "https://api.dwolla.com/transfers/f7742e9d-dd9a-ee11-ac47-06f818744a9d",
                                    "type" to "application/vnd.dwolla.v1.hal+json",
                                    "resource-type" to "transfer"
                                ),
                                "source-funding-source" to JsonBody(
                                    "href" to "https://api.dwolla.com/funding-sources/7dc2e1df-9a88-4d9a-868f-90b46f1defcc",
                                    "type" to "application/vnd.dwolla.v1.hal+json",
                                    "resource-type" to "funding-source"
                                ),
                                "destination" to JsonBody(
                                    "href" to "https://api.dwolla.com/customers/10abd9dd-06e8-427b-ad47-a692cf7f86cd",
                                    "type" to "application/vnd.dwolla.v1.hal+json",
                                    "resource-type" to "customer"
                                )
                            ),
                            "id" to "f7742e9d-dd9a-ee11-ac47-06f818744a9d",
                            "status" to "processed",
                            "amount" to JsonBody(
                                "value" to "10.00",
                                "currency" to "USD"
                            ),
                            "created" to "2023-12-15T00:05:12.030Z",
                            "processingChannel" to JsonBody(
                                "destination" to "real-time-payments"
                            ),
                            "rtpDetails" to JsonBody(
                                "destination" to JsonBody(
                                    "networkId" to "20231214021214273T1B5R2056501811252",
                                    "remittanceData" to "ABC_123 Remittance Data"
                                )
                            )
                        )
                    )
                ),
            "total" to 1
            )
        )

        assertParsedResponse {
            dwolla.transfers.listForAccount("f03790e2-44d6-ee11-ac47-06f818744a9d")
        }
    }

    @Test
    fun testListForCustomer() {
        mockRequest(
            dwolla = dwolla,
            statusCode = 200,
            responseBody = JsonBody(
                "_links" to JsonBody(
                    "self" to JsonBody(
                        "href" to "https://api.dwolla.com/customers/10abd9dd-06e8-427b-ad47-a692cf7f86cd/transfers",
                        "type" to "application/vnd.dwolla.v1.hal+json",
                        "resource-type" to "fee"
                    )
                ),
                "_embedded" to JsonBody(
                    "transfers" to listOf(
                        JsonBody(
                            "_links" to JsonBody(
                                "source" to JsonBody(
                                    "href" to "https://api.dwolla.com/accounts/f03790e2-44d6-ee11-ac47-06f818744a9d",
                                    "type" to "application/vnd.dwolla.v1.hal+json",
                                    "resource-type" to "account"
                                ),
                                "destination-funding-source" to JsonBody(
                                    "href" to "https://api.dwolla.com/funding-sources/6cee847c-ee6a-4368-97aa-130fd2a8ea5e",
                                    "type" to "application/vnd.dwolla.v1.hal+json",
                                    "resource-type" to "funding-source"
                                ),
                                "self" to JsonBody(
                                    "href" to "https://api.dwolla.com/transfers/f7742e9d-dd9a-ee11-ac47-06f818744a9d",
                                    "type" to "application/vnd.dwolla.v1.hal+json",
                                    "resource-type" to "transfer"
                                ),
                                "source-funding-source" to JsonBody(
                                    "href" to "https://api.dwolla.com/funding-sources/7dc2e1df-9a88-4d9a-868f-90b46f1defcc",
                                    "type" to "application/vnd.dwolla.v1.hal+json",
                                    "resource-type" to "funding-source"
                                ),
                                "destination" to JsonBody(
                                    "href" to "https://api.dwolla.com/customers/10abd9dd-06e8-427b-ad47-a692cf7f86cd",
                                    "type" to "application/vnd.dwolla.v1.hal+json",
                                    "resource-type" to "customer"
                                )
                            ),
                            "id" to "f7742e9d-dd9a-ee11-ac47-06f818744a9d",
                            "status" to "processed",
                            "amount" to JsonBody(
                                "value" to "10.00",
                                "currency" to "USD"
                            ),
                            "created" to "2023-12-15T00:05:12.030Z",
                            "processingChannel" to JsonBody(
                                "destination" to "real-time-payments"
                            ),
                            "rtpDetails" to JsonBody(
                                "destination" to JsonBody(
                                    "networkId" to "20231214021214273T1B5R2056501811252",
                                    "remittanceData" to "ABC_123 Remittance Data"
                                )
                            )
                        )
                    )
                ),
                "total" to 1
            )
        )

        assertParsedResponse {
            dwolla.transfers.listForCustomer("10abd9dd-06e8-427b-ad47-a692cf7f86cd")
        }
    }

    @Test
    fun testGetFailureReason() {
        mockRequest(
            dwolla = dwolla,
            statusCode = 200,
            responseBody = JsonBody(
                "_links" to JsonBody(
                    "self" to JsonBody(
                        "href" to "https://api.dwolla.com/transfers/f7742e9d-dd9a-ee11-ac47-06f818744a9d/failure",
                        "type" to "application/vnd.dwolla.v1.hal+json",
                        "resource-type" to "failure"
                    ),

                    "failed-funding-source" to JsonBody(
                        "href" to "https://api.dwolla.com/funding-sources/f9b4a82a-caad-4d0e-af8a-17f83e71ede8",
                        "type" to "application/vnd.dwolla.v1.hal+json",
                        "resource-type" to "funding-source"
                    ),
                    "customer" to JsonBody(
                        "href" to "https://api.dwolla.com/customers/573c712a-73f1-4823-9a5d-0bebcc38a078",
                        "type" to "application/vnd.dwolla.v1.hal+json",
                        "resource-type" to "customer"
                    )
                ),
                "code" to "R01",
                "description" to "Insufficient Funds",
                "explanation" to "Available balance is not sufficient to cover the dollar amount of the debit entry.",
                "created" to "2023-12-15T00:05:12.030Z"
            )
        )

        assertParsedResponse {
            dwolla.transfers.getFailureReason("f7742e9d-dd9a-ee11-ac47-06f818744a9d")
        }
    }

    @Test
    fun testCancel() {
        mockRequest(
            dwolla = dwolla,
            statusCode = 200,
            responseBody = JsonBody(
                "_links" to JsonBody(
                    "self" to JsonBody(
                        "href" to "https://api.dwolla.com/transfers/1a17d2e7-50d7-ee11-ac47-06f818744a9d",
                        "type" to "application/vnd.dwolla.v1.hal+json",
                        "resource-type" to "transfer"
                    )
                ),
                "id" to "1a17d2e7-50d7-ee11-ac47-06f818744a9d",
                "status" to "cancelled",
                "amount" to JsonBody(
                    "value" to "10.00",
                    "currency" to "USD"
                ),
                "created" to "2024-02-29T22:21:46.017Z"
            )
        )

        assertParsedResponse {
            dwolla.transfers.cancel("1a17d2e7-50d7-ee11-ac47-06f818744a9d")
        }
    }

    private fun createTransfer(): Transfer {
        return dwolla.transfers.create(
            sourceFundingSourceId = "65cd94cf-2de6-498b-a08c-1c6cf9223a15",
            destinationFundingSourceId = "eb4e32de-eef2-496c-a6e0-5be8596b2096",
            amount = Amount(value = "10.00")
        )
    }
    private fun createTransferWithOptionalParams(): Transfer {
        return dwolla.transfers.create(
            sourceFundingSourceId = "65cd94cf-2de6-498b-a08c-1c6cf9223a15",
            destinationFundingSourceId = "eb4e32de-eef2-496c-a6e0-5be8596b2096",
            amount = Amount(value = "10.00"),
            fees = arrayOf(Fee(chargeTo = "70088704-f2f1-443e-bd41-f20305d6c912", amount = Amount(value = "1.00"))),
            clearing = Clearing(destination = ClearingType.NEXT_AVAILABLE),
            processingChannel = ProcessingChannel(destination = ProcessingChannelType.REAL_TIME_PAYMENTS),
            rtpDetails = RtpDetails(destination = RtpDetail(remittanceData = "ABC_123 Remittance Data")),
            achDetails = AchDetails(
                source = AchDetail(Addenda(values = arrayOf("ABC123_AddendaValue"))),
                destination = AchDetail(Addenda(values = arrayOf("ABC123_AddendaValue")))
            ),
            correlationId = "ABC123_CorreationId",
            idempotencyKey = "ABC123_IdempotencyKey"
        )
    }

    private val transferResponse = JsonBody(
        "_links" to JsonBody(
            "source" to JsonBody(
                "href" to "https://api.dwolla.com/accounts/0ee84069-47c5-455c-b425-633523291dc3",
                "type" to "application/vnd.dwolla.v1.hal+json",
                "resource-type" to "account"
            ),
            "destination-funding-source" to JsonBody(
                "href" to "https://api.dwolla.com/funding-sources/6cee847c-ee6a-4368-97aa-130fd2a8ea5e",
                "type" to "application/vnd.dwolla.v1.hal+json",
                "resource-type" to "funding-source"
            ),
            "self" to JsonBody(
                "href" to "https://api.dwolla.com/transfers/f03790e2-44d6-ee11-ac47-06f818744a9d",
                "type" to "application/vnd.dwolla.v1.hal+json",
                "resource-type" to "transfer"
            ),
            "funded-transfer" to JsonBody(
                "href" to "https://api.dwolla.com/transfers/f13790e2-44d6-ee11-ac47-06f818744a9d",
                "type" to "application/vnd.dwolla.v1.hal+json",
                "resource-type" to "transfer"
            ),
            "source-funding-source" to JsonBody(
                "href" to "https://api.dwolla.com/funding-sources/7dc2e1df-9a88-4d9a-868f-90b46f1defcc",
                "type" to "application/vnd.dwolla.v1.hal+json",
                "resource-type" to "funding-source"
            ),
            "destination" to JsonBody(
                "href" to "https://api.dwolla.com/customers/10abd9dd-06e8-427b-ad47-a692cf7f86cd",
                "type" to "application/vnd.dwolla.v1.hal+json",
                "resource-type" to "customer"
            ),
            "fees" to JsonBody(
                "href" to "https://api.dwolla.com/transfers/f03790e2-44d6-ee11-ac47-06f818744a9d/fees",
                "type" to "application/vnd.dwolla.v1.hal+json",
                "resource-type" to "fee"
            )
        ),
        "id" to "f03790e2-44d6-ee11-ac47-06f818744a9d",
        "status" to "processed",
        "amount" to JsonBody(
            "value" to "100.00",
            "currency" to "USD"
        ),
        "clearing" to JsonBody(
            "destination" to "next-day"
        ),
        "achDetails" to JsonBody(
            "source" to JsonBody(
                "addenda" to JsonBody(
                    "values" to arrayOf("ABC123_AddendaValue")
                )
            ),
            "destination" to JsonBody(
                "addenda" to JsonBody(
                    "values" to arrayOf("ABC123_AddendaValue")
                )
            )
        ),
        "correlationId" to "ABC123_CorrelationId",
        "created" to "2024-02-28T14:23:05.180Z",
        "individualAchId" to  "I85NNN94"
    )

}
