package com.dwolla.api

import com.dwolla.Instances.dwolla
import com.dwolla.api.customers.BusinessType
import com.dwolla.api.customers.Controller
import com.dwolla.api.documents.DocumentType
import com.dwolla.api.shared.DateOfBirth
import com.dwolla.api.shared.InternationalAddress
import com.dwolla.resource.beneficialowners.BeneficialOwner
import com.dwolla.resource.customers.Customer
import com.dwolla.shared.Country
import com.dwolla.shared.USState
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import java.io.File
import kotlin.test.Test

@Tags(Tag("api"))
class DocumentsApiTest : ApiTest() {

    @Test
    fun testCreateForCustomer() {
        val c = createCustomer(firstName = "document", lastName = "document")

        assertResponse {
            dwolla.documents.createForCustomer(
                customerId = c.id,
                documentType = DocumentType.LICENSE,
                file = File("${System.getProperty("user.dir")}/src/test/kotlin/com/dwolla/api/assets/test-document-upload-success.png")
            )
        }
    }

    @Test
    fun testCreateForBeneficialOwner() {
        val c = createCustomer()
        val bo = createBeneficialOwnerForCustomer(customerId = c.id, firstName = "document", lastName = "document")

        assertResponse {
            dwolla.documents.createForBeneficialOwner(
                beneficialOwnerId = bo.id,
                documentType = DocumentType.LICENSE,
                file = File("${System.getProperty("user.dir")}/src/test/kotlin/com/dwolla/api/assets/test-document-upload-success.png")
            )
        }
    }

    private fun createCustomer(firstName: String = string(), lastName: String = string()): Customer {
        return dwolla.customers.createVerifiedBusiness(
            firstName = firstName,
            lastName = lastName,
            email = email(),
            address1 = "123 main st",
            city = "des moines",
            state = USState.IA,
            postalCode = "50309",
            businessClassification = "9ed3f66c-7d6f-11e3-86ae-5404a6144203",
            businessName = "biz name",
            businessType = BusinessType.LLC,
            controller = Controller(
                firstName = "first",
                lastName = "last",
                title = "ceo",
                dateOfBirth = DateOfBirth("1990", "01", "01"),
                address = InternationalAddress(
                    address1 = "123 main st",
                    city = "des moines",
                    country = Country.US,
                    postalCode = "50309",
                    stateProvinceRegion = "ia"
                ),
                ssn = "1234"
            ),
            ein = "234123123"
        )
    }

    private fun createBeneficialOwnerForCustomer(
        customerId: String,
        firstName: String = string(),
        lastName: String = string()
    ): BeneficialOwner {

        return dwolla.beneficialOwners.createForCustomer(
            customerId = customerId,
            address = InternationalAddress(
                address1 = "123 main st",
                city = "des moines",
                stateProvinceRegion = "IA",
                country = Country.US,
                postalCode = "50309"
            ),
            firstName = firstName,
            lastName = lastName,
            dateOfBirth = DateOfBirth("1990", "01", "01"),
            ssn = "123456789",
            passport = null
        )
    }
}