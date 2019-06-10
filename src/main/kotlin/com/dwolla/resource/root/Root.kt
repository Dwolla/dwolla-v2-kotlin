package com.dwolla.resource.root

import com.dwolla.resource.HalResource
import com.dwolla.resource.Links

data class Root(@JvmField val _links: Links) : HalResource(_links)
