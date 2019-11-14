package com.dwolla.resource.accounts

import com.google.gson.annotations.SerializedName

enum class AccountType(@JvmField val value: String) {

    @SerializedName("Personal")
    PERSONAL("Personal"),

    @SerializedName("Commercial")
    COMMERCIAL("Commercial"),

    @SerializedName("NonProfit")
    NON_PROFIT("NonProfit"),

    @SerializedName("Government")
    GOVERNMENT("Government"),
}
