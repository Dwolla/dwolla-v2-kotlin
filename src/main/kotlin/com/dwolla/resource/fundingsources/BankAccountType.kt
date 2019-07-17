package com.dwolla.resource.fundingsources

import com.google.gson.annotations.SerializedName

enum class BankAccountType(@JvmField val value: String) {
    @SerializedName("checking")
    CHECKING("checking"),

    @SerializedName("savings")
    SAVINGS("savings"),

    @SerializedName("general-ledger")
    GENERAL_LEDGER("general-ledger"),

    @SerializedName("loan")
    LOAN("loan"),
}
