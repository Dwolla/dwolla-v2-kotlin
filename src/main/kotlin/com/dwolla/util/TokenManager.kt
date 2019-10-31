package com.dwolla.util

import com.dwolla.Dwolla

internal class TokenManager(private val dwolla: Dwolla) {

    internal var token: TokenManagerToken? = null

    @Synchronized fun getAccessToken(): String {
        val currentToken: TokenManagerToken = if (token == null) fetchToken() else token!!
        val freshToken: TokenManagerToken = if (currentToken.isExpired()) fetchToken() else currentToken
        if (token != freshToken)
            token = freshToken
        return freshToken.accessToken
    }

    private fun fetchToken(): TokenManagerToken {
        val res = dwolla.fetchToken("grant_type" to "client_credentials")
        return TokenManagerToken(accessToken = res.accessToken, expiresIn = res.expiresIn)
    }
}
