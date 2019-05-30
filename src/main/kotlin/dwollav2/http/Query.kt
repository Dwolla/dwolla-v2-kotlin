package dwollav2.http

class Query {
    internal val list: MutableList<Pair<String, Any>> = mutableListOf()

    constructor() {}

    constructor(vararg pairs: Pair<String, Any?>) {
        pairs.forEach { p ->
            add(p.first, p.second)
        }
    }

    fun add(key: String, value: Any?): Query {
        if (value != null)
            list.add(key to value)
        return this
    }

    override fun toString(): String {
        return list.toString()
    }
}
