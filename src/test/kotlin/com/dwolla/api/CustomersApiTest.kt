package com.dwolla.api

import com.dwolla.Instances.dwolla
import com.dwolla.api.customers.BusinessType
import com.dwolla.api.customers.Controller
import com.dwolla.api.shared.DateOfBirth
import com.dwolla.api.shared.InternationalAddress
import com.dwolla.resource.customers.Customer
import com.dwolla.resource.customers.CustomerStatus
import com.dwolla.shared.Country
import com.dwolla.shared.USState
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import kotlin.test.Test
import kotlin.test.assertEquals

@Tags(Tag("api"))
class CustomersApiTest : ApiTest() {

    @Test
    fun testCreateReceiveOnly() {
        assertResponse { createReceiveOnly() }
    }

    @Test
    fun testGet() {
        val id = createReceiveOnly().id
        assertResponse { dwolla.customers.get(id) }
    }

    @Test
    fun testList() {
        assertResponse { dwolla.customers.list(10) }
    }

    @Test
    fun testCreateUnverified() {
        assertResponse { createUnverified() }
    }

    @Test
    fun testCreateVerifiedPersonal() {
        assertResponse { createVerifiedPersonal() }
    }

    @Test
    fun testCreateVerifiedSoleProp() {
        assertResponse { createVerifiedSoleProp() }
    }

    @Test
    fun testCreateVerifiedBusiness() {
        assertResponse { createVerifiedBusiness() }
    }

    @Test
    fun testUpdateUnverified() {
        val unverified = createUnverified()

        assertResponse {
            dwolla.customers.updateUnverified(
                id = unverified.id,
                firstName = string(),
                lastName = string(),
                email = email(),
                businessName = string()
            )
        }
    }

    @Test
    fun testUpdateVerifiedPersonal() {
        val verifiedPersonal = createVerifiedPersonal()

        assertResponse {
            dwolla.customers.updateVerifiedPersonal(
                id = verifiedPersonal.id,
                email = email(),
                address1 = "456 2nd st.",
                city = "chicago",
                state = USState.IL,
                postalCode = "60007",
                phone = "5392038573"
            )
        }
    }

    @Test
    fun testUpdateVerifiedBusiness() {
        val verifiedBusiness = createVerifiedBusiness()

        assertResponse {
            dwolla.customers.updateVerifiedBusiness(
                id = verifiedBusiness.id,
                email = email(),
                address1 = "456 2nd st.",
                city = "chicago",
                state = USState.IL,
                postalCode = "60007",
                phone = "5392038573",
                doingBusinessAs = string(),
                website = "https://verified.biz"
            )
        }
    }

    @Test
    fun testUpgradeToVerifiedPersonal() {
        val unverified = createUnverified()

        assertResponse {
            dwolla.customers.upgradeToVerifiedPersonal(
                id = unverified.id,
                firstName = "Jane",
                lastName = "Doe",
                dateOfBirth = DateOfBirth("1990", "01", "01"),
                address1 = "123 main st.",
                city = "des moines",
                state = USState.IA,
                postalCode = "50309",
                ssn ="1234567890"
            )
        }
    }

    @Test
    fun testDeactivate() {
        val deactivated = assertResponse {
            dwolla.customers.deactivate(createUnverified().id)
        }
        assertEquals(CustomerStatus.DEACTIVATED, deactivated.status)
    }

    @Test
    fun testReactivate() {
        val deactivated = dwolla.customers.deactivate(createUnverified().id)

        val reactivated = assertResponse {
            dwolla.customers.reactivate(deactivated.id)
        }
        assertEquals(CustomerStatus.UNVERIFIED, reactivated.status)
    }

    @Test
    fun testSuspend() {
        val suspended = assertResponse {
            dwolla.customers.suspend(createUnverified().id)
        }
        assertEquals(CustomerStatus.SUSPENDED, suspended.status)
    }

    @Test
    fun testRetryVerifiedPersonal() {
        val retryPersonal =
            dwolla.customers.createVerifiedPersonal(
                firstName = "retry",
                lastName = "retry",
                email = email(),
                address1 = "123 main st",
                city = "des moines",
                state = USState.IA,
                dateOfBirth = DateOfBirth("1990", "01", "01"),
                postalCode = "50309",
                ssn = "1234"
            )

        assertResponse {
            dwolla.customers.retryVerifiedPersonal(
                id = retryPersonal.id,
                firstName = string(),
                lastName = string(),
                email = email(),
                address1 = "123 main st",
                city = "des moines",
                state = USState.IA,
                dateOfBirth = DateOfBirth("1990", "01", "01"),
                postalCode = "50309",
                ssn = "123456789"
            )
        }
    }

    @Test
    fun testRetryVerifiedBusiness() {
        val retryBusiness =
            dwolla.customers.createVerifiedBusiness(
                firstName = "retry",
                lastName = "retry",
                email = email(),
                address1 = "123 main st",
                city = "des moines",
                state = USState.IA,
                postalCode = "50309",
                businessClassification = businessClassification(),
                businessName = string(),
                businessType = BusinessType.LLC,
                ein = "123531243",
                controller = Controller(
                    firstName = string(),
                    lastName = string(),
                    title = string(),
                    dateOfBirth = DateOfBirth("1990", "01", "01"),
                    address = InternationalAddress(
                        address1 = "123 main st",
                        city = "des moines",
                        stateProvinceRegion = "IA",
                        country = Country.US,
                        postalCode = "50309"
                    ),
                    ssn = "1234"
                )
            )

        assertResponse {
            dwolla.customers.retryVerifiedBusiness(
                id = retryBusiness.id,
                email = email(),
                address1 = "123 main st",
                city = "des moines",
                state = USState.IA,
                postalCode = "50309"
            )
        }
    }

    private fun createVerifiedBusiness(): Customer {
        return dwolla.customers.createVerifiedBusiness(
            firstName = string(),
            lastName = string(),
            email = email(),
            address1 = "123 main st",
            city = "des moines",
            state = USState.IA,
            postalCode = "50309",
            businessClassification = businessClassification(),
            businessName = string(),
            businessType = BusinessType.LLC,
            ein = "123531243",
            controller = Controller(
                firstName = string(),
                lastName = string(),
                title = string(),
                dateOfBirth = DateOfBirth("1990", "01", "01"),
                address = InternationalAddress(
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

    private fun createVerifiedSoleProp(): Customer {
        return dwolla.customers.createVerifiedSoleProp(
            firstName = string(),
            lastName = string(),
            email = email(),
            address1 = "123 main st",
            city = "des moines",
            state = USState.IA,
            dateOfBirth = DateOfBirth("1990", "01", "01"),
            postalCode = "50309",
            ssn = "1234",
            businessClassification = businessClassification(),
            businessName = string()
        )
    }

    private fun createVerifiedPersonal(): Customer {
        return dwolla.customers.createVerifiedPersonal(
            firstName = string(),
            lastName = string(),
            email = email(),
            address1 = "123 main st",
            city = "des moines",
            state = USState.IA,
            dateOfBirth = DateOfBirth("1990", "01", "01"),
            postalCode = "50309",
            ssn = "1234"
        )
    }

    private fun createUnverified(): Customer {
        return dwolla.customers.createUnverified(
            firstName = string(),
            lastName = string(),
            email = email()
        )
    }

    private fun createReceiveOnly(): Customer {
        return dwolla.customers.createReceiveOnly(
            firstName = string(),
            lastName = string(),
            email = email()
        )
    }
}
