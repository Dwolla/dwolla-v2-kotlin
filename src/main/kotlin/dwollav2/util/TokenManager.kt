package dwollav2.util

import dwollav2.Client

internal class TokenManager(private val client: Client) {

    internal var token: Token? = null

    @Synchronized fun getAccessToken(): String {
        val currentToken: Token = if (token == null) client.fetchToken() else token!!
        val freshToken: Token = if (currentToken.isExpired()) client.fetchToken() else currentToken
        if (token != freshToken)
            token = freshToken
        return freshToken.accessToken
    }
}
