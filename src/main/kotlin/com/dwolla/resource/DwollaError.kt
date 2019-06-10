package com.dwolla.resource

import java.util.Optional

data class DwollaError(
    @JvmField val _links: Links,
    @JvmField val code: String,
    @JvmField val message: String,
    @JvmField val _embedded: Optional<EmbeddedErrors>
) : HalResource(_links)
