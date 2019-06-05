package dwolla.resource.customers

import com.google.gson.annotations.SerializedName

enum class CustomerStatus(@JvmField val value: String) {
    @SerializedName("unverified")
    UNVERIFIED("unverified"),

    @SerializedName("retry")
    RETRY("retry"),

    @SerializedName("document")
    DOCUMENT("document"),

    @SerializedName("verified")
    VERIFIED("verified"),

    @SerializedName("suspended")
    SUSPENDED("suspended"),

    @SerializedName("deactivated")
    DEACTIVATED("deactivated")
}
