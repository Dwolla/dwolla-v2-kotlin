package dwollav2.api

import dwollav2.Client
import dwollav2.exception.DwollaException
import dwollav2.exception.OAuthException
import dwollav2.resource.businessclassifications.BusinessClassifications

class BusinessClassificationsApi(private val client: Client) {

    @Throws(DwollaException::class, OAuthException::class)
    fun list(): BusinessClassifications {
        return client.get(BusinessClassifications::class.java, "business-classifications").body
    }
}
