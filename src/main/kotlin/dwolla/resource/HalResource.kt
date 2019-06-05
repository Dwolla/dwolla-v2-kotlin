package dwolla.resource

open class HalResource(private val links: Links) {
    fun hasLink(key: String): Boolean {
        return links.containsKey(key)
    }
}
