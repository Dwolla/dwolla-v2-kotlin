package com.dwolla.api

import com.dwolla.Instances.dwolla
import com.dwolla.api.customers.BusinessType
import com.dwolla.api.customers.Controller
import com.dwolla.api.shared.DateOfBirth
import com.dwolla.api.shared.InternationalAddress
import com.dwolla.resource.beneficialowners.BeneficialOwner
import com.dwolla.resource.customers.Customer
import com.dwolla.shared.Country
import com.dwolla.shared.USState
import kotlin.test.Test

class BeneficialOwnersApiTest : ApiTest() {

    private val c = createVerifiedBusiness()

    @Test
    fun testCreateForCustomer() {
        assertResponse {
            createForCustomer()
        }
    }

    @Test
    fun testGet() {
        val bo = createForCustomer()

        assertResponse { dwolla.beneficialOwners.get(bo.id) }
    }

    @Test
    fun testListByCustomer() {
        createForCustomer()

        assertResponse { dwolla.beneficialOwners.listByCustomer(c.id) }
    }

    private fun createForCustomer(): BeneficialOwner {
        return dwolla.beneficialOwners.createForCustomer(
            customerId = c.id,
            address = InternationalAddress(
                address1 = "123 main st",
                city = "des moines",
                stateProvinceRegion = "IA",
                country = Country.US,
                postalCode = "50309"
            ),
            firstName = string(),
            lastName = string(),
            dateOfBirth = DateOfBirth("1990", "01", "01"),
            ssn = "123456789",
            passport = null
        )
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
}
