package dwollav2

import com.github.kittinunf.fuel.core.Headers
import dwollav2.util.TokenManager
import io.mockk.every
import io.mockk.mockk
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ClientTest {
    private val client = Client("key", "secret")
    private val mockFuelClient = mockk<com.github.kittinunf.fuel.core.Client>()
    private val mockTokenManager = mockk<TokenManager>()
    private val token = "access-token"

    @BeforeTest
    fun setup() {
        client.fuelManager.client = mockFuelClient
        client.tokenManager = mockTokenManager

        every { mockTokenManager.getAccessToken() } returns token
    }

    @Test fun `Client#get(path) returns response body as String`() {
        val responseBody = "{\"foo\":\"bar\"}"
        mockRequest(200, responseBody)

        val response = client.get("/")

        assertEquals(responseBody, response.body)
    }

    private fun mockRequest(statusCode: Int, responseBody: String) {
        every { mockFuelClient.executeRequest(any()).statusCode } returns statusCode
        every { mockFuelClient.executeRequest(any()).responseMessage } returns "OK"
        every { mockFuelClient.executeRequest(any()).data } returns responseBody.toByteArray()
        every { mockFuelClient.executeRequest(any()).headers } returns Headers()
    }
}
