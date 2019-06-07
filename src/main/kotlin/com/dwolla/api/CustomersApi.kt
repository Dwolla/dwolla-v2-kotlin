package com.dwolla.api

import com.dwolla.Client
import com.dwolla.api.customers.CustomerBusinessType
import com.dwolla.api.customers.CustomerController
import com.dwolla.api.customers.CustomerStatus
import com.dwolla.exception.DwollaException
import com.dwolla.exception.OAuthException
import com.dwolla.http.Headers
import com.dwolla.http.JsonBody
import com.dwolla.http.Query
import com.dwolla.resource.customers.Customer
import com.dwolla.resource.customers.Customers

class CustomersApi(private val client: Client) {

    @Throws(DwollaException::class, OAuthException::class)
    fun getById(id: String): Customer {
        return client.get(Customer::class.java, customerUrl(id)).body
    }

    @Throws(DwollaException::class, OAuthException::class)
    fun list(limit: Long? = null, offset: Long? = null, search: String? = null, status: CustomerStatus? = null): Customers {
        return client.get(Customers::class.java, "customers", Query(
                "limit" to limit,
                "offset" to offset,
                "search" to search,
                "status" to status?.value
        )).body
    }

    @Throws(DwollaException::class, OAuthException::class)
    fun createReceiveOnly(
        firstName: String,
        lastName: String,
        email: String,
        businessName: String? = null,
        ipAddress: String? = null,
        idempotencyKey: String? = null
    ): Customer {

        return createCustomer(JsonBody(
                "firstName" to firstName,
                "lastName" to lastName,
                "email" to email,
                "type" to "receive-only",
                "businessName" to businessName,
                "ipAddress" to ipAddress
        ), idempotencyKey)
    }

    @Throws(DwollaException::class, OAuthException::class)
    fun createUnverified(
        firstName: String,
        lastName: String,
        email: String,
        businessName: String? = null,
        ipAddress: String? = null,
        idempotencyKey: String? = null
    ): Customer {

        return createCustomer(JsonBody(
                "firstName" to firstName,
                "lastName" to lastName,
                "email" to email,
                "businessName" to businessName,
                "ipAddress" to ipAddress
        ), idempotencyKey)
    }

    @Throws(DwollaException::class, OAuthException::class)
    fun createVerifiedPersonal(
        firstName: String,
        lastName: String,
        email: String,
        address1: String,
        address2: String? = null,
        city: String,
        state: String,
        postalCode: String,
        dateOfBirth: String,
        ssn: String,
        phone: String? = null,
        ipAddress: String? = null,
        idempotencyKey: String? = null
    ): Customer {

        return createCustomer(JsonBody(
                "firstName" to firstName,
                "lastName" to lastName,
                "email" to email,
                "type" to "personal",
                "address1" to address1,
                "address2" to address2,
                "city" to city,
                "state" to state,
                "postalCode" to postalCode,
                "dateOfBirth" to dateOfBirth,
                "ssn" to ssn,
                "phone" to phone,
                "ipAddress" to ipAddress
        ), idempotencyKey)
    }

    @Throws(DwollaException::class, OAuthException::class)
    fun createVerifiedSoleProp(
        firstName: String,
        lastName: String,
        email: String,
        dateOfBirth: String,
        ssn: String,
        address1: String,
        address2: String? = null,
        city: String,
        state: String,
        postalCode: String,
        businessName: String,
        businessClassification: String,
        website: String? = null,
        phone: String? = null,
        ein: String? = null,
        doingBusinessAs: String? = null,
        ipAddress: String? = null,
        idempotencyKey: String? = null
    ): Customer {

        return createCustomer(JsonBody(
                "firstName" to firstName,
                "lastName" to lastName,
                "email" to email,
                "type" to "business",
                "dateOfBirth" to dateOfBirth,
                "ssn" to ssn,
                "address1" to address1,
                "address2" to address2,
                "city" to city,
                "state" to state,
                "postalCode" to postalCode,
                "businessName" to businessName,
                "businessType" to "soleProprietorship",
                "businessClassification" to businessClassification,
                "website" to website,
                "phone" to phone,
                "ein" to ein,
                "doingBusinessAs" to doingBusinessAs,
                "ipAddress" to ipAddress
        ), idempotencyKey)
    }

    @Throws(DwollaException::class, OAuthException::class)
    fun createVerifiedBusiness(
            firstName: String,
            lastName: String,
            email: String,
            address1: String,
            address2: String? = null,
            city: String,
            state: String,
            postalCode: String,
            businessName: String,
            businessType: CustomerBusinessType,
            businessClassification: String,
            ein: String,
            controller: CustomerController,
            website: String? = null,
            phone: String? = null,
            doingBusinessAs: String? = null,
            ipAddress: String? = null,
            idempotencyKey: String? = null
    ): Customer {

        return createCustomer(JsonBody(
                "firstName" to firstName,
                "lastName" to lastName,
                "email" to email,
                "type" to "business",
                "address1" to address1,
                "address2" to address2,
                "city" to city,
                "state" to state,
                "postalCode" to postalCode,
                "businessName" to businessName,
                "businessType" to businessType,
                "businessClassification" to businessClassification,
                "ein" to ein,
                "controller" to listOf(
                        "firstName" to controller.firstName,
                        "lastName" to controller.lastName,
                        "dateOfBirth" to controller.dateOfBirth,
                        "address" to listOf(
                                "address1" to controller.address.address1,
                                "address2" to controller.address.address2,
                                "address3" to controller.address.address3,
                                "city" to controller.address.city,
                                "stateProvinceRegion" to controller.address.stateProvinceRegion,
                                "postalCode" to controller.address.postalCode,
                                "country" to controller.address.country
                        ),
                        "passport" to listOf(
                                "number" to controller.passport?.number,
                                "country" to controller.passport?.country
                        )
                ),
                "website" to website,
                "phone" to phone,
                "doingBusinessAs" to doingBusinessAs,
                "ipAddress" to ipAddress
        ), idempotencyKey)
    }

