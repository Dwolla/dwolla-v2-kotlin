package com.dwolla.util

import com.dwolla.http.JsonBody
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlin.test.Test
import kotlin.test.assertEquals

class JsonBodyTest {
    @Test
    fun `builds from Gson JsonObject`() {
        val rawJson = "{\"strProp\":\"value\",\"listOfInts\":[1,2,3]}"
        val gsonObj = JsonParser.parseString(rawJson).asJsonObject
        val jsonBody = JsonBody.fromEntrySet(gsonObj.entrySet())
        val strJsonBody = GsonBuilder().create().toJson(jsonBody.map)
        assertEquals(strJsonBody, rawJson)
    }
}
