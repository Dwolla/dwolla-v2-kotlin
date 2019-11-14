package com.dwolla

object Instances {

    val dwolla = Dwolla(
        key = getenvOrReadline("DWOLLA_SANDBOX_KEY"),
        secret = getenvOrReadline("DWOLLA_SANDBOX_SECRET"),
        environment = DwollaEnvironment.SANDBOX
    )

    private fun getenvOrReadline(name: String): String {
        fun readlineWithPrompt(): String {
            println("Please enter your $name and hit Return")
            return readLine()!!
        }
        return System.getenv(name) ?: readlineWithPrompt()
    }
}
