package dwollav2.resource.customers

import dwollav2.resource.Links

data class Customers(
    @JvmField val _links: Links,
    @JvmField val _embedded: EmbeddedCustomers,
    @JvmField val total: Int
)
