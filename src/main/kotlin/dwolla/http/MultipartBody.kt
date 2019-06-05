package dwolla.http

import com.github.kittinunf.fuel.core.DataPart

class MultipartBody {
    internal val list: MutableList<DataPart> = mutableListOf()

    constructor() {}

    constructor(vararg parts: DataPart) {
        parts.forEach { p -> list.add(p) }
    }

    fun add(part: DataPart): MultipartBody {
        list.add(part)
        return this
    }

    override fun toString(): String {
        return list.toString()
    }
}
