package com.dwolla.util

import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TokenTest {
    @Test
    fun `Token#isExpired returns false`() {
        val token = Token("access-token", 3600)

        assertFalse { token.isExpired() }
    }

    @Test
    fun `Token#isExpired returns true`() {
        val token = Token("access-token", -3600)

        assertTrue { token.isExpired() }
    }

    @Test
    fun `Token#isExpired returns true if within leeway period (60 seconds)`() {
        val token = Token("access-token", 30)

        assertTrue { token.isExpired() }
    }
}
