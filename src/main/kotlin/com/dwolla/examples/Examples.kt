package com.dwolla.examples

import com.dwolla.Dwolla
import com.dwolla.Environment
import java.util.UUID

abstract class Examples {
    protected abstract val scope: String

    protected val dwolla = Dwolla(
        key = getenvOrReadline("DWOLLA_SANDBOX_KEY"),
        secret = getenvOrReadline("DWOLLA_SANDBOX_SECRET"),
        environment = Environment.SANDBOX
    )

    abstract fun run()

    protected fun rand(): String {
        return UUID.randomUUID().toString()
    }

    protected fun <T : Any> example(name: String, body: () -> T): T {
        val heading = "Examples: $scope.$name"
        println("-".repeat(heading.length))
        println(heading)
        println("")
        val result = body()
        println(dwolla.gson.toJson(result).prependIndent("  "))
        println("")
        println("")
        return result
    }

    private fun getenvOrReadline(name: String): String {
        fun readlineWithPrompt(): String {
            println("Please enter your $name and hit Return")
            return readLine()!!
        }
        return System.getenv(name) ?: readlineWithPrompt()
    }
}
