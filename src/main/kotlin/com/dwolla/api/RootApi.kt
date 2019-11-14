package com.dwolla.api

import com.dwolla.DwollaClient
import com.dwolla.exception.DwollaApiException
import com.dwolla.exception.DwollaAuthException
import com.dwolla.resource.root.Root

class RootApi(private val client: DwollaClient) {

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun get(): Root {
        return client.get(Root::class.java, "/").body
    }
}
