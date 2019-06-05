package dwolla.resource.root

import dwolla.resource.HalResource
import dwolla.resource.Links

data class Root(@JvmField val _links: Links) : HalResource(_links)
