package com.dwolla.util

import com.dwolla.Dwolla
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class UrlBuilderTest {
    private val client = Dwolla("id", "secret")
    private val urlBuilder = client.urlBuilder

    @Test fun `builds url with path`() {
        val p1 = "baz"

        val url = urlBuilder.buildUrl(p1)

        assertEquals("${client.environment.apiBaseUrl()}/$p1", url)
    }

    @Test fun `builds url with slashed path`() {
        val p1 = "/baz"

        val url = urlBuilder.buildUrl(p1)

        assertEquals("${client.environment.apiBaseUrl()}$p1", url)
    }

    @Test fun `ignores anything before last absolute uri`() {
        val p1 = client.environment.apiBaseUrl()
        val p2 = "abc"
        val p3 = client.environment.apiBaseUrl()
        val p4 = "123"
        val p5 = "/456"
        val p6 = "789"

        val url = urlBuilder.buildUrl(p1, p2, p3, p4, p5, p6)

        assertEquals("$p3/$p4$p5/$p6", url)
    }

    @Test fun `throws exception if ILLEGAL url`() {
        val p1 = "${client.environment.apiBaseUrl()}.foo.bar"

        assertFailsWith(IllegalArgumentException::class, "should not build url not starting with apiUrl") {
            urlBuilder.buildUrl(p1)
        }
    }
}
