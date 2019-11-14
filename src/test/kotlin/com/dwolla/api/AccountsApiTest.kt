package com.dwolla.api

import com.dwolla.Instances.dwolla
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import kotlin.test.Test

@Tags(Tag("api"))
class AccountsApiTest : ApiTest() {

    @Test
    fun testGet() {
        val accountId = dwolla.root.get()._links["account"]!!.href

        assertResponse { dwolla.accounts.get(accountId) }
    }
}
