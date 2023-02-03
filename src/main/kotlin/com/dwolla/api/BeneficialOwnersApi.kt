package com.dwolla.api

import com.dwolla.DwollaClient
import com.dwolla.api.shared.DateOfBirth
import com.dwolla.api.shared.InternationalAddress
import com.dwolla.api.shared.Passport
import com.dwolla.http.JsonBody
import com.dwolla.resource.beneficialowners.BeneficialOwner
import com.dwolla.resource.beneficialowners.BeneficialOwners
import com.dwolla.resource.beneficialowners.BeneficialOwnership
import com.dwolla.util.Paths.Companion.BENEFICIAL_OWNERS
import com.dwolla.util.Paths.Companion.BENEFICIAL_OWNERSHIP
import com.dwolla.util.Paths.Companion.CUSTOMERS

class BeneficialOwnersApi(private val client: DwollaClient) {
    fun createForCustomer(
        customerId: String,
        firstName: String,
        lastName: String,
        ssn: String?,
        dateOfBirth: DateOfBirth,
        address: InternationalAddress,
        passport: Passport?
    ): BeneficialOwner {
        return client.postFollow(
            BeneficialOwner::class.java,
            customerBeneficialOwnersUrl(customerId),
            JsonBody(
                "firstName" to firstName,
                "lastName" to lastName,
                "ssn" to ssn,
                "dateOfBirth" to dateOfBirth,
                "address" to address,
                "passport" to passport
            )
        ).body
    }

    fun certifyBeneficialOwnershipForCustomer(customerId: String): BeneficialOwnership {
        return client.postFollow(
            BeneficialOwnership::class.java,
            customerBeneficialOwnershipUrl(customerId)
        ).body
    }

    fun get(id: String): BeneficialOwner {
        return client.get(
            BeneficialOwner::class.java,
            beneficialOwnerUrl(id)
        ).body
    }

    fun getBeneficialOwnershipByCustomer(customerId: String): BeneficialOwnership {
        return client.get(
            BeneficialOwnership::class.java,
            customerBeneficialOwnershipUrl(customerId)
        ).body
    }

    fun listByCustomer(customerId: String): BeneficialOwners {
        return client.get(
            BeneficialOwners::class.java,
            customerBeneficialOwnersUrl(customerId)
        ).body
    }

    fun remove(id: String): BeneficialOwner {
        return client.delete(
            BeneficialOwner::class.java,
            customerBeneficialOwnersUrl(id)
        ).body
    }

    fun update(
        id: String,
        firstName: String,
        lastName: String,
        ssn: String?,
        dateOfBirth: DateOfBirth,
        address: InternationalAddress,
        passport: Passport?
    ): BeneficialOwner {
        return client.post(
            BeneficialOwner::class.java,
            beneficialOwnerUrl(id),
            JsonBody(
                "firstName" to firstName,
                "lastName" to lastName,
                "ssn" to ssn,
                "dateOfBirth" to dateOfBirth,
                "address" to address,
                "passport" to passport
            )
        ).body
    }

    private fun beneficialOwnerUrl(id: String): String {
        return client.urlBuilder.buildUrl(BENEFICIAL_OWNERS, id)
    }

    private fun customerBeneficialOwnersUrl(customerId: String): String {
        return client.urlBuilder.buildUrl(CUSTOMERS, customerId, BENEFICIAL_OWNERS)
    }

    private fun customerBeneficialOwnershipUrl(customerId: String): String {
        return client.urlBuilder.buildUrl(CUSTOMERS, customerId, BENEFICIAL_OWNERSHIP)
    }
}
