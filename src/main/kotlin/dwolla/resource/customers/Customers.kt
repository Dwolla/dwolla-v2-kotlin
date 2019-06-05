package dwolla.resource.customers

import dwolla.resource.HalResource
import dwolla.resource.Links

data class Customers(
    @JvmField val _links: Links,
    @JvmField val _embedded: EmbeddedCustomers,
    @JvmField val total: Int
) : HalResource(_links)
