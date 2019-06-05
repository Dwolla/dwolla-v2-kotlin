package dwolla.resource.customers

import java.util.Optional

data class CustomerControllerAddress(
    @JvmField val address1: String,
    @JvmField val address2: Optional<String>,
    @JvmField val address3: Optional<String>,
    @JvmField val city: String,
    @JvmField val stateProvinceRegion: String,
    @JvmField val country: String,
    @JvmField val postalCode: Optional<String>
)
