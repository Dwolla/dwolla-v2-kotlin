package com.dwolla

import io.mockk.every
import io.mockk.mockk
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class DwollaAuthTest {
    val clientId = "client-id"
    val redirectUri = "https://my.site/dwolla_callback"
    val state = "my state"
    val wrongState = "wrong state"
    val scope = "my scope"
    val accessToken = "my-access-token"
    val code = "my-code"
    val dwolla = mockClient()

    @Test fun authUrlWithoutState() {
        val auth = DwollaAuth(dwolla, redirectUri = redirectUri, scope = scope)

        assertEquals(
            "${dwolla.getEnvironment().authBaseUrl}?response_type=code&client_id=$clientId&redirect_uri=https%3A%2F%2Fmy.site%2Fdwolla_callback&scope=my+scope",
            auth.url
        )
    }

    @Test fun authUrlWithState() {
        val auth = DwollaAuth(dwolla, redirectUri = redirectUri, state = state, scope = scope)

        assertEquals(
            "${dwolla.getEnvironment().authBaseUrl}?response_type=code&client_id=$clientId&redirect_uri=https%3A%2F%2Fmy.site%2Fdwolla_callback&state=my+state&scope=my+scope",
            auth.url
        )
    }

    @Test fun authCallbackSuccess() {
        val auth = DwollaAuth(dwolla, redirectUri = redirectUri, state = state, scope = scope)

        val token = auth.callback(code = code, state = state)

        assertEquals(accessToken, token.accessToken)
    }

    @Test fun authCallbackStateMismatch() {
        val auth = DwollaAuth(dwolla, redirectUri = redirectUri, state = state, scope = scope)

        assertFailsWith(IllegalArgumentException::class) {
            auth.callback(code = code, state = wrongState)
        }
    }

    private fun mockClient(): Dwolla {
        return mockk {
            every { getClientId() } returns clientId
            every { getEnvironment() } returns DwollaEnvironment.SANDBOX
            every {
                fetchToken(
                    "grant_type" to "authorization_code",
                    "redirect_uri" to redirectUri,
                    "code" to code
                )
            } returns TokenResponse(accessToken, 3600)
        }
    }
}
