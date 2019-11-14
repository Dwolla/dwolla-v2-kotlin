package com.dwolla.resource.transfers

import com.dwolla.resource.HalResource
import com.dwolla.resource.Links

data class Transfer(@JvmField val _links: Links) : HalResource() {
    override fun getLinks(): Links {
        return _links
    }
}
