package dwollav2.http

class Headers {
    internal val map: MutableMap<String, String> = mutableMapOf()

    constructor() {}

    constructor(vararg pairs: Pair<String, String?>) {
        pairs.forEach { p ->
            add(p.first, p.second)
        }
    }

    fun add(key: String, value: String?): Headers {
        if (value != null)
            map[key.toLowerCase()] = value
        return this
    }

    fun get(key: String): String? {
        return map[key.toLowerCase()]
    }

    override fun toString(): String {
        return map.toString()
    }
}
