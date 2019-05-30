package dwollav2.http

data class Response<T>(
    @JvmField val statusCode: Int,
    @JvmField val headers: Headers,
    @JvmField val body: T
)
