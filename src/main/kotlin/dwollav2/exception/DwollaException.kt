package dwollav2.exception

import dwollav2.http.Headers
import dwollav2.resource.DwollaError

open class DwollaException(
    @JvmField override val message: String,
    @JvmField val statusCode: Int,
    @JvmField val headers: Headers,
    @JvmField val error: DwollaError
) : Exception(message)
