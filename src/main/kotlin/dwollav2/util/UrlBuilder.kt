package dwollav2.util

import dwollav2.Client

internal class UrlBuilder(private val client: Client) {

    fun buildUrl(vararg parts: String): String {
        return parts.fold(client.environment.apiUrl) { url, p ->
            when {
                p.startsWith(client.environment.apiUrl) -> trimSlashes(p)
                p.contains(":") -> throw IllegalArgumentException("bad")
                else -> "$url/${trimSlashes(p)}"
            }
        }
    }

    private fun trimSlashes(s: String): String {
        return s.removePrefix("/").removeSuffix("/")
    }
}
