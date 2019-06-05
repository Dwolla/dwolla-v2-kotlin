package dwolla.api.customers

import com.google.gson.annotations.SerializedName

enum class CustomerBusinessType(val value: String) {
    @SerializedName("corporation")
    CORPORATION("corporation"),

    @SerializedName("llc")
    LLC("llc"),

    @SerializedName("partnership")
    PARTNERSHIP("partnership")
}
