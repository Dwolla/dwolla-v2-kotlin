package com.dwolla

import com.dwolla.api.* // ktlint-disable no-wildcard-imports

class DwollaToken(
    environment: Environment,
    @JvmField val accessToken: String,
    @JvmField val refreshToken: String? = null,
    @JvmField val expiresIn: Long? = null,
    @JvmField val tokenType: String? = null,
    @JvmField val idToken: String? = null
) : DwollaClient(environment) {

    @JvmField val businessClassifications = BusinessClassificationsApi(this)
    @JvmField val customers = CustomersApi(this)
    @JvmField val documents = DocumentsApi(this)
    @JvmField val root = RootApi(this)
    @JvmField val fundingSources = FundingSourcesApi(this)
    @JvmField val iavTokens = IavTokensApi(this)
    @JvmField val fundingSourcesTokens = FundingSourcesTokensApi(this)
    @JvmField val accounts = AccountsApi(this)
    @JvmField val beneficialOwners = BeneficialOwnersApi(this)
    @JvmField internal val transfers = TransfersApi(this)

    override fun getAccessToken(): String {
        return accessToken
    }
}
