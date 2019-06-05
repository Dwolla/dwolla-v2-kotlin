package dwolla.api.customers

data class CustomerController(
    @JvmField val firstName: String,
    @JvmField val lastName: String,
    @JvmField val title: String,
    @JvmField val dateOfBirth: String,
    @JvmField val address: CustomerAddress,
    @JvmField val ssn: String? = null,
    @JvmField val passport: CustomerPassport? = null
)
