package com.dwolla

import com.dwolla.api.* // ktlint-disable no-wildcard-imports
import com.dwolla.exception.DwollaAuthException
import com.dwolla.exception.DwollaException
import com.dwolla.http.Headers
import com.dwolla.resource.DwollaAuthError
import com.dwolla.util.Deserializer
import com.dwolla.util.TokenManager
import com.github.kittinunf.fuel.core.extensions.authentication
import com.google.gson.annotations.SerializedName

class Dwolla(
    @JvmField val key: String,
    @JvmField internal val secret: String,
    environment: DwollaEnvironment = DwollaEnvironment.PRODUCTION
) : DwollaClient(environment) {

    @JvmField val accounts = AccountsApi(this)
    @JvmField val beneficialOwners = BeneficialOwnersApi(this)
    @JvmField val businessClassifications = BusinessClassificationsApi(this)
    @JvmField val customers = CustomersApi(this)
    @JvmField val documents = DocumentsApi(this)
    @JvmField val fundingSources = FundingSourcesApi(this)
    @JvmField val fundingSourcesTokens = FundingSourcesTokensApi(this)
    @JvmField val iavTokens = IavTokensApi(this)
    @JvmField val root = RootApi(this)
    @JvmField internal val transfers = TransfersApi(this)

    // internal for testing purposes
    internal var tokenManager: TokenManager = TokenManager(this)

    override fun getAccessToken(): String {
        return tokenManager.getAccessToken()
    }

    fun auth(redirectUri: String, state: String? = null, scope: String): DwollaAuth {
        return DwollaAuth(
            dwolla = this,
            redirectUri = redirectUri,
            state = state,
            scope = scope
        )
    }

    fun token(accessToken: String): DwollaToken {
        return DwollaToken(
            environment = environment,
            accessToken = accessToken
        )
    }

    fun refreshToken(refreshToken: String): DwollaToken {
        val res = fetchToken(
            "grant_type" to "refresh_token",
            "refresh_token" to refreshToken
        )
        return DwollaToken(
            environment = environment,
            accessToken = res.accessToken,
            refreshToken = res.refreshToken,
            expiresIn = res.expiresIn,
            idToken = res.idToken
        )
    }

    internal fun fetchToken(vararg params: Pair<String, String?>): TokenResponse {
        try {
            val result = fuelManager
                .post(environment.tokenUrl, params.filterNot { (_, v) -> v.isNullOrBlank() }.toList())
                .header(USER_AGENT_HEADER)
                .authentication()
                .basic(key, secret)
                .responseObject(Deserializer(gson, TokenResponse::class.java))
            val responseHeaders = Headers(*result.second.headers.map { h -> h.key to h.value.first() }.toTypedArray())
            return result.third.fold(
                success = { res -> res },
                failure = {
                    val rawBody = result.second.data.toString(Charsets.UTF_8)
                    if (rawBody.isBlank() && result.second.statusCode == 401) {
                        // for some reason we can't read the response body when a 401 is returned
                        val error = DwollaAuthError("", null, null)
                        throw DwollaAuthException("401 Unauthorized", result.second.statusCode, responseHeaders, error)
                    } else {
                        val error = Deserializer(gson, DwollaAuthError::class.java).deserialize(rawBody)
                        throw DwollaAuthException(rawBody, result.second.statusCode, responseHeaders, error)
                    }
                }
            )
        } catch (e: DwollaException) {
            throw e
        } catch (e: Exception) {
            throw DwollaException("See stack trace for more details...", e)
        }
    }

    // for testing purposes
    internal fun getClientId(): String {
        return key
    }
}

internal class TokenResponse(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("expires_in") val expiresIn: Long,
    @SerializedName("refresh_token") val refreshToken: String? = null,
    @SerializedName("id_token") val idToken: String? = null
)
