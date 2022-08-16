package com.dwolla.api

import com.dwolla.DwollaClient
import com.dwolla.api.customers.BusinessType
import com.dwolla.api.customers.Controller
import com.dwolla.api.customers.CustomerStatus
import com.dwolla.api.shared.DateOfBirth
import com.dwolla.exception.DwollaApiException
import com.dwolla.exception.DwollaAuthException
import com.dwolla.http.Headers
import com.dwolla.http.JsonBody
import com.dwolla.http.Query
import com.dwolla.resource.customers.Customer
import com.dwolla.resource.customers.Customers
import com.dwolla.shared.USState
import com.dwolla.util.Headers.Companion.IDEMPOTENCY_KEY
import com.dwolla.util.Paths.Companion.CUSTOMERS

class CustomersApi(private val client: DwollaClient) {

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun get(id: String): Customer {
        return client.get(Customer::class.java, customerUrl(id)).body
    }

    @JvmOverloads
    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun list(
        limit: Long? = null,
        offset: Long? = null,
        search: String? = null,
        status: CustomerStatus? = null
    ): Customers {

        return client.get(Customers::class.java, CUSTOMERS, Query(
            "limit" to limit,
            "offset" to offset,
            "search" to search,
            "status" to status?.value
        )).body
    }

    @JvmOverloads
    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun createReceiveOnly(
        firstName: String,
        lastName: String,
        email: String,
        businessName: String? = null,
        ipAddress: String? = null,
        idempotencyKey: String? = null,
        correlationId: String? = null
    ): Customer {

        return createCustomer(JsonBody(
            "firstName" to firstName,
            "lastName" to lastName,
            "email" to email,
            "type" to "receive-only",
            "businessName" to businessName,
            "ipAddress" to ipAddress,
            "correlationId" to correlationId
        ), idempotencyKey)
    }

    @JvmOverloads
    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun createUnverified(
        firstName: String,
        lastName: String,
        email: String,
        businessName: String? = null,
        ipAddress: String? = null,
        idempotencyKey: String? = null,
        correlationId: String? = null
    ): Customer {

        return createCustomer(JsonBody(
            "firstName" to firstName,
            "lastName" to lastName,
            "email" to email,
            "businessName" to businessName,
            "ipAddress" to ipAddress,
            "correlationId" to correlationId
        ), idempotencyKey)
    }

    @JvmOverloads
    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun createVerifiedPersonal(
        firstName: String,
        lastName: String,
        email: String,
        address1: String,
        address2: String? = null,
        city: String,
        state: USState,
        postalCode: String,
        dateOfBirth: DateOfBirth,
        ssn: String,
        phone: String? = null,
        ipAddress: String? = null,
        idempotencyKey: String? = null,
        correlationId: String? = null
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
            "ipAddress" to ipAddress,
            "correlationId" to correlationId
        ), idempotencyKey)
    }

    @JvmOverloads
    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun createVerifiedSoleProp(
        firstName: String,
        lastName: String,
        email: String,
        dateOfBirth: DateOfBirth,
        ssn: String,
        address1: String,
        address2: String? = null,
        city: String,
        state: USState,
        postalCode: String,
        businessName: String,
        businessClassification: String,
        website: String? = null,
        phone: String? = null,
        ein: String? = null,
        doingBusinessAs: String? = null,
        ipAddress: String? = null,
        idempotencyKey: String? = null,
        correlationId: String? = null
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
            "businessType" to BusinessType.SOLE_PROPRIETORSHIP,
            "businessClassification" to businessClassification,
            "website" to website,
            "phone" to phone,
            "ein" to ein,
            "doingBusinessAs" to doingBusinessAs,
            "ipAddress" to ipAddress,
            "correlationId" to correlationId
        ), idempotencyKey)
    }

    @JvmOverloads
    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun createVerifiedBusiness(
        firstName: String,
        lastName: String,
        email: String,
        address1: String,
        address2: String? = null,
        city: String,
        state: USState,
        postalCode: String,
        businessName: String,
        businessType: BusinessType,
        businessClassification: String,
        ein: String,
        controller: Controller,
        website: String? = null,
        phone: String? = null,
        doingBusinessAs: String? = null,
        ipAddress: String? = null,
        idempotencyKey: String? = null,
        correlationId: String? = null
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
            "controller" to controller,
            "website" to website,
            "phone" to phone,
            "doingBusinessAs" to doingBusinessAs,
            "ipAddress" to ipAddress,
            "correlationId" to correlationId
        ), idempotencyKey)
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
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

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun updateVerifiedPersonal(
        id: String,
        email: String? = null,
        ipAddress: String? = null,
        address1: String? = null,
        address2: String? = null,
        city: String? = null,
        state: USState? = null,
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

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun updateVerifiedBusiness(
        id: String,
        email: String? = null,
        ipAddress: String? = null,
        address1: String? = null,
        address2: String? = null,
        city: String? = null,
        state: USState? = null,
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

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun upgradeToVerifiedPersonal(
        id: String,
        firstName: String,
        lastName: String,
        address1: String,
        address2: String? = null,
        city: String,
        state: USState,
        postalCode: String,
        email: String? = null,
        phone: String? = null,
        ipAddress: String? = null,
        ssn: String,
        dateOfBirth: DateOfBirth
    ): Customer {

        return client.post(Customer::class.java, customerUrl(id), JsonBody(
            "firstName" to firstName,
            "lastName" to lastName,
            "email" to email,
            "address1" to address1,
            "address2" to address2,
            "city" to city,
            "state" to state,
            "postalCode" to postalCode,
            "phone" to phone,
            "ipAddress" to ipAddress,
            "ssn" to ssn,
            "dateOfBirth" to dateOfBirth
        )).body
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun suspend(id: String): Customer {
        return client.post(Customer::class.java, customerUrl(id), JsonBody("status" to "suspended")).body
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun deactivate(id: String): Customer {
        return client.post(Customer::class.java, customerUrl(id), JsonBody("status" to "deactivated")).body
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun reactivate(id: String): Customer {
        return client.post(Customer::class.java, customerUrl(id), JsonBody("status" to "reactivated")).body
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun retryVerifiedPersonal(
        id: String,
        firstName: String,
        lastName: String,
        email: String,
        address1: String,
        address2: String? = null,
        city: String,
        state: USState,
        postalCode: String,
        dateOfBirth: DateOfBirth,
        ssn: String,
        phone: String? = null,
        ipAddress: String? = null
    ): Customer {

        return client.post(Customer::class.java, customerUrl(id), JsonBody(
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
        )).body
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun retryVerifiedBusiness(
        id: String,
        email: String,
        address1: String,
        address2: String? = null,
        city: String,
        state: USState,
        postalCode: String,
        website: String? = null,
        phone: String? = null,
        doingBusinessAs: String? = null,
        ipAddress: String? = null
    ): Customer {

        return client.post(Customer::class.java, customerUrl(id), JsonBody(
            "email" to email,
            "type" to "business",
            "address1" to address1,
            "address2" to address2,
            "city" to city,
            "state" to state,
            "postalCode" to postalCode,
            "website" to website,
            "doingBusinessAs" to doingBusinessAs,
            "phone" to phone,
            "ipAddress" to ipAddress
        )).body
    }

    private fun createCustomer(jsonBody: JsonBody, idempotencyKey: String?): Customer {
        return client.postFollow(
            Customer::class.java,
            CUSTOMERS,
            jsonBody,
            Headers(IDEMPOTENCY_KEY to idempotencyKey)
        ).body
    }

    private fun customerUrl(id: String): String {
        return client.urlBuilder.buildUrl(CUSTOMERS, id)
    }
}
