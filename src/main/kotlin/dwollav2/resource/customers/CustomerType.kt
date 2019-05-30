package dwollav2.resource.customers

import com.google.gson.annotations.SerializedName

enum class CustomerType(@JvmField val value: String) {
    @SerializedName("personal-no-balance")
    PERSONAL_NO_BALANCE("personal-no-balance"),

    @SerializedName("business-no-balance")
    BUSINESS_NO_BALANCE("business-no-balance"),

    @SerializedName("personal")
    PERSONAL("personal"),

    @SerializedName("business")
    BUSINESS("business"),

    @SerializedName("receive-only")
    RECEIVE_ONLY("receive-only"),

    @SerializedName("unverified")
    UNVERIFIED("unverified"),
}
