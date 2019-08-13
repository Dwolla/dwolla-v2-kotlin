package com.dwolla.api

import com.dwolla.Dwolla
import com.dwolla.api.customers.BusinessType
import com.dwolla.api.customers.Controller
import com.dwolla.api.customers.CustomerStatus
import com.dwolla.exception.DwollaException
import com.dwolla.exception.OAuthException
import com.dwolla.http.Headers
import com.dwolla.http.JsonBody
import com.dwolla.http.Query
import com.dwolla.resource.customers.Customer
import com.dwolla.resource.customers.Customers
import com.dwolla.resource.customers.FundingSourcesToken
import com.dwolla.resource.customers.IavToken
import com.dwolla.shared.DateOfBirth
import com.dwolla.shared.State
import com.dwolla.util.Headers.Companion.IDEMPOTENCY_KEY
import com.dwolla.util.Paths.Companion.CUSTOMERS
import com.dwolla.util.Paths.Companion.FUNDING_SOURCES_TOKEN
import com.dwolla.util.Paths.Companion.IAV_TOKEN

class CustomersApi(private val dwolla: Dwolla) {

    @Throws(DwollaException::class, OAuthException::class)
    fun get(id: String): Customer {
        return dwolla.get(Customer::class.java, customerUrl(id)).body
    }

    @Throws(DwollaException::class, OAuthException::class)
    fun list(
        limit: Long? = null,
        offset: Long? = null,
        search: String? = null,
        status: CustomerStatus? = null
    ): Customers {

        return dwolla.get(Customers::class.java, CUSTOMERS, Query(
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
        state: State,
        postalCode: String,
        dateOfBirth: DateOfBirth,
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
        dateOfBirth: DateOfBirth,
        ssn: String,
        address1: String,
        address2: String? = null,
        city: String,
        state: State,
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
        state: State,
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
            "controller" to controller,
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

        return dwolla.post(Customer::class.java, customerUrl(id), JsonBody(
            "firstName" to firstName,
            "lastName" to lastName,
            "email" to email,
            "businessName" to businessName
        )).body
    }

    @Throws(DwollaException::class, OAuthException::class)
    fun updateVerifiedPersonal(
        id: String,
        email: String? = null,
        ipAddress: String? = null,
        address1: String? = null,
        address2: String? = null,
        city: String? = null,
        state: State? = null,
        postalCode: String? = null,
        phone: String? = null
    ): Customer {

        return dwolla.post(Customer::class.java, customerUrl(id), JsonBody(
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
        state: State? = null,
        postalCode: String? = null,
        phone: String? = null,
        doingBusinessAs: String? = null,
        website: String? = null
    ): Customer {

        return dwolla.post(Customer::class.java, customerUrl(id), JsonBody(
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
        address1: String,
        address2: String? = null,
        city: String,
        state: State,
        postalCode: String,
        email: String? = null,
        phone: String? = null,
        ipAddress: String? = null
    ): Customer {

        return dwolla.post(Customer::class.java, customerUrl(id), JsonBody(
            "email" to email,
            "address1" to address1,
            "address2" to address2,
            "city" to city,
            "state" to state,
            "postalCode" to postalCode,
            "phone" to phone,
            "ipAddress" to ipAddress
        )).body
    }

    @Throws(DwollaException::class, OAuthException::class)
    fun suspend(id: String): Customer {
        return dwolla.post(Customer::class.java, customerUrl(id), JsonBody("status" to "suspended")).body
    }

    @Throws(DwollaException::class, OAuthException::class)
    fun deactivate(id: String): Customer {
        return dwolla.post(Customer::class.java, customerUrl(id), JsonBody("status" to "deactivated")).body
    }

    @Throws(DwollaException::class, OAuthException::class)
    fun reactivate(id: String): Customer {
        return dwolla.post(Customer::class.java, customerUrl(id), JsonBody("status" to "reactivated")).body
    }

    @Throws(DwollaException::class, OAuthException::class)
    fun retryVerifiedPersonal(
        id: String,
        firstName: String,
        lastName: String,
        email: String,
        address1: String,
        address2: String? = null,
        city: String,
        state: State,
        postalCode: String,
        dateOfBirth: DateOfBirth,
        ssn: String,
        phone: String? = null,
        ipAddress: String? = null
    ): Customer {

        return dwolla.post(Customer::class.java, customerUrl(id), JsonBody(
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

    @Throws(DwollaException::class, OAuthException::class)
    fun retryVerifiedBusiness(
        id: String,
        email: String,
        address1: String,
        address2: String? = null,
        city: String,
        state: State,
        postalCode: String,
        website: String? = null,
        phone: String? = null,
        doingBusinessAs: String? = null,
        ipAddress: String? = null
    ): Customer {

        return dwolla.post(Customer::class.java, customerUrl(id), JsonBody(
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

    @Throws(DwollaException::class, OAuthException::class)
    fun createIavToken(id: String): IavToken {
        return dwolla.post(IavToken::class.java, iavTokenUrl(id)).body
    }

    @Throws(DwollaException::class, OAuthException::class)
    fun createFundingSourcesToken(id: String): FundingSourcesToken {
        return dwolla.post(FundingSourcesToken::class.java, fundingSourcesTokenUrl(id)).body
    }

    private fun createCustomer(jsonBody: JsonBody, idempotencyKey: String?): Customer {
        return dwolla.postFollow(
            Customer::class.java,
            CUSTOMERS,
            jsonBody,
            Headers(IDEMPOTENCY_KEY to idempotencyKey)
        ).body
    }

    private fun customerUrl(id: String): String {
        return dwolla.urlBuilder.buildUrl(CUSTOMERS, id)
    }

    private fun fundingSourcesTokenUrl(id: String): String {
        return dwolla.urlBuilder.buildUrl(CUSTOMERS, id, FUNDING_SOURCES_TOKEN)
    }

    private fun iavTokenUrl(id: String): String {
        return dwolla.urlBuilder.buildUrl(CUSTOMERS, id, IAV_TOKEN)
    }
}
