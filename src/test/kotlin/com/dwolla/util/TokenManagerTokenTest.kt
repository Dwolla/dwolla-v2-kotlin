package com.dwolla.util

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TokenManagerTokenTest {
    @Test
    fun `Token#isExpired returns false`() {
        val token = TokenManagerToken("access-token", 3600)

        assertFalse { token.isExpired() }
    }

    @Test
    fun `Token#isExpired returns true`() {
        val token = TokenManagerToken("access-token", -3600)

        assertTrue { token.isExpired() }
    }

    @Test
    fun `Token#isExpired returns true if within leeway period (60 seconds)`() {
        val token = TokenManagerToken("access-token", 30)

        assertTrue { token.isExpired() }
    }
}
