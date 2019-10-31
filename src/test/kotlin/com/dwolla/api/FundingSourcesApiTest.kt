package com.dwolla.api

import com.dwolla.Instances.dwolla
import com.dwolla.api.fundingsources.BankAccountType
import com.dwolla.api.shared.DateOfBirth
import com.dwolla.resource.fundingsources.FundingSource
import com.dwolla.resource.fundingsources.FundingSourceType
import com.dwolla.shared.USState
import kotlin.test.Test

class FundingSourcesApiTest : ApiTest() {

    private val customer = dwolla.customers.createVerifiedPersonal(
        firstName = string(),
        lastName = string(),
        email = email(),
        address1 = "123 main st",
        city = "des moines",
        dateOfBirth = DateOfBirth("1990", "09", "12"),
        postalCode = "50309",
        ssn = "1234",
        state = USState.IA
    )

    @Test
    fun testCreateForCustomer() {
        assertResponse { createForCustomer() }
    }

    @Test
    fun testListByCustomer() {
        assertResponse { dwolla.fundingSources.listByCustomer(customer.id) }
    }

    @Test
    fun testUpdate() {
        val fs = createForCustomer()

        assertResponse { dwolla.fundingSources.update(fs.id, name = string()) }
    }

    @Test
    fun testGet() {
        val fs = createForCustomer()

        assertResponse { dwolla.fundingSources.get(fs.id) }
    }

    @Test
    fun testGetBalance() {
        val balance = dwolla.fundingSources.listByCustomer(customer.id)
            ._embedded.fundingSources.find { fs -> fs.type == FundingSourceType.BALANCE }!!

        assertResponse { dwolla.fundingSources.getBalance(balance.id) }
    }

    @Test
    fun testInitiateMicroDeposits() {
        val fs = createForCustomer()

        assertResponse { dwolla.fundingSources.initiateMicroDeposits(fs.id) }
    }

    @Test
    fun testGetMicroDeposits() {
        val fs = createForCustomer()
        dwolla.fundingSources.initiateMicroDeposits(fs.id)

        assertResponse { dwolla.fundingSources.getMicroDeposits(fs.id) }
    }

    @Test
    fun testVerifyMicroDeposits() {
        val fs = createForCustomer()
        dwolla.fundingSources.initiateMicroDeposits(fs.id)

        assertResponse {
            dwolla.fundingSources.verifyMicroDeposits(
                id = fs.id,
                amount1 = "0.02",
                amount2 = "0.04"
            )
        }
    }

    @Test
    fun testRemove() {
        val fs = createForCustomer()

        assertResponse { dwolla.fundingSources.remove(fs.id) }
    }

    private fun createForCustomer(): FundingSource {
        return dwolla.fundingSources.createForCustomer(
            customerId = customer.id,
            routingNumber = "222222226",
            accountNumber = "12345678910",
            bankAccountType = BankAccountType.CHECKING,
            name = string(),
            verified = false
        )
    }
}
