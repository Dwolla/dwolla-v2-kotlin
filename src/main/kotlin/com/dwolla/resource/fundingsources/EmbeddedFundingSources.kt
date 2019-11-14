package com.dwolla.resource.fundingsources

import com.google.gson.annotations.SerializedName

data class EmbeddedFundingSources(
    @JvmField @SerializedName("funding-sources")
    val fundingSources: Array<FundingSource>
)
