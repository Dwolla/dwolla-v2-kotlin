package dwolla.exception

import dwolla.http.Headers
import dwolla.resource.DwollaError

open class DwollaException(
    @JvmField override val message: String,
    @JvmField val statusCode: Int,
    @JvmField val headers: Headers,
    @JvmField val error: DwollaError
) : Exception(message)
