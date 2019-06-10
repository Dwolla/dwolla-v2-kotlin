package com.dwolla.resource.documents

import com.google.gson.annotations.SerializedName

enum class DocumentFailureReason(@JvmField val value: String) {
    @SerializedName("ScanNotReadable")
    SCAN_NOT_READABLE("ScanNotReadable"),

    @SerializedName("ScanNotUploaded")
    SCAN_NOT_UPLOADED("ScanNotUploaded"),

    @SerializedName("ScanIdTypeNotSupported")
    SCAN_ID_TYPE_NOT_SUPPORTED("ScanIdTypeNotSupported"),

    @SerializedName("ScanNameMismatch")
    SCAN_NAME_MISMATCH("ScanNameMismatch"),

    @SerializedName("ScanFailedOther")
    SCAN_FAILED_OTHER("ScanFailedOther"),

    @SerializedName("FailedOther")
    FAILED_OTHER("FailedOther")
}
