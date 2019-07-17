package com.dwolla.api

import com.dwolla.Dwolla
import com.dwolla.exception.DwollaException
import com.dwolla.exception.OAuthException
import com.dwolla.resource.root.Root

class RootApi(private val dwolla: Dwolla) {

    @Throws(DwollaException::class, OAuthException::class)
    fun get(): Root {
        return dwolla.get(Root::class.java, "/").body
    }
}
