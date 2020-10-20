package com.dwolla.exception

open class DwollaException(
    message: String,
    cause: Throwable? = null
) : Exception(message, cause)
