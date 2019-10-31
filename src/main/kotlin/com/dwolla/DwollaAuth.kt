package com.dwolla

import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class DwollaAuth(
    internal val dwolla: Dwolla,
    private val redirectUri: String,
    private val state: String? = null,
    scope: String // TODO: remove eventually
) {

    @JvmField val url = "${dwolla.getEnvironment().authBaseUrl}?${encodeWwwForm(
        "response_type" to "code",
        "client_id" to dwolla.getClientId(),
        "redirect_uri" to redirectUri,
        "state" to state,
        "scope" to scope
    )}"

    fun callback(code: String, state: String? = null): DwollaToken {
        require(state == this.state) { "OAuth state mismatch" }

        val res = dwolla.fetchToken(
            "grant_type" to "authorization_code",
            "redirect_uri" to redirectUri,
            "code" to code
        )
        return DwollaToken(
            environment = dwolla.getEnvironment(),
            accessToken = res.accessToken,
            refreshToken = res.refreshToken,
            expiresIn = res.expiresIn,
            idToken = res.idToken
        )
    }

    private fun encodeWwwForm(vararg pairs: Pair<String, String?>): String {
        return pairs
            .filterNot { (_, v) -> v.isNullOrBlank() }
            .joinToString("&") { (k, v) ->
                "$k=${URLEncoder.encode(v, StandardCharsets.UTF_8.name())}"
            }
    }
}
