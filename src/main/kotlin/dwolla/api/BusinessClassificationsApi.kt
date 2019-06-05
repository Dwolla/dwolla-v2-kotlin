package dwolla.api

import dwolla.Client
import dwolla.exception.DwollaException
import dwolla.exception.OAuthException
import dwolla.resource.businessclassifications.BusinessClassifications

class BusinessClassificationsApi(private val client: Client) {

    @Throws(DwollaException::class, OAuthException::class)
    fun list(): BusinessClassifications {
        return client.get(BusinessClassifications::class.java, "business-classifications").body
    }
}
