package com.dwolla

import com.dwolla.api.AccountsApi
import com.dwolla.api.BeneficialOwnersApi
import com.dwolla.api.BusinessClassificationsApi
import com.dwolla.api.CustomersApi
import com.dwolla.api.DocumentsApi
import com.dwolla.api.FundingSourcesApi
import com.dwolla.api.FundingSourcesTokensApi
import com.dwolla.api.IavTokensApi
import com.dwolla.api.RootApi
import com.dwolla.api.TransfersApi

class DwollaToken(
    environment: DwollaEnvironment,
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
