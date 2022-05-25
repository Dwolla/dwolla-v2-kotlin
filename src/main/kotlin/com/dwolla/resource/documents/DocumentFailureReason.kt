package com.dwolla.resource.documents

import com.google.gson.annotations.SerializedName

enum class DocumentFailureReason(@JvmField val value: String) {
    @SerializedName("BusinessDocNotSupported")
    BUSINESS_DOC_NOT_SUPPORTED("BusinessDocNotSupported"),

    @SerializedName("BusinessNameMismatch")
    BUSINESS_NAME_MISMATCH("BusinessNameMismatch"),

    @SerializedName("BusinessTypeMismatch")
    BUSINESS_TYPE_MISMATCH("BusinessTypeMismatch"),

    @SerializedName("ScanDobMismatch")
    SCAN_DOB_MISMATCH("ScanDobMismatch"),

    @SerializedName("ScanFailedOther")
    SCAN_FAILED_OTHER("ScanFailedOther"),

    @SerializedName("ScanIdExpired")
    SCAN_ID_EXPIRED("ScanIdExpired"),

    @SerializedName("ScanIdTypeNotSupported")
    SCAN_ID_TYPE_NOT_SUPPORTED("ScanIdTypeNotSupported"),

    @SerializedName("ScanIdUnrecognized")
    SCAN_ID_UNRECOGNIZED("ScanIdUnrecognized"),

    @SerializedName("ScanNameMismatch")
    SCAN_NAME_MISMATCH("ScanNameMismatch"),

    @SerializedName("ScanNotReadable")
    SCAN_NOT_READABLE("ScanNotReadable"),

    @SerializedName("ScanNotUploaded")
    SCAN_NOT_UPLOADED("ScanNotUploaded")
}
