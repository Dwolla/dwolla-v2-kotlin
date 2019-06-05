package dwolla.api.documents

import com.google.gson.annotations.SerializedName

enum class DocumentType(val value: String) {
    @SerializedName("passport")
    PASSPORT("passport"),

    @SerializedName("license")
    LICENSE("license"),

    @SerializedName("idCard")
    ID_CARD("idCard"),

    @SerializedName("other")
    OTHER("other")
}
