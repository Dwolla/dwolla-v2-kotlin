package com.dwolla.api

import com.dwolla.Instances.dwolla
import java.util.UUID

abstract class ApiTest {

    private val businessClassification = lazy {
        dwolla.businessClassifications.list()._embedded.businessClassifications.first()
            ._embedded.industryClassifications.first().id
    }

    protected fun <T : Any> assertResponse(body: () -> T): T {
        val className = Thread.currentThread().stackTrace[2].className.split(".").last()
        val methodName = Thread.currentThread().stackTrace[2].methodName
        println("$className.$methodName")
        val res = body()
        println(dwolla.gson.toJson(res))
        Thread.sleep(2000)
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
}
