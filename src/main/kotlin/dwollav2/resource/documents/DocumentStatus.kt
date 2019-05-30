package dwollav2.resource.documents

import com.google.gson.annotations.SerializedName

enum class DocumentStatus(@JvmField val value: String) {
    @SerializedName("pending")
    PENDING("pending"),

    @SerializedName("reviewed")
    REVIEWED("reviewed")
}
