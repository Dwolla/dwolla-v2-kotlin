package dwolla.resource

import com.google.gson.annotations.SerializedName

data class Link(
    @JvmField
    val href: String,

    @JvmField @SerializedName("resource-type")
    val resourceType: String
)
