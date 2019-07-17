package com.dwolla.api

import com.dwolla.Dwolla
import com.dwolla.exception.DwollaException
import com.dwolla.exception.OAuthException
import com.dwolla.resource.businessclassifications.BusinessClassifications
import com.dwolla.util.Paths.Companion.BUSINESS_CLASSIFICATIONS

class BusinessClassificationsApi(private val dwolla: Dwolla) {

    @Throws(DwollaException::class, OAuthException::class)
    fun list(): BusinessClassifications {
        return dwolla.get(BusinessClassifications::class.java, BUSINESS_CLASSIFICATIONS).body
    }
}
