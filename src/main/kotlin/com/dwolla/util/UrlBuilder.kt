package com.dwolla.util

import com.dwolla.Dwolla

internal class UrlBuilder(private val dwolla: Dwolla) {

    fun buildUrl(vararg parts: String): String {
        return parts.fold(dwolla.environment.apiUrl) { url, p ->
            when {
                p.startsWith(dwolla.environment.apiUrl) -> trimSlashes(p)
                p.contains(":") -> throw IllegalArgumentException("bad") // TODO
                else -> "$url/${trimSlashes(p)}"
            }
        }
    }

    private fun trimSlashes(s: String): String {
        return s.removePrefix("/").removeSuffix("/")
    }
}
