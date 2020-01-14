package com.dwolla.http

import com.google.gson.JsonElement
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import java.lang.reflect.Type

class JsonBody {
    internal val map: MutableMap<String, Any> = mutableMapOf()

    constructor() {}

    constructor(vararg pairs: Pair<String, Any?>) {
        pairs.forEach { p ->
            add(p.first, p.second)
        }
    }

    fun add(key: String, value: Any?): JsonBody {
        if (value != null)
            map[key] = value
        return this
    }

    override fun toString(): String {
        return map.toString()
    }
}

internal class JsonBodyJsonSerializer : JsonSerializer<JsonBody> {
    override fun serialize(src: JsonBody?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        return context!!.serialize(src!!.map)
    }
}
