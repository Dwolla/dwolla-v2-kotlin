package com.dwolla.api

import com.dwolla.Instances.dwolla
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import kotlin.test.Test

@Tags(Tag("api"))
class BusinessClassificationsApiTest : ApiTest() {

    @Test
    fun testList() {
        assertResponse { dwolla.businessClassifications.list() }
    }
}
