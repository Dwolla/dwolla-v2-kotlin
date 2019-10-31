package com.dwolla

import kotlin.test.Test
import kotlin.test.assertEquals

class DwollaEnvironmentTest {
    @Test fun testProductionEnvironment() {
        assertEquals("https://api.dwolla.com", DwollaEnvironment.PRODUCTION.apiBaseUrl)
        assertEquals("https://api.dwolla.com/token", DwollaEnvironment.PRODUCTION.tokenUrl)
    }

    @Test fun testSandboxEnvironment() {
        assertEquals("https://api-sandbox.dwolla.com", DwollaEnvironment.SANDBOX.apiBaseUrl)
        assertEquals("https://api-sandbox.dwolla.com/token", DwollaEnvironment.SANDBOX.tokenUrl)
    }
}
