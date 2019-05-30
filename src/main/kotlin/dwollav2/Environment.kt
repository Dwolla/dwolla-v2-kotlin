package dwollav2

enum class Environment {
    PRODUCTION {
        override val tokenUrl = "https://api.dwolla.com/token"
        override val apiUrl = "https://api.dwolla.com"
    },

    SANDBOX {
        override val tokenUrl = "https://api-sandbox.dwolla.com/token"
        override val apiUrl = "https://api-sandbox.dwolla.com"
    };

    internal abstract val tokenUrl: String
    internal abstract val apiUrl: String
}
