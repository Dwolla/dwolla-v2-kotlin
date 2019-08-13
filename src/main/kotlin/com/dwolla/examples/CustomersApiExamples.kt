package com.dwolla.examples

import com.dwolla.api.customers.BusinessType
import com.dwolla.api.customers.Controller
import com.dwolla.api.customers.ControllerAddress
import com.dwolla.shared.Country
import com.dwolla.shared.DateOfBirth
import com.dwolla.shared.State

fun main() {
    CustomersExamples().run()
}

class CustomersExamples : Examples() {
    override val scope = "dwolla.customers"

    override fun run() {
        val businessClassification = dwolla.businessClassifications.list()._embedded.businessClassifications.first()._embedded.industryClassifications.first().id

        example("createReceiveOnly") {
            dwolla.customers.createReceiveOnly(
                firstName = rand(),
                lastName = rand(),
                email = "${rand()}@test.com"
            )
        }

        val customerId = example("list") {
            dwolla.customers.list(limit = 1)
        }._embedded.customers.first().id

        val customer =
        example("get") {
            dwolla.customers.get(customerId)
        }

        val unverified =
        example("createUnverified") {
            dwolla.customers.createUnverified(
                firstName = rand(),
                lastName = rand(),
                email = "${rand()}@test.com"
            )
        }

        val verifiedPersonal =
        example("createVerifiedPersonal") {
            dwolla.customers.createVerifiedPersonal(
                firstName = rand(),
                lastName = rand(),
                email = "${rand()}@test.com",
                address1 = "123 main st",
                city = "des moines",
                state = State.IA,
                dateOfBirth = DateOfBirth("1990", "01", "01"),
                postalCode = "50309",
                ssn = "1234"
            )
        }

        example("createVerifiedSoleProp") {
            dwolla.customers.createVerifiedSoleProp(
                firstName = rand(),
                lastName = rand(),
                email = "${rand()}@test.com",
                address1 = "123 main st",
                city = "des moines",
                state = State.IA,
                dateOfBirth = DateOfBirth("1990", "01", "01"),
                postalCode = "50309",
                ssn = "1234",
                businessClassification = businessClassification,
                businessName = rand()
            )
        }

        val verifiedBusiness =
        example("createVerifiedBusiness") {
            dwolla.customers.createVerifiedBusiness(
                firstName = rand(),
                lastName = rand(),
                email = "${rand()}@test.com",
                address1 = "123 main st",
                city = "des moines",
                state = State.IA,
                postalCode = "50309",
                businessClassification = businessClassification,
                businessName = rand(),
                businessType = BusinessType.LLC,
                ein = "123531243",
                controller = Controller(
                    firstName = rand(),
                    lastName = rand(),
                    title = rand(),
                    dateOfBirth = DateOfBirth("1990", "01", "01"),
                    address = ControllerAddress(
                        address1 = "123 main st",
                        city = "des moines",
                        stateProvinceRegion = "IA",
                        country = Country.US,
                        postalCode = "50309"
                    ),
                    ssn = "1234"
                )
            )
        }

        example("updateUnverified") {
            dwolla.customers.updateUnverified(
                id = unverified.id,
                firstName = rand(),
                lastName = rand(),
                email = "${rand()}@test.com",
                businessName = rand()
            )
        }

        example("updateVerifiedPersonal") {
            dwolla.customers.updateVerifiedPersonal(
                id = verifiedPersonal.id,
                email = "${rand()}@test.com",
                address1 = "456 2nd st.",
                city = "chicago",
                state = State.IL,
                postalCode = "60007",
                phone = "5392038573"
            )
        }

        example("updateVerifiedBusiness") {
            dwolla.customers.updateVerifiedBusiness(
                id = verifiedBusiness.id,
                email = "${rand()}@test.com",
                address1 = "456 2nd st.",
                city = "chicago",
                state = State.IL,
                postalCode = "60007",
                phone = "5392038573",
                doingBusinessAs = rand(),
                website = "https://verified.biz"
            )
        }

        example("upgradeToVerifiedPersonal") {
            dwolla.customers.upgradeToVerifiedPersonal(
                id = unverified.id,
                address1 = "123 main st.",
                city = "des moines",
                state = State.IA,
                postalCode = "50309"
            )
        }

        example("deactivate") {
            dwolla.customers.deactivate(customer.id)
        }

        example("reactivate") {
            dwolla.customers.reactivate(customer.id)
        }

        example("suspend") {
            dwolla.customers.suspend(customer.id)
        }

        val personalInRetry = dwolla.customers.createVerifiedPersonal(
            firstName = "retry",
            lastName = "retry",
            email = "${rand()}@test.com",
            address1 = "123 main st",
            city = "des moines",
            state = State.IA,
            dateOfBirth = DateOfBirth("1990", "01", "01"),
            postalCode = "50309",
            ssn = "1234"
        )
        example("retryVerifiedPersonal") {
            dwolla.customers.retryVerifiedPersonal(
                id = personalInRetry.id,
                firstName = rand(),
                lastName = rand(),
                email = "${rand()}@test.com",
                address1 = "123 main st",
                city = "des moines",
                state = State.IA,
                dateOfBirth = DateOfBirth("1990", "01", "01"),
                postalCode = "50309",
                ssn = "123456789"
            )
        }

        val businessInRetry =
        dwolla.customers.createVerifiedBusiness(
            firstName = "retry",
            lastName = "retry",
            email = "${rand()}@test.com",
            address1 = "123 main st",
            city = "des moines",
            state = State.IA,
            postalCode = "50309",
            businessClassification = businessClassification,
            businessName = rand(),
            businessType = BusinessType.LLC,
            ein = "123531243",
            controller = Controller(
                firstName = rand(),
                lastName = rand(),
                title = rand(),
                dateOfBirth = DateOfBirth("1990", "01", "01"),
                address = ControllerAddress(
                    address1 = "123 main st",
                    city = "des moines",
                    stateProvinceRegion = "IA",
                    country = Country.US,
                    postalCode = "50309"
                ),
                ssn = "1234"
            )
        )
        example("retryVerifiedBusiness") {
            dwolla.customers.retryVerifiedBusiness(
                id = businessInRetry.id,
                email = "${rand()}@test.com",
                address1 = "123 main st",
                city = "des moines",
                state = State.IA,
                postalCode = "50309"
            )
        }

        example("createIavToken") {
            val c = dwolla.customers.createReceiveOnly(
                firstName = rand(),
                lastName = rand(),
                email = "${rand()}@test.com"
            )
            dwolla.customers.createIavToken(c.id)
        }

        example("createFundingSourcesToken") {
            val c = dwolla.customers.createReceiveOnly(
                firstName = rand(),
                lastName = rand(),
                email = "${rand()}@test.com"
            )
            dwolla.customers.createFundingSourcesToken(c.id)
        }
    }
}