    @Throws(DwollaException::class, OAuthException::class)
    fun updateUnverified(
        id: String,
        firstName: String? = null,
        lastName: String? = null,
        email: String? = null,
        businessName: String? = null
    ): Customer {

        return client.post(Customer::class.java, customerUrl(id), JsonBody(
                "firstName" to firstName,
                "lastName" to lastName,
                "email" to email,
                "businessName" to businessName
        )).body
    }

    @Throws(DwollaException::class, OAuthException::class)
    fun updateVerified(
        id: String,
        email: String? = null,
        ipAddress: String? = null,
        address1: String? = null,
        address2: String? = null,
        city: String? = null,
        state: String? = null,
        postalCode: String? = null,
        phone: String? = null
    ): Customer {

        return client.post(Customer::class.java, customerUrl(id), JsonBody(
                "email" to email,
                "ipAddress" to ipAddress,
                "address1" to address1,
                "address2" to address2,
                "city" to city,
                "state" to state,
                "postalCode" to postalCode,
                "phone" to phone
        )).body
    }

    @Throws(DwollaException::class, OAuthException::class)
    fun updateVerifiedBusiness(
        id: String,
        email: String? = null,
        ipAddress: String? = null,
        address1: String? = null,
        address2: String? = null,
        city: String? = null,
        state: String? = null,
        postalCode: String? = null,
        phone: String? = null,
        doingBusinessAs: String? = null,
        website: String? = null
    ): Customer {

        return client.post(Customer::class.java, customerUrl(id), JsonBody(
                "email" to email,
                "ipAddress" to ipAddress,
                "address1" to address1,
                "address2" to address2,
                "city" to city,
                "state" to state,
                "postalCode" to postalCode,
                "phone" to phone,
                "doingBusinessAs" to doingBusinessAs,
                "website" to website
        )).body
    }

    @Throws(DwollaException::class, OAuthException::class)
    fun upgradeToVerifiedPersonal(
        id: String,
        email: String,
        address1: String,
        address2: String? = null,
        city: String,
        state: String,
        phone: String? = null,
        ipAddress: String? = null
    ): Customer {

        return client.post(Customer::class.java, customerUrl(id), JsonBody(
                "email" to email,
                "address1" to address1,
                "address2" to address2,
                "city" to city,
                "state" to state,
                "phone" to phone,
                "ipAddress" to ipAddress
        )).body
    }

    @Throws(DwollaException::class, OAuthException::class)
    fun suspend(id: String): Customer {
        return client.post(Customer::class.java, customerUrl(id), JsonBody("status" to "suspended")).body
    }

    @Throws(DwollaException::class, OAuthException::class)
    fun deactivate(id: String): Customer {
        return client.post(Customer::class.java, customerUrl(id), JsonBody("status" to "deactivated")).body
    }

    @Throws(DwollaException::class, OAuthException::class)
    fun reactivate(id: String): Customer {
        return client.post(Customer::class.java, customerUrl(id), JsonBody("status" to "reactivated")).body
    }

    @Throws(DwollaException::class, OAuthException::class)
    fun retryVerified(
        id: String,
        firstName: String,
        lastName: String,
        email: String,
        type: String,
        address1: String,
        address2: String? = null,
        city: String,
        state: String,
        postalCode: String,
        dateOfBirth: String,
        fullSsn: String,
        phone: String? = null,
        ipAddress: String? = null
    ): Customer {

        return client.post(Customer::class.java, customerUrl(id), JsonBody(
                "firstName" to firstName,
                "lastName" to lastName,
                "email" to email,
                "type" to type,
                "address1" to address1,
                "address2" to address2,
                "city" to city,
                "state" to state,
                "postalCode" to postalCode,
                "dateOfBirth" to dateOfBirth,
                "ssn" to fullSsn,
                "phone" to phone,
                "ipAddress" to ipAddress
        )).body
    }

    private fun createCustomer(jsonBody: JsonBody, idempotencyKey: String?): Customer {
        val createCustomer = client.post("customers", jsonBody, Headers("idempotency-key" to idempotencyKey))
        val url = createCustomer.headers.get("location")!!
        val getCustomer = client.get(Customer::class.java, url)
        return getCustomer.body
    }

    private fun customerUrl(id: String): String {
        return client.urlBuilder.buildUrl("customers", id)
    }
}
