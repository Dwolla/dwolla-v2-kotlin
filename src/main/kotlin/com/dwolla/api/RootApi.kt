package com.dwolla.api

import com.dwolla.Client
import com.dwolla.exception.DwollaException
import com.dwolla.exception.OAuthException
import com.dwolla.resource.root.Root

class RootApi(private val client: Client) {

    @Throws(DwollaException::class, OAuthException::class)
    fun get(): Root {
        return client.get(Root::class.java, "/").body
    }
}
