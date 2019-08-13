package com.dwolla.shared

import com.google.gson.annotations.SerializedName

enum class Country(
    @JvmField val value: String,
    @JvmField val displayName: String
) {

    @SerializedName("AD")
    AD("AD", "Andorra, Principality of"),

    @SerializedName("AE")
    AE("AE", "United Arab Emirates"),

    @SerializedName("AF")
    AF("AF", "Afghanistan"),

    @SerializedName("AG")
    AG("AG", "Antigua and Barbuda"),

    @SerializedName("AI")
    AI("AI", "Anguilla"),

    @SerializedName("AL")
    AL("AL", "Albania, People's Socialist Republic of"),

    @SerializedName("AM")
    AM("AM", "Armenia"),

    // @SerializedName("AN")
    // AN("AN", "Netherlands Antilles"),

    @SerializedName("AO")
    AO("AO", "Angola, Republic of"),

    @SerializedName("AQ")
    AQ("AQ", "Antarctica (the territory South of 60 deg S)"),

    @SerializedName("AR")
    AR("AR", "Argentina, Argentine Republic"),

    @SerializedName("AS")
    AS("AS", "American Samoa"),

    @SerializedName("AT")
    AT("AT", "Austria, Republic of"),

    @SerializedName("AU")
    AU("AU", "Australia, Commonwealth of"),

    @SerializedName("AW")
    AW("AW", "Aruba"),

    @SerializedName("AX")
    AX("AX", "\u00c5land Islands"),

    @SerializedName("AZ")
    AZ("AZ", "Azerbaijan, Republic of"),

    @SerializedName("BA")
    BA("BA", "Bosnia and Herzegovina"),

    @SerializedName("BB")
    BB("BB", "Barbados"),

    @SerializedName("BD")
    BD("BD", "Bangladesh, People's Republic of"),

    @SerializedName("BE")
    BE("BE", "Belgium, Kingdom of"),

    @SerializedName("BF")
    BF("BF", "Burkina Faso"),

    @SerializedName("BG")
    BG("BG", "Bulgaria, People's Republic of"),

    @SerializedName("BH")
    BH("BH", "Bahrain, Kingdom of"),

    @SerializedName("BI")
    BI("BI", "Burundi, Republic of"),

    @SerializedName("BJ")
    BJ("BJ", "Benin, People's Republic of"),

    @SerializedName("BL")
    BL("BL", "Saint Barth\u00e9lemy"),

    @SerializedName("BM")
    BM("BM", "Bermuda"),

    @SerializedName("BN")
    BN("BN", "Brunei Darussalam"),

    @SerializedName("BO")
    BO("BO", "Bolivia, Republic of"),

    @SerializedName("BQ")
    BQ("BQ", "Bonaire, Sint Eustatius and Saba"),

    @SerializedName("BR")
    BR("BR", "Brazil, Federative Republic of"),

    @SerializedName("BS")
    BS("BS", "Bahamas, Commonwealth of the"),

    @SerializedName("BT")
    BT("BT", "Bhutan, Kingdom of"),

    @SerializedName("BV")
    BV("BV", "Bouvet Island (Bouvetoya)"),

    @SerializedName("BW")
    BW("BW", "Botswana, Republic of"),

    @SerializedName("BY")
    BY("BY", "Belarus"),

    @SerializedName("BZ")
    BZ("BZ", "Belize"),

    @SerializedName("CA")
    CA("CA", "Canada"),

    @SerializedName("CC")
    CC("CC", "Cocos (Keeling) Islands"),

    @SerializedName("CD")
    CD("CD", "Congo, Democratic Republic of"),

    @SerializedName("CF")
    CF("CF", "Central African Republic"),

    @SerializedName("CG")
    CG("CG", "Congo, People's Republic of"),

    @SerializedName("CH")
    CH("CH", "Switzerland, Swiss Confederation"),

    @SerializedName("CI")
    CI("CI", "Cote D'Ivoire, Ivory Coast, Republic of the"),

    @SerializedName("CK")
    CK("CK", "Cook Islands"),

    @SerializedName("CL")
    CL("CL", "Chile, Republic of"),

    @SerializedName("CM")
    CM("CM", "Cameroon, United Republic of"),

    @SerializedName("CN")
    CN("CN", "China, People's Republic of"),

    @SerializedName("CO")
    CO("CO", "Colombia, Republic of"),

    @SerializedName("CR")
    CR("CR", "Costa Rica, Republic of"),

    // @SerializedName("CS")
    // CS("CS", "Serbia and Montenegro"),

    @SerializedName("CU")
    CU("CU", "Cuba, Republic of"),

    @SerializedName("CV")
    CV("CV", "Cape Verde, Republic of"),

    @SerializedName("CW")
    CW("CW", "Cura\u00e7ao"),

    @SerializedName("CX")
    CX("CX", "Christmas Island"),

    @SerializedName("CY")
    CY("CY", "Cyprus, Republic of"),

    @SerializedName("CZ")
    CZ("CZ", "Czech Republic"),

    @SerializedName("DE")
    DE("DE", "Germany"),

    @SerializedName("DJ")
    DJ("DJ", "Djibouti, Republic of"),

    @SerializedName("DK")
    DK("DK", "Denmark, Kingdom of"),

    @SerializedName("DM")
    DM("DM", "Dominica, Commonwealth of"),

    @SerializedName("DO")
    DO("DO", "Dominican Republic"),

    @SerializedName("DZ")
    DZ("DZ", "Algeria, People's Democratic Republic of"),

    @SerializedName("EC")
    EC("EC", "Ecuador, Republic of"),

    @SerializedName("EE")
    EE("EE", "Estonia"),

    @SerializedName("EG")
    EG("EG", "Egypt, Arab Republic of"),

    @SerializedName("EH")
    EH("EH", "Western Sahara"),

    @SerializedName("ER")
    ER("ER", "Eritrea"),

    @SerializedName("ES")
    ES("ES", "Spain, Spanish State"),

    @SerializedName("ET")
    ET("ET", "Ethiopia"),

    @SerializedName("FI")
    FI("FI", "Finland, Republic of"),

    @SerializedName("FJ")
    FJ("FJ", "Fiji, Republic of the Fiji Islands"),

    @SerializedName("FK")
    FK("FK", "Falkland Islands (Malvinas)"),

    @SerializedName("FM")
    FM("FM", "Micronesia, Federated States of"),

    @SerializedName("FO")
    FO("FO", "Faeroe Islands"),

    @SerializedName("FR")
    FR("FR", "France, French Republic"),

    @SerializedName("GA")
    GA("GA", "Gabon, Gabonese Republic"),

    @SerializedName("GB")
    GB("GB", "United Kingdom of Great Britain & N. Ireland"),

    @SerializedName("GD")
    GD("GD", "Grenada"),

    @SerializedName("GE")
    GE("GE", "Georgia"),

    @SerializedName("GF")
    GF("GF", "French Guiana"),

    @SerializedName("GG")
    GG("GG", "Guernsey"),

    @SerializedName("GH")
    GH("GH", "Ghana, Republic of"),

    @SerializedName("GI")
    GI("GI", "Gibraltar"),

    @SerializedName("GL")
    GL("GL", "Greenland"),

    @SerializedName("GM")
    GM("GM", "Gambia, Republic of the"),

    @SerializedName("GN")
    GN("GN", "Guinea, Revolutionary People's Rep'c of"),

    @SerializedName("GP")
    GP("GP", "Guadaloupe"),

    @SerializedName("GQ")
    GQ("GQ", "Equatorial Guinea, Republic of"),

    @SerializedName("GR")
    GR("GR", "Greece, Hellenic Republic"),

    @SerializedName("GS")
    GS("GS", "South Georgia and the South Sandwich Islands"),

    @SerializedName("GT")
    GT("GT", "Guatemala, Republic of"),

    @SerializedName("GU")
    GU("GU", "Guam"),

    @SerializedName("GW")
    GW("GW", "Guinea-Bissau, Republic of"),

    @SerializedName("GY")
    GY("GY", "Guyana, Republic of"),

    @SerializedName("HK")
    HK("HK", "Hong Kong, Special Administrative Region of China"),

    @SerializedName("HM")
    HM("HM", "Heard and McDonald Islands"),

    @SerializedName("HN")
    HN("HN", "Honduras, Republic of"),

    @SerializedName("HR")
    HR("HR", "Hrvatska (Croatia)"),

    @SerializedName("HT")
    HT("HT", "Haiti, Republic of"),

    @SerializedName("HU")
    HU("HU", "Hungary, Hungarian People's Republic"),

    @SerializedName("ID")
    ID("ID", "Indonesia, Republic of"),

    @SerializedName("IE")
    IE("IE", "Ireland"),

    @SerializedName("IL")
    IL("IL", "Israel, State of"),

    @SerializedName("IM")
    IM("IM", "Isle of Man"),

    @SerializedName("IN")
    IN("IN", "India, Republic of"),

    @SerializedName("IO")
    IO("IO", "British Indian Ocean Territory (Chagos Archipelago)"),

    @SerializedName("IQ")
    IQ("IQ", "Iraq, Republic of"),

    @SerializedName("IR")
    IR("IR", "Iran, Islamic Republic of"),

    @SerializedName("IS")
    IS("IS", "Iceland, Republic of"),

    @SerializedName("IT")
    IT("IT", "Italy, Italian Republic"),

    @SerializedName("JE")
    JE("JE", "Jersey"),

    @SerializedName("JM")
    JM("JM", "Jamaica"),

    @SerializedName("JO")
    JO("JO", "Jordan, Hashemite Kingdom of"),

    @SerializedName("JP")
    JP("JP", "Japan"),

    @SerializedName("KE")
    KE("KE", "Kenya, Republic of"),

    @SerializedName("KG")
    KG("KG", "Kyrgyz Republic"),

    @SerializedName("KH")
    KH("KH", "Cambodia, Kingdom of"),

    @SerializedName("KI")
    KI("KI", "Kiribati, Republic of"),

    @SerializedName("KM")
    KM("KM", "Comoros, Union of the"),

    @SerializedName("KN")
    KN("KN", "St. Kitts and Nevis"),

    @SerializedName("KP")
    KP("KP", "Korea, Democratic People's Republic of"),

    @SerializedName("KR")
    KR("KR", "Korea, Republic of"),

    @SerializedName("KW")
    KW("KW", "Kuwait, State of"),

    @SerializedName("KY")
    KY("KY", "Cayman Islands"),

    @SerializedName("KZ")
    KZ("KZ", "Kazakhstan, Republic of"),

    @SerializedName("LA")
    LA("LA", "Lao People's Democratic Republic"),

    @SerializedName("LB")
    LB("LB", "Lebanon, Lebanese Republic"),

    @SerializedName("LC")
    LC("LC", "St. Lucia"),

    @SerializedName("LI")
    LI("LI", "Liechtenstein, Principality of"),

    @SerializedName("LK")
    LK("LK", "Sri Lanka, Democratic Socialist Republic of"),

    @SerializedName("LR")
    LR("LR", "Liberia, Republic of"),

    @SerializedName("LS")
    LS("LS", "Lesotho, Kingdom of"),

    @SerializedName("LT")
    LT("LT", "Lithuania"),

    @SerializedName("LU")
    LU("LU", "Luxembourg, Grand Duchy of"),

    @SerializedName("LV")
    LV("LV", "Latvia"),

    @SerializedName("LY")
    LY("LY", "Libyan Arab Jamahiriya"),

    @SerializedName("MA")
    MA("MA", "Morocco, Kingdom of"),

    @SerializedName("MC")
    MC("MC", "Monaco, Principality of"),

    @SerializedName("MD")
    MD("MD", "Moldova, Republic of"),

    @SerializedName("ME")
    ME("ME", "Montenegro, Republic of"),

    @SerializedName("MF")
    MF("MF", "Saint Martin"),

    @SerializedName("MG")
    MG("MG", "Madagascar, Republic of"),

    @SerializedName("MH")
    MH("MH", "Marshall Islands"),

    @SerializedName("MK")
    MK("MK", "Macedonia, the former Yugoslav Republic of"),

    @SerializedName("ML")
    ML("ML", "Mali, Republic of"),

    @SerializedName("MM")
    MM("MM", "Myanmar"),

    @SerializedName("MN")
    MN("MN", "Mongolia, Mongolian People's Republic"),

    @SerializedName("MO")
    MO("MO", "Macao, Special Administrative Region of China"),

    @SerializedName("MP")
    MP("MP", "Northern Mariana Islands"),

    @SerializedName("MQ")
    MQ("MQ", "Martinique"),

    @SerializedName("MR")
    MR("MR", "Mauritania, Islamic Republic of"),

    @SerializedName("MS")
    MS("MS", "Montserrat"),

    @SerializedName("MT")
    MT("MT", "Malta, Republic of"),

    @SerializedName("MU")
    MU("MU", "Mauritius"),

    @SerializedName("MV")
    MV("MV", "Maldives, Republic of"),

    @SerializedName("MW")
    MW("MW", "Malawi, Republic of"),

    @SerializedName("MX")
    MX("MX", "Mexico, United Mexican States"),

    @SerializedName("MY")
    MY("MY", "Malaysia"),

    @SerializedName("MZ")
    MZ("MZ", "Mozambique, People's Republic of"),

    @SerializedName("NA")
    NA("NA", "Namibia"),

    @SerializedName("NC")
    NC("NC", "New Caledonia"),

    @SerializedName("NE")
    NE("NE", "Niger, Republic of the"),

    @SerializedName("NF")
    NF("NF", "Norfolk Island"),

    @SerializedName("NG")
    NG("NG", "Nigeria, Federal Republic of"),

    @SerializedName("NI")
    NI("NI", "Nicaragua, Republic of"),

    @SerializedName("NL")
    NL("NL", "Netherlands, Kingdom of the"),

    @SerializedName("NO")
    NO("NO", "Norway, Kingdom of"),

    @SerializedName("NP")
    NP("NP", "Nepal, Kingdom of"),

    @SerializedName("NR")
    NR("NR", "Nauru, Republic of"),

    @SerializedName("NU")
    NU("NU", "Niue, Republic of"),

    @SerializedName("NZ")
    NZ("NZ", "New Zealand"),

    @SerializedName("OM")
    OM("OM", "Oman, Sultanate of"),

    @SerializedName("PA")
    PA("PA", "Panama, Republic of"),

    @SerializedName("PE")
    PE("PE", "Peru, Republic of"),

    @SerializedName("PF")
    PF("PF", "French Polynesia"),

    @SerializedName("PG")
    PG("PG", "Papua New Guinea"),

    @SerializedName("PH")
    PH("PH", "Philippines, Republic of the"),

    @SerializedName("PK")
    PK("PK", "Pakistan, Islamic Republic of"),

    @SerializedName("PL")
    PL("PL", "Poland, Republic of Poland"),

    @SerializedName("PM")
    PM("PM", "St. Pierre and Miquelon"),

    @SerializedName("PN")
    PN("PN", "Pitcairn Island"),

    @SerializedName("PR")
    PR("PR", "Puerto Rico"),

    @SerializedName("PS")
    PS("PS", "Palestinian Territory, Occupied"),

    @SerializedName("PT")
    PT("PT", "Portugal, Portuguese Republic"),

    @SerializedName("PW")
    PW("PW", "Palau"),

    @SerializedName("PY")
    PY("PY", "Paraguay, Republic of"),

    @SerializedName("QA")
    QA("QA", "Qatar, State of"),

    @SerializedName("RE")
    RE("RE", "Reunion"),

    @SerializedName("RO")
    RO("RO", "Romania, Socialist Republic of"),

    @SerializedName("RS")
    RS("RS", "Serbia, Republic of"),

    @SerializedName("RU")
    RU("RU", "Russian Federation"),

    @SerializedName("RW")
    RW("RW", "Rwanda, Rwandese Republic"),

    @SerializedName("SA")
    SA("SA", "Saudi Arabia, Kingdom of"),

    @SerializedName("SB")
    SB("SB", "Solomon Islands"),

    @SerializedName("SC")
    SC("SC", "Seychelles, Republic of"),

    @SerializedName("SD")
    SD("SD", "Sudan, Democratic Republic of the"),

    @SerializedName("SE")
    SE("SE", "Sweden, Kingdom of"),

    @SerializedName("SG")
    SG("SG", "Singapore, Republic of"),

    @SerializedName("SH")
    SH("SH", "St. Helena"),

    @SerializedName("SI")
    SI("SI", "Slovenia"),

    @SerializedName("SJ")
    SJ("SJ", "Svalbard & Jan Mayen Islands"),

    @SerializedName("SK")
    SK("SK", "Slovakia (Slovak Republic)"),

    @SerializedName("SL")
    SL("SL", "Sierra Leone, Republic of"),

    @SerializedName("SM")
    SM("SM", "San Marino, Republic of"),

    @SerializedName("SN")
    SN("SN", "Senegal, Republic of"),

    @SerializedName("SO")
    SO("SO", "Somalia, Somali Republic"),

    @SerializedName("SR")
    SR("SR", "Suriname, Republic of"),

    @SerializedName("SS")
    SS("SS", "South Sudan"),

    @SerializedName("ST")
    ST("ST", "Sao Tome and Principe, Democratic Republic of"),

    @SerializedName("SV")
    SV("SV", "El Salvador, Republic of"),

    @SerializedName("SX")
    SX("SX", "Sint Maarten (Dutch part)"),

    @SerializedName("SY")
    SY("SY", "Syrian Arab Republic"),

    @SerializedName("SZ")
    SZ("SZ", "Swaziland, Kingdom of"),

    @SerializedName("TC")
    TC("TC", "Turks and Caicos Islands"),

    @SerializedName("TD")
    TD("TD", "Chad, Republic of"),

    @SerializedName("TF")
    TF("TF", "French Southern Territories"),

    @SerializedName("TG")
    TG("TG", "Togo, Togolese Republic"),

    @SerializedName("TH")
    TH("TH", "Thailand, Kingdom of"),

    @SerializedName("TJ")
    TJ("TJ", "Tajikistan"),

    @SerializedName("TK")
    TK("TK", "Tokelau (Tokelau Islands)"),

    @SerializedName("TL")
    TL("TL", "Timor-Leste, Democratic Republic of"),

    @SerializedName("TM")
    TM("TM", "Turkmenistan"),

    @SerializedName("TN")
    TN("TN", "Tunisia, Republic of"),

    @SerializedName("TO")
    TO("TO", "Tonga, Kingdom of"),

    @SerializedName("TR")
    TR("TR", "Turkey, Republic of"),

    @SerializedName("TT")
    TT("TT", "Trinidad and Tobago, Republic of"),

    @SerializedName("TV")
    TV("TV", "Tuvalu"),

    @SerializedName("TW")
    TW("TW", "Taiwan, Province of China"),

    @SerializedName("TZ")
    TZ("TZ", "Tanzania, United Republic of"),

    @SerializedName("UA")
    UA("UA", "Ukraine"),

    @SerializedName("UG")
    UG("UG", "Uganda, Republic of"),

    @SerializedName("UM")
    UM("UM", "United States Minor Outlying Islands"),

    @SerializedName("US")
    US("US", "United States of America"),

    @SerializedName("UY")
    UY("UY", "Uruguay, Eastern Republic of"),

    @SerializedName("UZ")
    UZ("UZ", "Uzbekistan"),

    @SerializedName("VA")
    VA("VA", "Holy See (Vatican City State)"),

    @SerializedName("VC")
    VC("VC", "St. Vincent and the Grenadines"),

    @SerializedName("VE")
    VE("VE", "Venezuela, Bolivarian Republic of"),

    @SerializedName("VG")
    VG("VG", "British Virgin Islands"),

    @SerializedName("VI")
    VI("VI", "US Virgin Islands"),

    @SerializedName("VN")
    VN("VN", "Viet Nam, Socialist Republic of"),

    @SerializedName("VU")
    VU("VU", "Vanuatu"),

    @SerializedName("WF")
    WF("WF", "Wallis and Futuna Islands"),

    @SerializedName("WS")
    WS("WS", "Samoa, Independent State of"),

    @SerializedName("YE")
    YE("YE", "Yemen"),

    @SerializedName("YT")
    YT("YT", "Mayotte"),

    @SerializedName("ZA")
    ZA("ZA", "South Africa, Republic of"),

    @SerializedName("ZM")
    ZM("ZM", "Zambia, Republic of"),

    @SerializedName("ZW")
    ZW("ZW", "Zimbabwe"),
}
