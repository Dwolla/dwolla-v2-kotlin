package com.dwolla.exception

import com.dwolla.http.Headers
import com.dwolla.resource.DwollaAuthError

open class DwollaAuthException(
    @JvmField override val message: String,
    @JvmField val statusCode: Int,
    @JvmField val headers: Headers,
    @JvmField val error: DwollaAuthError
) : Exception(message)
