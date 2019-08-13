package com.dwolla.examples

import com.dwolla.api.fundingsources.BankAccountType
import com.dwolla.resource.fundingsources.FundingSourceType
import com.dwolla.shared.DateOfBirth
import com.dwolla.shared.State

fun main() {
    FundingSourcesExamples().run()
}

class FundingSourcesExamples : Examples() {
    override val scope = "dwolla.fundingSources"

    override fun run() {
        val customer = dwolla.customers.createVerifiedPersonal(
            firstName = "first",
            lastName = "last",
            email = "${rand()}@test.com",
            address1 = "123 main st",
            city = "des moines",
            dateOfBirth = DateOfBirth("1990", "09", "12"),
            postalCode = "50309",
            ssn = "1234",
            state = State.IA
        )

        val fundingSource =
        example("createForCustomer") {
            dwolla.fundingSources.createForCustomer(
                customerId = customer.id,
                routingNumber = "222222226",
                accountNumber = "12345678910",
                bankAccountType = BankAccountType.CHECKING,
                name = rand(),
                verified = false
            )
        }

        val balance =
        example("listByCustomer") {
            dwolla.fundingSources.listByCustomer(customer.id)
        }._embedded.fundingSources.find { fs -> fs.type == FundingSourceType.BALANCE }

        example("update") {
            dwolla.fundingSources.update(fundingSource.id, name = rand())
        }

        example("get") {
            dwolla.fundingSources.get(fundingSource.id)
        }

        example("getBalance") {
            dwolla.fundingSources.getBalance(balance!!.id)
        }

        example("initiateMicroDeposits") {
            dwolla.fundingSources.initiateMicroDeposits(fundingSource.id)
        }

        example("getMicroDeposits") {
            dwolla.fundingSources.getMicroDeposits(fundingSource.id)
        }

        example("verifyMicroDeposits") {
            dwolla.fundingSources.verifyMicroDeposits(fundingSource.id, "0.01", "0.02")
        }

        example("remove") {
            dwolla.fundingSources.remove(fundingSource.id)
        }
    }
}
