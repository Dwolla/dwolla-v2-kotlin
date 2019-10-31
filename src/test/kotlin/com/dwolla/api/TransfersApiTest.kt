package com.dwolla.api

import com.dwolla.Instances.dwolla
import com.dwolla.shared.Amount
import kotlin.test.Test

class TransfersApiTest : ApiTest() {

    @Test
    fun `testCreate (source, destination)`() {
        // val accountId = dwolla.root.get()._links["account"]!!.href
        // val source = dwolla.accounts.get(accountId)
        // val destination = dwolla.customers.createUnverified(
        //     firstName = string(),
        //     lastName = string(),
        //     email = email()
        // )

        assertResponse {
            dwolla.transfers.create(
                sourceFundingSource = dwolla.fundingSources.get("65cd94cf-2de6-498b-a08c-1c6cf9223a15"),
                destinationFundingSource = dwolla.fundingSources.get("eb4e32de-eef2-496c-a6e0-5be8596b2096"),
                amount = Amount(value = "1.01"),
                idempotencyKey = string()
            )
        }
    }
}
