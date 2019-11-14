package com.dwolla

import com.dwolla.util.TokenManager
import com.github.kittinunf.fuel.core.Headers
import io.mockk.every
import io.mockk.mockk
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class DwollaTest {
    private val dwolla = Dwolla("key", "secret")
    private val mockFuelClient = mockk<com.github.kittinunf.fuel.core.Client>()
    private val mockTokenManager = mockk<TokenManager>()
    private val accessToken = "access-token"
    private val refreshToken = "refresh-token"
    private val redirectUri = "redirect-uri"
    private val state = "state"
    private val scope = "scope"

    @BeforeTest
    fun setup() {
        dwolla.fuelManager.client = mockFuelClient
        dwolla.tokenManager = mockTokenManager

        every { mockTokenManager.getAccessToken() } returns accessToken
    }

    @Test fun `Client#get(path) returns response body as String`() {
        val responseBody = "{\"foo\":\"bar\"}"
        mockRequest(200, responseBody)

        val response = dwolla.get("/")

        assertEquals(responseBody, response.body)
    }

    @Test fun `Client#auth`() {
        val auth = dwolla.auth(redirectUri = redirectUri, state = state, scope = scope)

        assertEquals(dwolla, auth.dwolla)
        assertEquals(
            "https://accounts.dwolla.com/auth?response_type=code&client_id=${dwolla.key}&redirect_uri=$redirectUri&state=$state&scope=$scope",
            auth.url
        )
    }

    @Test fun `Client#token`() {
        val token = dwolla.token(accessToken = accessToken)

        assertEquals(dwolla.environment, token.environment)
        assertEquals(accessToken, token.accessToken)
    }

    // TODO: no answer found for: Response(child of #1#3).getBody$fuel()
    // @Test fun `Client#refreshToken`() {
    //     mockRequest(200, dwolla.gson.toJson(TokenResponse(accessToken, 3600)))
    //
    //     val token = dwolla.refreshToken(refreshToken = refreshToken)
    //
    //     assertEquals(dwolla.environment, token.environment)
    //     assertEquals(accessToken, token.accessToken)
    // }

    private fun mockRequest(statusCode: Int, responseBody: String) {
        every { mockFuelClient.executeRequest(any()).statusCode } returns statusCode
        every { mockFuelClient.executeRequest(any()).responseMessage } returns "OK"
        every { mockFuelClient.executeRequest(any()).data } returns responseBody.toByteArray()
        every { mockFuelClient.executeRequest(any()).headers } returns Headers()
    }
}
