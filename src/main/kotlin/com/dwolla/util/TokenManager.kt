package com.dwolla.util

import com.dwolla.Dwolla

internal class TokenManager(private val dwolla: Dwolla) {

    internal var token: Token? = null

    @Synchronized fun getAccessToken(): String {
        val currentToken: Token = if (token == null) dwolla.fetchToken() else token!!
        val freshToken: Token = if (currentToken.isExpired()) dwolla.fetchToken() else currentToken
        if (token != freshToken)
            token = freshToken
        return freshToken.accessToken
    }
}

// freshToken = currentToken.isExpired() ?