package com.dwolla

import com.dwolla.api.BusinessClassificationsApi
import com.dwolla.api.CustomersApi
import com.dwolla.api.DocumentsApi
import com.dwolla.api.FundingSourcesApi
import com.dwolla.api.FundingSourcesTokensApi
import com.dwolla.api.IavTokensApi
import com.dwolla.api.RootApi
import com.dwolla.exception.DwollaException
import com.dwolla.exception.OAuthException
import com.dwolla.http.Headers
import com.dwolla.http.JsonBody
import com.dwolla.http.MultipartBody
import com.dwolla.http.Query
import com.dwolla.http.Response
import com.dwolla.resource.DwollaError
import com.dwolla.resource.OAuthError
import com.dwolla.util.Deserializer
import com.dwolla.util.Token
import com.dwolla.util.TokenManager
import com.dwolla.util.UrlBuilder
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.ResponseResultOf
import com.github.kittinunf.fuel.core.extensions.authentication
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import net.dongliu.gson.GsonJava8TypeAdapterFactory
import java.util.Optional

class Dwolla(
    @JvmField val key: String,
    @JvmField internal val secret: String,
    @JvmField val environment: Environment = Environment.PRODUCTION
) {

    companion object {
        private val ACCEPT_HEADER = mapOf("accept" to "application/vnd.dwolla.v1.hal+json")
        private val USER_AGENT_HEADER = mapOf("user-agent" to "dwolla-v2-kotlin/0.1.0-SNAPSHOT")
    }

    internal val fuelManager: FuelManager = FuelManager()
    internal val gson = initializeGson()
    internal val urlBuilder: UrlBuilder = UrlBuilder(this)
    internal var tokenManager: TokenManager = TokenManager(this)

    @JvmField val businessClassifications = BusinessClassificationsApi(this)
    @JvmField val customers = CustomersApi(this)
    @JvmField val documents = DocumentsApi(this)
    @JvmField val root = RootApi(this)
    @JvmField val fundingSources = FundingSourcesApi(this)
    @JvmField val fundingSourcesTokens = FundingSourcesTokensApi(this)
    @JvmField val iavTokens = IavTokensApi(this)

    @Throws(DwollaException::class, OAuthException::class)
    fun get(path: String): Response<String> {
        val url = urlBuilder.buildUrl(path)
        return makeRequest(fuelManager.get(url))
    }

    @Throws(DwollaException::class, OAuthException::class)
    fun get(path: String, headers: Headers): Response<String> {
        val url = urlBuilder.buildUrl(path)
        return makeRequest(fuelManager.get(url).header(headers.map))
    }

    @Throws(DwollaException::class, OAuthException::class)
    fun get(path: String, query: Query): Response<String> {
        val url = urlBuilder.buildUrl(path)
        return makeRequest(fuelManager.get(url, query.list))
    }

    @Throws(DwollaException::class, OAuthException::class)
    fun get(path: String, query: Query, headers: Headers): Response<String> {
        val url = urlBuilder.buildUrl(path)
        return makeRequest(fuelManager.get(url, query.list).header(headers.map))
    }

    @Throws(DwollaException::class, OAuthException::class)
    fun <T : Any> get(deserializeAs: Class<T>, path: String): Response<T> {
        val url = urlBuilder.buildUrl(path)
        return makeRequest(deserializeAs, fuelManager.get(url))
    }

    @Throws(DwollaException::class, OAuthException::class)
    fun <T : Any> get(deserializeAs: Class<T>, path: String, headers: Headers): Response<T> {
        val url = urlBuilder.buildUrl(path)
        return makeRequest(deserializeAs, fuelManager.get(url).header(headers.map))
    }

    @Throws(DwollaException::class, OAuthException::class)
    fun <T : Any> get(deserializeAs: Class<T>, path: String, query: Query): Response<T> {
        val url = urlBuilder.buildUrl(path)
        return makeRequest(deserializeAs, fuelManager.get(url, query.list))
    }

    @Throws(DwollaException::class, OAuthException::class)
    fun <T : Any> get(deserializeAs: Class<T>, path: String, query: Query, headers: Headers): Response<T> {
        val url = urlBuilder.buildUrl(path)
        return makeRequest(deserializeAs, fuelManager.get(url, query.list).header(headers.map))
    }

    @Throws(DwollaException::class, OAuthException::class)
    fun post(path: String): Response<String> {
        val url = urlBuilder.buildUrl(path)
        return makeRequest(fuelManager.post(url))
    }

    @Throws(DwollaException::class, OAuthException::class)
    fun post(path: String, headers: Headers): Response<String> {
        val url = urlBuilder.buildUrl(path)
        return makeRequest(fuelManager.post(url).header(headers.map))
    }

    @Throws(DwollaException::class, OAuthException::class)
    fun post(path: String, body: JsonBody): Response<String> {
        val url = urlBuilder.buildUrl(path)
        val jsonBody = gson.toJson(body.map)
        return makeRequest(fuelManager.post(url).jsonBody(jsonBody))
    }

    @Throws(DwollaException::class, OAuthException::class)
    fun post(path: String, body: MultipartBody): Response<String> {
        val url = urlBuilder.buildUrl(path)
        return makeRequest(fuelManager.upload(url).add(*body.list.toTypedArray()))
    }

    @Throws(DwollaException::class, OAuthException::class)
    fun post(path: String, body: JsonBody, headers: Headers): Response<String> {
        val url = urlBuilder.buildUrl(path)
        val jsonBody = gson.toJson(body.map)
        return makeRequest(fuelManager.post(url).jsonBody(jsonBody).header(headers.map))
    }

    @Throws(DwollaException::class, OAuthException::class)
    fun post(path: String, body: MultipartBody, headers: Headers): Response<String> {
        val url = urlBuilder.buildUrl(path)
        return makeRequest(fuelManager.upload(url).add(*body.list.toTypedArray()).header(headers.map))
    }

    @Throws(DwollaException::class, OAuthException::class)
    fun <T : Any> post(deserializeAs: Class<T>, path: String): Response<T> {
        val url = urlBuilder.buildUrl(path)
        return makeRequest(deserializeAs, fuelManager.post(url))
    }

    @Throws(DwollaException::class, OAuthException::class)
    fun <T : Any> post(deserializeAs: Class<T>, path: String, headers: Headers): Response<T> {
        val url = urlBuilder.buildUrl(path)
        return makeRequest(deserializeAs, fuelManager.post(url).header(headers.map))
    }

    @Throws(DwollaException::class, OAuthException::class)
    fun <T : Any> post(deserializeAs: Class<T>, path: String, body: JsonBody): Response<T> {
        val url = urlBuilder.buildUrl(path)
        val jsonBody = gson.toJson(body.map)
        return makeRequest(deserializeAs, fuelManager.post(url).jsonBody(jsonBody))
    }

    @Throws(DwollaException::class, OAuthException::class)
    fun <T : Any> post(deserializeAs: Class<T>, path: String, body: MultipartBody): Response<T> {
        val url = urlBuilder.buildUrl(path)
        return makeRequest(deserializeAs, fuelManager.upload(url).add(*body.list.toTypedArray()))
    }

    @Throws(DwollaException::class, OAuthException::class)
    fun <T : Any> post(deserializeAs: Class<T>, path: String, body: JsonBody, headers: Headers): Response<T> {
        val url = urlBuilder.buildUrl(path)
        val jsonBody = gson.toJson(body.map)
        return makeRequest(deserializeAs, fuelManager.post(url).jsonBody(jsonBody).header(headers.map))
    }

    @Throws(DwollaException::class, OAuthException::class)
    fun <T : Any> post(deserializeAs: Class<T>, path: String, body: MultipartBody, headers: Headers): Response<T> {
        val url = urlBuilder.buildUrl(path)
        return makeRequest(deserializeAs, fuelManager.upload(url).add(*body.list.toTypedArray()).header(headers.map))
    }

    @Throws(DwollaException::class, OAuthException::class)
    internal fun postFollow(path: String): Response<String> {
        val url = urlBuilder.buildUrl(path)
        return follow(makeRequest(fuelManager.post(url)))
    }

    @Throws(DwollaException::class, OAuthException::class)
    internal fun postFollow(path: String, headers: Headers): Response<String> {
        val url = urlBuilder.buildUrl(path)
        return follow(makeRequest(fuelManager.post(url).header(headers.map)))
    }

    @Throws(DwollaException::class, OAuthException::class)
    internal fun postFollow(path: String, body: JsonBody): Response<String> {
        val url = urlBuilder.buildUrl(path)
        val jsonBody = gson.toJson(body.map)
        return follow(makeRequest(fuelManager.post(url).jsonBody(jsonBody)))
    }

    @Throws(DwollaException::class, OAuthException::class)
    internal fun postFollow(path: String, body: MultipartBody): Response<String> {
        val url = urlBuilder.buildUrl(path)
        return follow(makeRequest(fuelManager.upload(url).add(*body.list.toTypedArray())))
    }

    @Throws(DwollaException::class, OAuthException::class)
    internal fun postFollow(path: String, body: JsonBody, headers: Headers): Response<String> {
        val url = urlBuilder.buildUrl(path)
        val jsonBody = gson.toJson(body.map)
        return follow(makeRequest(fuelManager.post(url).jsonBody(jsonBody).header(headers.map)))
    }

    @Throws(DwollaException::class, OAuthException::class)
    internal fun postFollow(path: String, body: MultipartBody, headers: Headers): Response<String> {
        val url = urlBuilder.buildUrl(path)
        return follow(makeRequest(fuelManager.upload(url).add(*body.list.toTypedArray()).header(headers.map)))
    }

    @Throws(DwollaException::class, OAuthException::class)
    internal fun <T : Any> postFollow(deserializeAs: Class<T>, path: String): Response<T> {
        val url = urlBuilder.buildUrl(path)
        return follow(deserializeAs, makeRequest(fuelManager.post(url)))
    }

    @Throws(DwollaException::class, OAuthException::class)
    internal fun <T : Any> postFollow(deserializeAs: Class<T>, path: String, headers: Headers): Response<T> {
        val url = urlBuilder.buildUrl(path)
        return follow(deserializeAs, makeRequest(fuelManager.post(url).header(headers.map)))
    }

    @Throws(DwollaException::class, OAuthException::class)
    internal fun <T : Any> postFollow(deserializeAs: Class<T>, path: String, body: JsonBody): Response<T> {
        val url = urlBuilder.buildUrl(path)
        val jsonBody = gson.toJson(body.map)
        return follow(deserializeAs, makeRequest(fuelManager.post(url).jsonBody(jsonBody)))
    }

    @Throws(DwollaException::class, OAuthException::class)
    internal fun <T : Any> postFollow(deserializeAs: Class<T>, path: String, body: MultipartBody): Response<T> {
        val url = urlBuilder.buildUrl(path)
        return follow(deserializeAs, makeRequest(fuelManager.upload(url).add(*body.list.toTypedArray())))
    }

    @Throws(DwollaException::class, OAuthException::class)
    internal fun <T : Any> postFollow(deserializeAs: Class<T>, path: String, body: JsonBody, headers: Headers): Response<T> {
        val url = urlBuilder.buildUrl(path)
        val jsonBody = gson.toJson(body.map)
        return follow(deserializeAs, makeRequest(fuelManager.post(url).jsonBody(jsonBody).header(headers.map)))
    }

    @Throws(DwollaException::class, OAuthException::class)
    internal fun <T : Any> postFollow(deserializeAs: Class<T>, path: String, body: MultipartBody, headers: Headers): Response<T> {
        val url = urlBuilder.buildUrl(path)
        return follow(deserializeAs, makeRequest(fuelManager.upload(url).add(*body.list.toTypedArray()).header(headers.map)))
    }

    internal fun fetchToken(): Token {
        val result = fuelManager
            .post(environment.tokenUrl, listOf("grant_type" to "client_credentials"))
            .header(USER_AGENT_HEADER)
            .authentication()
            .basic(key, secret)
            .responseObject(Deserializer(gson, ClientCredentialsResponse::class.java))
        val responseHeaders = Headers(*result.second.headers.map { h -> h.key to h.value.first() }.toTypedArray())
        return result.third.fold(
            success = { res -> Token(res.accessToken, res.expiresIn) },
            failure = { _ ->
                val rawBody = result.second.data.toString(Charsets.UTF_8)
                if (rawBody.isBlank() && result.second.statusCode == 401) {
                    // for some reason we can't read the response body when a 401 is returned
                    val error = OAuthError("", Optional.empty(), Optional.empty())
                    throw OAuthException("401 Unauthorized", result.second.statusCode, responseHeaders, error)
                } else {
                    val error = Deserializer(gson, OAuthError::class.java).deserialize(rawBody)
                    throw OAuthException(rawBody, result.second.statusCode, responseHeaders, error)
                }
            }
        )
    }

    private fun follow(response: Response<String>): Response<String> {
        val location = response.headers.get(com.dwolla.util.Headers.LOCATION)!!
        return get(location)
    }

    private fun <T : Any> follow(deserializeAs: Class<T>, response: Response<String>): Response<T> {
        val location = response.headers.get(com.dwolla.util.Headers.LOCATION)!!
        return get(deserializeAs, location)
    }

    private fun makeRequest(request: Request): Response<String> {
        val preparedRequest = prepareRequest(request)
        val result = preparedRequest.responseString()
        return handleResponse(result)
    }

    private fun <T : Any> makeRequest(deserializeAs: Class<T>, request: Request): Response<T> {
        val preparedRequest = prepareRequest(request)
        val result = preparedRequest.responseObject(Deserializer(gson, deserializeAs))
        return handleResponse(result)
    }

    private fun prepareRequest(request: Request): Request {
        return request
            .header(ACCEPT_HEADER + USER_AGENT_HEADER)
            .authentication()
            .bearer(tokenManager.getAccessToken())
    }

    private fun <T : Any> handleResponse(result: ResponseResultOf<T>): Response<T> {
        val responseHeaders = Headers(*result.second.headers.map { h -> h.key to h.value.first() }.toTypedArray())
        return result.third.fold(
            success = { res -> Response(result.second.statusCode, responseHeaders, res) },
            failure = { _ ->
                val rawBody = result.second.data.toString(Charsets.UTF_8)
                if (rawBody.isBlank() && result.second.statusCode == 401) {
                    // for some reason we can't read the response body when a 401 is returned
                    val error = DwollaError(mapOf(), "", "", Optional.empty())
                    throw DwollaException("401 Unauthorized", result.second.statusCode, responseHeaders, error)
                } else {
                    val error = Deserializer(gson, DwollaError::class.java).deserialize(rawBody)
                    throw DwollaException(rawBody, result.second.statusCode, responseHeaders, error)
                }
            }
        )
    }

    private fun initializeGson(): Gson {
        return GsonBuilder()
            .registerTypeAdapterFactory(GsonJava8TypeAdapterFactory())
            .setPrettyPrinting()
            .create()
    }
}

private class ClientCredentialsResponse(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("expires_in") val expiresIn: Long
)
