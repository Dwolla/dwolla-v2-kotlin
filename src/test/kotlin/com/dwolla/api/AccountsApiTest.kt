package com.dwolla.api

import com.dwolla.Instances.dwolla
import kotlin.test.Test

class AccountsApiTest : ApiTest() {

    @Test
    fun testGet() {
        val accountId = dwolla.root.get()._links["account"]!!.href

        assertResponse { dwolla.accounts.get(accountId) }
    }
}
