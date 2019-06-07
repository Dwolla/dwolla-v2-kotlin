package com.dwolla.resource

data class EmbeddedError(
        @JvmField val _links: Links,
        @JvmField val code: String,
        @JvmField val message: String,
        @JvmField val path: String
) : HalResource(_links)
