package dwolla.exception

import dwolla.http.Headers
import dwolla.resource.OAuthError

open class OAuthException(
    @JvmField override val message: String,
    @JvmField val statusCode: Int,
    @JvmField val headers: Headers,
    @JvmField val error: OAuthError
) : Exception(message)
