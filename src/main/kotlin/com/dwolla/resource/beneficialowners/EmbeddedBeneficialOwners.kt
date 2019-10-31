package com.dwolla.resource.beneficialowners

import com.google.gson.annotations.SerializedName

data class EmbeddedBeneficialOwners(
    @JvmField @SerializedName("beneficial-owners")
    val beneficialOwners: Array<BeneficialOwner>
)
