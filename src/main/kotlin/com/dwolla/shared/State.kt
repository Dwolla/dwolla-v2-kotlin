package com.dwolla.shared

import com.google.gson.annotations.SerializedName

enum class State(
    @JvmField val value: String,
    @JvmField val displayName: String
) {

    @SerializedName("AL")
    AL("AL", "Alabama"),

    @SerializedName("AK")
    AK("AK", "Alaska"),

    @SerializedName("AS")
    AS("AS", "American Samoa"),

    @SerializedName("AZ")
    AZ("AZ", "Arizona"),

    @SerializedName("AR")
    AR("AR", "Arkansas"),

    @SerializedName("CA")
    CA("CA", "California"),

    @SerializedName("CO")
    CO("CO", "Colorado"),

    @SerializedName("CT")
    CT("CT", "Connecticut"),

    @SerializedName("DC")
    DC("DC", "District of Columbia"),

    @SerializedName("DE")
    DE("DE", "Delaware"),

    @SerializedName("FL")
    FL("FL", "Florida"),

    @SerializedName("GA")
    GA("GA", "Georgia"),

    @SerializedName("GU")
    GU("GU", "Guam"),

    @SerializedName("HI")
    HI("HI", "Hawaii"),

    @SerializedName("ID")
    ID("ID", "Idaho"),

    @SerializedName("IL")
    IL("IL", "Illinois"),

    @SerializedName("IN")
    IN("IN", "Indiana"),

    @SerializedName("IA")
    IA("IA", "Iowa"),

    @SerializedName("KS")
    KS("KS", "Kansas"),

    @SerializedName("KY")
    KY("KY", "Kentucky"),

    @SerializedName("LA")
    LA("LA", "Louisiana"),

    @SerializedName("ME")
    ME("ME", "Maine"),

    @SerializedName("MD")
    MD("MD", "Maryland"),

    @SerializedName("MA")
    MA("MA", "Massachusetts"),

    @SerializedName("MI")
    MI("MI", "Michigan"),

    @SerializedName("MN")
    MN("MN", "Minnesota"),

    @SerializedName("MS")
    MS("MS", "Mississippi"),

    @SerializedName("MO")
    MO("MO", "Missouri"),

    @SerializedName("MT")
    MT("MT", "Montana"),

    @SerializedName("NE")
    NE("NE", "Nebraska"),

    @SerializedName("NV")
    NV("NV", "Nevada"),

    @SerializedName("NH")
    NH("NH", "New Hampshire"),

    @SerializedName("NJ")
    NJ("NJ", "New Jersey"),

    @SerializedName("NM")
    NM("NM", "New Mexico"),

    @SerializedName("NY")
    NY("NY", "New York"),

    @SerializedName("NC")
    NC("NC", "North Carolina"),

    @SerializedName("ND")
    ND("ND", "North Dakota"),

    @SerializedName("MP")
    MP("MP", "Northern Mariana Islands"),

    @SerializedName("OH")
    OH("OH", "Ohio"),

    @SerializedName("OK")
    OK("OK", "Oklahoma"),

    @SerializedName("OR")
    OR("OR", "Oregon"),

    @SerializedName("PA")
    PA("PA", "Pennsylvania"),

    @SerializedName("PR")
    PR("PR", "Puerto Rico"),

    @SerializedName("RI")
    RI("RI", "Rhode Island"),

    @SerializedName("SC")
    SC("SC", "South Carolina"),

    @SerializedName("SD")
    SD("SD", "South Dakota"),

    @SerializedName("TN")
    TN("TN", "Tennessee"),

    @SerializedName("TX")
    TX("TX", "Texas"),

    @SerializedName("UM")
    UM("UM", "U.S. Minor Outlying Islands"),

    @SerializedName("UT")
    UT("UT", "Utah"),

    @SerializedName("VT")
    VT("VT", "Vermont"),

    @SerializedName("VI")
    VI("VI", "Virgin Islands"),

    @SerializedName("VA")
    VA("VA", "Virginia"),

    @SerializedName("WA")
    WA("WA", "Washington"),

    @SerializedName("WV")
    WV("WV", "West Virginia"),

    @SerializedName("WI")
    WI("WI", "Wisconsin"),

    @SerializedName("WY")
    WY("WY", "Wyoming")
}
