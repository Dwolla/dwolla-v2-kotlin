package dwollav2.resource

data class EmbeddedError(
    @JvmField val code: String,
    @JvmField val message: String,
    @JvmField val path: String,
    @JvmField val _links: Links
)
