package com.dwolla.resource

// not working with GSON
abstract class HalResource {
    protected abstract fun getLinks(): Links

    fun getHref(name: String): String {
        return getLinks().get(name)!!.href
    }

    fun hasLink(key: String): Boolean {
        return getLinks().containsKey(key)
    }
}
