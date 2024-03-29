package com.dwolla

enum class DwollaEnvironment : Environment {
    PRODUCTION {
        override val tokenUrl = "https://api.dwolla.com/token"
        override val apiBaseUrl = "https://api.dwolla.com"
        override val authBaseUrl = "https://accounts.dwolla.com/auth"
    },

    SANDBOX {
        override val tokenUrl = "https://api-sandbox.dwolla.com/token"
        override val apiBaseUrl = "https://api-sandbox.dwolla.com"
        override val authBaseUrl = "https://accounts-sandbox.dwolla.com/auth"
    };

    internal abstract val tokenUrl: String
    internal abstract val apiBaseUrl: String
    internal abstract val authBaseUrl: String

    override fun tokenUrl(): String {
        return tokenUrl
    }

    override fun apiBaseUrl(): String {
        return apiBaseUrl
    }

    override fun authBaseUrl(): String {
        return authBaseUrl
    }
}
