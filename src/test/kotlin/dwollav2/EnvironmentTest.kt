package dwollav2

import kotlin.test.Test
import kotlin.test.assertEquals

class EnvironmentTest {
    @Test fun testProductionEnvironment() {
        assertEquals("https://api.dwolla.com", Environment.PRODUCTION.apiUrl)
        assertEquals("https://api.dwolla.com/token", Environment.PRODUCTION.tokenUrl)
    }

    @Test fun testSandboxEnvironment() {
        assertEquals("https://api-sandbox.dwolla.com", Environment.SANDBOX.apiUrl)
        assertEquals("https://api-sandbox.dwolla.com/token", Environment.SANDBOX.tokenUrl)
    }
}
