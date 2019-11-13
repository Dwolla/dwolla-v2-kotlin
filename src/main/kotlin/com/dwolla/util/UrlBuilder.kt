package com.dwolla.util

import com.dwolla.Client

internal class UrlBuilder(private val client: Client) {

    fun buildUrl(vararg parts: String): String =
        parts.fold(client.environment.apiUrl) { url, p ->
            when {
                startsWithApiUrl(p) -> trimSlashes(p)
                p.contains(":") -> throw IllegalArgumentException("bad")
                else -> "$url/${trimSlashes(p)}"
            }
        }

    private fun startsWithApiUrl(p: String) =
            p == client.environment.apiUrl || p.startsWith("${client.environment.apiUrl}/")

    private fun trimSlashes(s: String): String =
        s.removePrefix("/").removeSuffix("/")
}
