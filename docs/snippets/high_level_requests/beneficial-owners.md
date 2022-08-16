# High-level examples (Beneficial Owners)
[`dwolla.beneficialOwners.*`](https://github.com/Dwolla/dwolla-v2-kotlin/blob/main/src/main/kotlin/com/dwolla/api/BeneficialOwnersApi.kt)

## Create a Beneficial Owner for a Customer

```kotlin
dwolla.beneficialOwners.createForCustomer(
  customerId = "707177c3-bf15-4e7e-b37c-55c3898d9bf4",
  firstName = "John",
  lastName = "Doe",
  ssn = "123-45-6789",
  dateOfBirth = "1990-09-09",
  address = InternationalAddress(
    address1 = "123 main st",
    city = "des moines",
    stateProvinceRegion = "IA",
    country = Country.US,
    postalCode = "50309"
  ),
)
```

## Retrieve a Beneficial Owner by ID

```kotlin
dwolla.beneficialOwners.get(
  id = "8a2cdc8d-629d-4a24-98ac-40b735229fe2",
)
```

## Retrieve a list of Beneficial Owner for a Customer

```kotlin
dwolla.beneficialOwners.listByCustomer(
  id = "c2bdcc87-91cd-41dd-9b06-5e31d4d3bbe4",
)
```
