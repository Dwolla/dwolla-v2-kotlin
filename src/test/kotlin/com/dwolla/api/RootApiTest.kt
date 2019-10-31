package com.dwolla.api

import com.dwolla.Instances.dwolla
import kotlin.test.Test

class RootApiTest : ApiTest() {

    @Test
    fun testGet() {
        assertResponse { dwolla.root.get() }
    }
}
