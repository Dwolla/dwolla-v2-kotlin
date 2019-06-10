package com.dwolla.api

import com.dwolla.Client
import com.dwolla.exception.DwollaException
import com.dwolla.exception.OAuthException
import com.dwolla.resource.businessclassifications.BusinessClassifications

class BusinessClassificationsApi(private val client: Client) {

    @Throws(DwollaException::class, OAuthException::class)
    fun list(): BusinessClassifications {
        return client.get(BusinessClassifications::class.java, "business-classifications").body
    }
}
