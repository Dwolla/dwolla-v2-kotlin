package com.dwolla.api

import com.dwolla.Dwolla
import com.dwolla.Instances.dwolla
import com.dwolla.http.JsonBody
import com.github.kittinunf.fuel.core.Headers
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.core.requests.DefaultBody
import io.mockk.every
import java.net.URL
import java.util.* // ktlint-disable no-wildcard-imports
import kotlin.reflect.full.memberProperties
import kotlin.test.assertNotNull

abstract class ApiTest {

    private val businessClassification = lazy {
        dwolla.businessClassifications.list()._embedded.businessClassifications.first()
            ._embedded.industryClassifications.first().id
    }

    protected fun <T : Any> assertParsedResponse(body: () -> T?) {
        val instance = body() ?: return
        if (instance::class.qualifiedName?.startsWith("com.dwolla") != true) return

        instance::class.memberProperties
            .filter { !it.returnType.isMarkedNullable }
            .forEach {
                val newInstance = it.getter.call(instance)
                assertNotNull(newInstance)

                when (newInstance) {
                    is Iterable<*> -> newInstance.forEach { assertParsedResponse { it } }
                    is Map<*, *> -> newInstance.entries.forEach { assertParsedResponse { it.value } }
                    else -> assertParsedResponse { newInstance }
                }
            }
    }

    protected fun <T : Any> assertResponse(body: () -> T): T {
        val className = Thread.currentThread().stackTrace[2].className.split(".").last()
        val methodName = Thread.currentThread().stackTrace[2].methodName
        println("$className.$methodName")
        val res = body()
        println(dwolla.gson.toJson(res))
        Thread.sleep(1000)
        return res
    }

    protected fun email(): String {
        return "${string()}@test.com"
    }

    protected fun string(): String {
        return UUID.randomUUID().toString()
    }

    protected fun businessClassification(): String {
        return businessClassification.value
    }

    protected fun mockRequest(dwolla: Dwolla, statusCode: Int, responseBody: JsonBody) {
        every { dwolla.fuelManager.client.executeRequest(any()) } returns Response(
            body = DefaultBody.from({ dwolla.gson.toJson(responseBody).byteInputStream() }, null),
            headers = Headers(),
            responseMessage = "OK",
            statusCode = statusCode,
            url = URL("https://dwolla.com")
        )
    }
}
