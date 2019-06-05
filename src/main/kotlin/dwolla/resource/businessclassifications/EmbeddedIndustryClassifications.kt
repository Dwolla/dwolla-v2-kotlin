package dwolla.resource.businessclassifications

import com.google.gson.annotations.SerializedName

data class EmbeddedIndustryClassifications(
    @JvmField @SerializedName("industry-classifications")
    val industryClassifications: Array<IndustryClassification>
)
