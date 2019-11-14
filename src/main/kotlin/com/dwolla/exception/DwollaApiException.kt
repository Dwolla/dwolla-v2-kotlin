package com.dwolla.exception

import com.dwolla.http.Headers
import com.dwolla.resource.DwollaApiError

open class DwollaApiException(
    @JvmField override val message: String,
    @JvmField val statusCode: Int,
    @JvmField val headers: Headers,
    @JvmField val error: DwollaApiError
) : Exception(message)
