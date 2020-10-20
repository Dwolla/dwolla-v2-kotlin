package com.dwolla

import com.dwolla.exception.DwollaApiException
import com.dwolla.exception.DwollaAuthException
import com.dwolla.exception.DwollaException
import com.dwolla.http.* // ktlint-disable no-wildcard-imports
import com.dwolla.resource.DwollaApiError
import com.dwolla.util.Deserializer
import com.dwolla.util.UrlBuilder
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.ResponseResultOf
import com.github.kittinunf.fuel.core.extensions.authentication
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.google.gson.GsonBuilder
import net.dongliu.gson.GsonJava8TypeAdapterFactory

abstract class DwollaClient(@JvmField val environment: DwollaEnvironment) {

    protected abstract fun getAccessToken(): String

    internal val urlBuilder: UrlBuilder = UrlBuilder(environment)
    internal val fuelManager: FuelManager = FuelManager()
    internal val gson = GsonBuilder()
        .registerTypeAdapterFactory(GsonJava8TypeAdapterFactory())
        .registerTypeAdapter(JsonBody::class.java, JsonBodyJsonSerializer())
        .setPrettyPrinting()
        .create()

    companion object {
        val ACCEPT_HEADER = mapOf("accept" to "application/vnd.dwolla.v1.hal+json")
        val USER_AGENT_HEADER = mapOf("user-agent" to "dwolla-v2-kotlin/0.2.0")
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun get(path: String): Response<String> {
        val url = urlBuilder.buildUrl(path)
        return makeRequest(fuelManager.get(url))
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun get(path: String, headers: Headers): Response<String> {
        val url = urlBuilder.buildUrl(path)
        return makeRequest(fuelManager.get(url).header(headers.map))
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun get(path: String, query: Query): Response<String> {
        val url = urlBuilder.buildUrl(path)
        return makeRequest(fuelManager.get(url, query.list))
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun get(path: String, query: Query, headers: Headers): Response<String> {
        val url = urlBuilder.buildUrl(path)
        return makeRequest(fuelManager.get(url, query.list).header(headers.map))
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun <T : Any> get(deserializeAs: Class<T>, path: String): Response<T> {
        val url = urlBuilder.buildUrl(path)
        return makeRequest(deserializeAs, fuelManager.get(url))
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun <T : Any> get(deserializeAs: Class<T>, path: String, headers: Headers): Response<T> {
        val url = urlBuilder.buildUrl(path)
        return makeRequest(deserializeAs, fuelManager.get(url).header(headers.map))
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun <T : Any> get(deserializeAs: Class<T>, path: String, query: Query): Response<T> {
        val url = urlBuilder.buildUrl(path)
        return makeRequest(deserializeAs, fuelManager.get(url, query.list))
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun <T : Any> get(deserializeAs: Class<T>, path: String, query: Query, headers: Headers): Response<T> {
        val url = urlBuilder.buildUrl(path)
        return makeRequest(deserializeAs, fuelManager.get(url, query.list).header(headers.map))
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun post(path: String): Response<String> {
        val url = urlBuilder.buildUrl(path)
        return makeRequest(fuelManager.post(url))
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun post(path: String, headers: Headers): Response<String> {
        val url = urlBuilder.buildUrl(path)
        return makeRequest(fuelManager.post(url).header(headers.map))
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun post(path: String, body: JsonBody): Response<String> {
        val url = urlBuilder.buildUrl(path)
        val jsonBody = gson.toJson(body.map)
        return makeRequest(fuelManager.post(url).jsonBody(jsonBody))
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun post(path: String, body: JsonBody, headers: Headers): Response<String> {
        val url = urlBuilder.buildUrl(path)
        val jsonBody = gson.toJson(body.map)
        return makeRequest(fuelManager.post(url).jsonBody(jsonBody).header(headers.map))
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun <T : Any> post(deserializeAs: Class<T>, path: String): Response<T> {
        val url = urlBuilder.buildUrl(path)
        return makeRequest(deserializeAs, fuelManager.post(url))
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun <T : Any> post(deserializeAs: Class<T>, path: String, headers: Headers): Response<T> {
        val url = urlBuilder.buildUrl(path)
        return makeRequest(deserializeAs, fuelManager.post(url).header(headers.map))
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun <T : Any> post(deserializeAs: Class<T>, path: String, body: JsonBody): Response<T> {
        val url = urlBuilder.buildUrl(path)
        val jsonBody = gson.toJson(body.map)
        return makeRequest(deserializeAs, fuelManager.post(url).jsonBody(jsonBody))
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun <T : Any> post(deserializeAs: Class<T>, path: String, body: JsonBody, headers: Headers): Response<T> {
        val url = urlBuilder.buildUrl(path)
        val jsonBody = gson.toJson(body.map)
        return makeRequest(deserializeAs, fuelManager.post(url).jsonBody(jsonBody).header(headers.map))
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun post(path: String, body: MultipartBody): Response<String> {
        val url = urlBuilder.buildUrl(path)
        return makeRequest(fuelManager.upload(url).add(*body.list.toTypedArray()))
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun post(path: String, body: MultipartBody, headers: Headers): Response<String> {
        val url = urlBuilder.buildUrl(path)
        return makeRequest(fuelManager.upload(url).add(*body.list.toTypedArray()).header(headers.map))
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun <T : Any> post(deserializeAs: Class<T>, path: String, body: MultipartBody): Response<T> {
        val url = urlBuilder.buildUrl(path)
        return makeRequest(deserializeAs, fuelManager.upload(url).add(*body.list.toTypedArray()))
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun <T : Any> post(deserializeAs: Class<T>, path: String, body: MultipartBody, headers: Headers): Response<T> {
        val url = urlBuilder.buildUrl(path)
        return makeRequest(deserializeAs, fuelManager.upload(url).add(*body.list.toTypedArray()).header(headers.map))
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    internal fun postFollow(path: String): Response<String> {
        val url = urlBuilder.buildUrl(path)
        return follow(makeRequest(fuelManager.post(url)))
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    internal fun postFollow(path: String, headers: Headers): Response<String> {
        val url = urlBuilder.buildUrl(path)
        return follow(makeRequest(fuelManager.post(url).header(headers.map)))
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    internal fun postFollow(path: String, body: JsonBody): Response<String> {
        val url = urlBuilder.buildUrl(path)
        val jsonBody = gson.toJson(body.map)
        return follow(makeRequest(fuelManager.post(url).jsonBody(jsonBody)))
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    internal fun postFollow(path: String, body: JsonBody, headers: Headers): Response<String> {
        val url = urlBuilder.buildUrl(path)
        val jsonBody = gson.toJson(body.map)
        return follow(makeRequest(fuelManager.post(url).jsonBody(jsonBody).header(headers.map)))
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    internal fun <T : Any> postFollow(deserializeAs: Class<T>, path: String): Response<T> {
        val url = urlBuilder.buildUrl(path)
        return follow(deserializeAs, makeRequest(fuelManager.post(url)))
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    internal fun <T : Any> postFollow(deserializeAs: Class<T>, path: String, headers: Headers): Response<T> {
        val url = urlBuilder.buildUrl(path)
        return follow(deserializeAs, makeRequest(fuelManager.post(url).header(headers.map)))
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    internal fun <T : Any> postFollow(deserializeAs: Class<T>, path: String, body: JsonBody): Response<T> {
        val url = urlBuilder.buildUrl(path)
        val jsonBody = gson.toJson(body.map)
        return follow(deserializeAs, makeRequest(fuelManager.post(url).jsonBody(jsonBody)))
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    internal fun <T : Any> postFollow(deserializeAs: Class<T>, path: String, body: JsonBody, headers: Headers): Response<T> {
        val url = urlBuilder.buildUrl(path)
        val jsonBody = gson.toJson(body.map)
        return follow(deserializeAs, makeRequest(fuelManager.post(url).jsonBody(jsonBody).header(headers.map)))
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    internal fun postFollow(path: String, body: MultipartBody): Response<String> {
        val url = urlBuilder.buildUrl(path)
        return follow(makeRequest(fuelManager.upload(url).add(*body.list.toTypedArray())))
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    internal fun postFollow(path: String, body: MultipartBody, headers: Headers): Response<String> {
        val url = urlBuilder.buildUrl(path)
        return follow(makeRequest(fuelManager.upload(url).add(*body.list.toTypedArray()).header(headers.map)))
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    internal fun <T : Any> postFollow(deserializeAs: Class<T>, path: String, body: MultipartBody): Response<T> {
        val url = urlBuilder.buildUrl(path)
        return follow(deserializeAs, makeRequest(fuelManager.upload(url).add(*body.list.toTypedArray())))
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    internal fun <T : Any> postFollow(deserializeAs: Class<T>, path: String, body: MultipartBody, headers: Headers): Response<T> {
        val url = urlBuilder.buildUrl(path)
        return follow(deserializeAs, makeRequest(fuelManager.upload(url).add(*body.list.toTypedArray()).header(headers.map)))
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun delete(path: String): Response<String> {
        val url = urlBuilder.buildUrl(path)
        return makeRequest(fuelManager.delete(url))
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun delete(path: String, headers: Headers): Response<String> {
        val url = urlBuilder.buildUrl(path)
        return makeRequest(fuelManager.delete(url).header(headers.map))
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
        try {
            val preparedRequest = prepareRequest(request)
            val result = preparedRequest.responseString()
            return handleResponse(result)
        } catch (e: DwollaException) {
            throw e
        } catch (e: Exception) {
            throw DwollaException("See stack trace for more details...", e)
        }
    }

    private fun <T : Any> makeRequest(deserializeAs: Class<T>, request: Request): Response<T> {
        try {
            val preparedRequest = prepareRequest(request)
            val result = preparedRequest.responseObject(Deserializer(gson, deserializeAs))
            return handleResponse(result)
        } catch (e: DwollaException) {
            throw e
        } catch (e: Exception) {
            throw DwollaException("See stack trace for more details...", e)
        }
    }

    private fun prepareRequest(request: Request): Request {
        return request
            .header(ACCEPT_HEADER + USER_AGENT_HEADER)
            .authentication()
            .bearer(getAccessToken())
    }

    private fun <T : Any> handleResponse(result: ResponseResultOf<T>): Response<T> {
        val responseHeaders = Headers(*result.second.headers.map { h -> h.key to h.value.first() }.toTypedArray())
        return result.third.fold(
            success = { res -> Response(result.second.statusCode, responseHeaders, res) },
            failure = { _ ->
                val rawBody = result.second.data.toString(Charsets.UTF_8)
                if (rawBody.isBlank() && result.second.statusCode == 401) {
                    // for some reason we can't read the response body when a 401 is returned
                    val error = DwollaApiError(mapOf(), "", "", null)
                    throw DwollaApiException("401 Unauthorized", result.second.statusCode, responseHeaders, error)
                } else {
                    val error = Deserializer(gson, DwollaApiError::class.java).deserialize(rawBody)
                    throw DwollaApiException(rawBody, result.second.statusCode, responseHeaders, error)
                }
            }
        )
    }

    // NOTE: for testing purposes...
    internal fun getEnvironment(): DwollaEnvironment {
        return environment
    }
}
