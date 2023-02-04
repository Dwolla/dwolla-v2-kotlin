package com.dwolla.api

import com.dwolla.DwollaClient
import com.dwolla.exception.DwollaApiException
import com.dwolla.exception.DwollaAuthException
import com.dwolla.resource.businessclassifications.BusinessClassification
import com.dwolla.resource.businessclassifications.BusinessClassifications
import com.dwolla.util.Paths.Companion.BUSINESS_CLASSIFICATIONS

class BusinessClassificationsApi(private val client: DwollaClient) {
    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun get(id: String): BusinessClassification {
        return client.get(
            BusinessClassification::class.java,
            businessClassificationUrl(id)
        ).body
    }

    @Throws(DwollaApiException::class, DwollaAuthException::class)
    fun list(): BusinessClassifications {
        return client.get(BusinessClassifications::class.java, BUSINESS_CLASSIFICATIONS).body
    }

    private fun businessClassificationUrl(id: String): String {
        return client.urlBuilder.buildUrl(BUSINESS_CLASSIFICATIONS, id)
    }
}
