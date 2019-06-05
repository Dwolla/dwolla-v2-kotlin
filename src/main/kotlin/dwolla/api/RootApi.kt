package dwolla.api

import dwolla.Client
import dwolla.exception.DwollaException
import dwolla.exception.OAuthException
import dwolla.resource.root.Root

class RootApi(private val client: Client) {

    @Throws(DwollaException::class, OAuthException::class)
    fun get(): Root {
        return client.get(Root::class.java, "/").body
    }
}
