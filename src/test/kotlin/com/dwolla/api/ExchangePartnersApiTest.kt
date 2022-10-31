package com.dwolla.api

import com.dwolla.Instances.dwolla
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Tags
import org.junit.jupiter.api.Test

@Tags(Tag("api"), Tag("exchange-partners"))
class ExchangePartnersApiTest : ApiTest() {
    @Test
    fun testList() {
        assertResponse { dwolla.exchangePartners.list() }
    }

    @Test
    fun testGet() {
        val exchangePartnerId = "9b55a4b3-34ae-4607-b2d1-622f1eed77f9"
        assertResponse { dwolla.exchangePartners.get(exchangePartnerId) }
    }
}