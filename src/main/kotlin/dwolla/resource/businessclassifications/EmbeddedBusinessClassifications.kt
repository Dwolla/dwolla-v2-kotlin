package dwolla.resource.businessclassifications

import com.google.gson.annotations.SerializedName

data class EmbeddedBusinessClassifications(
    @JvmField @SerializedName("business-classifications")
    val businessClassifications: Array<BusinessClassification>
)
