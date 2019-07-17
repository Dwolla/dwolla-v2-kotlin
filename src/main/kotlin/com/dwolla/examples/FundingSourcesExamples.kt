package com.dwolla.examples

import com.dwolla.api.fundingsources.BankAccountType

class FundingSourcesExamples : Examples() {
    override val scope = "dwolla.fundingSources"

    override fun run() {
        val customer = dwolla.customers.createUnverified("first", "last", "${rand()}@test.com")

        val fundingSource =
        example("createForCustomer") {
            dwolla.fundingSources.createForCustomer(
                customerId = customer.id,
                routingNumber = "222222226",
                accountNumber = "12345678910",
                bankAccountType = BankAccountType.CHECKING,
                name = rand()
            )
        }

        example("listByCustomer") {
            dwolla.fundingSources.listByCustomer(customer.id)
        }

        example("update") {
            dwolla.fundingSources.update(fundingSource.id, name = rand())
        }

        example("get") {
            dwolla.fundingSources.get(fundingSource.id)
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
    }
}

fun main() {
    FundingSourcesExamples().run()
}
