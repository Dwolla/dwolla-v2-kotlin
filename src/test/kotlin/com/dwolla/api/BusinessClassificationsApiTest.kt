package com.dwolla.api

import com.dwolla.Instances.dwolla
import kotlin.test.Test

class BusinessClassificationsApiTest : ApiTest() {

    @Test
    fun testList() {
        assertResponse { dwolla.businessClassifications.list() }
    }
}
