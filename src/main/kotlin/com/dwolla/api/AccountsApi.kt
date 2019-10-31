package com.dwolla.api

import com.dwolla.DwollaClient
import com.dwolla.exception.DwollaApiException
import com.dwolla.exception.DwollaAuthException
import com.dwolla.resource.accounts.Account
import com.dwolla.util.Paths.Companion.ACCOUNTS

class AccountsApi(private val client: DwollaClient) {

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun get(id: String): Account {
        return client.get(Account::class.java, accountUrl(id)).body
    }

    private fun accountUrl(id: String): String {
        return client.urlBuilder.buildUrl(ACCOUNTS, id)
    }
}
