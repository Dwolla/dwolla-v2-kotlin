package com.dwolla.util

import com.dwolla.Environment

internal class UrlBuilder(private val environment: Environment) {

    fun buildUrl(vararg parts: String): String =
        parts.fold(environment.apiBaseUrl()) { url, p ->
            when {
                startsWithApiUrl(p) -> trimSlashes(p)
                p.contains(":") -> throw IllegalArgumentException("Invalid host ($p). Must start with ${environment.apiBaseUrl()}")
                else -> "$url/${trimSlashes(p)}"
            }
        }

    private fun startsWithApiUrl(p: String) =
            p == environment.apiBaseUrl() || p.startsWith("${environment.apiBaseUrl()}/")

    private fun trimSlashes(s: String): String =
        s.removePrefix("/").removeSuffix("/")
}
