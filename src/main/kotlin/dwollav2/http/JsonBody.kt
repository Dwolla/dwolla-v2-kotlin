package dwollav2.http

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
