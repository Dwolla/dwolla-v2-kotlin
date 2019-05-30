package dwollav2.exception

import dwollav2.http.Headers
import dwollav2.resource.OAuthError

open class OAuthException(
    @JvmField override val message: String,
    @JvmField val statusCode: Int,
    @JvmField val headers: Headers,
    @JvmField val error: OAuthError
) : Exception(message)
