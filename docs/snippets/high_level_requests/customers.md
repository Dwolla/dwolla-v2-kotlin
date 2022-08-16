# High-level examples (Customers) 
[`dwolla.customers.*`](https://github.com/Dwolla/dwolla-v2-kotlin/blob/main/src/main/kotlin/com/dwolla/api/CustomersApi.kt)

## Retrieve a Customer by ID

```kotlin
dwolla.customers.get()
```

## Retrieve a list of Customers (limit=10, offset=20, search=Jane, status=deactivated)

```kotlin
dwolla.customers.list(
    limit = 10,
    offset = 20,
    search = "Jane",
    status = CustomerStatus.DEACTIVATED
)
```

## Create a Receive-Only User

```kotlin
dwolla.customers.createReceiveOnly(
  firstName = "Jane",
  lastName = "Doe",
  email = "jane.doe@email.com"
)
```

## Create an Unverified Customer

```kotlin
dwolla.customers.createUnverified(
  firstName = "Jane",
  lastName = "Doe",
  email = "jane.doe@email.com", 
  businessName = "Jane Doe's Business",
  ipAddress = "10.10.10.10",
  idempotencyKey = "FC451A7A-AE30-4404-AB95-E3553FCD733F",
  correlationId = "123"
)
```

## Create a Personal Verified Customer

```kotlin
dwolla.customers.createVerifiedPersonal(
  firstName = "Jane",
  lastName = "Doe",
  email = "jane.doe@email.com",
  address1 = "360 Main St",
  address2 = "Building # ",
  city = "Some city",
  state = "IA",
  postalCode = "50309",
  dateOfBirth = "1970-03-15",
  ssn = "6789",
  phone = "5555555555",
  ipAddress = "10.10.10.10",
  idempotencyKey = "FC451A7A-AE30-4404-AB95-E3553FCD733F"
)
```

## Create a Business Verified Customer (Sole Proprietorship)

```kotlin
dwolla.customers.createVerifiedSoleProp(
  firstName = "John",
  lastName = "Doe",
  email = "john.doe@nomail.com",
  dateOfBirth = "1970-01-15",
  ssn = "1234",
  address1 = "360 Main St",
  address2 = "Building number 8",
  city = "Some City",
  state = "CA",
  postalCode = "50309",
  businessName = "John Doe's Business",
  businessClassification = "9ed3f670-7d6f-11e3-b1ce-5404a6144203",
  website = "https://www.website.com",
  phone = "555-555-5555",
  ein = "00-0000000",
  doingBusinessAs = "John's Pizzas",
  ipAddress = "00.00.00.00", 
  idempotencyKey =  "FC451A7A-AE30-4404-AB95-E3553FCD733F"
)
```

## Create a Business Verified Customer (LLC)

```kotlin
dwolla.customers.createVerifiedBusiness(
  firstName = "John",
  lastName = "Doe",
  email = "john.doe@nomail.com",
  address1 = "360 Main St",
  address2 = "Building 5",
  city = "Some City",
  state = USState.NY,
  postalCode = "11101",
  businessName = "John Doe Inc.",
  businessType = BusinessType.LLC,
  businessClassification = "9ed38155-7d6f-11e3-83c3-5404a6144203",
  controller = Controller(
    firstName = "John",
    lastName = "Controller",
    title = "CEO",
    dateOfBirth = DateOfBirth(1995,10,3),
    address = InternationalAddress(
      address1 = "123 main st",
      city = "des moines",
      stateProvinceRegion = "IA",
      country = Country.US,
      postalCode = "50309"
    ), 
    ssn = "1234"
  ),
  ein = "00-0000000",
  website =  "https://www.example.com",
  phone =  "5555555555",
  doingBusinessAs =  "Jane's Canes",
  ipAddress =  "00.00.00.00",
  idempotencyKey =  "FC451A7A-AE30-4404-AB95-E3553FCD733F"
)
```

## Update an Unverified Customer

```kotlin
dwolla.customers.updateUnverified(
  id = "FC451A7A-AE30-4404-AB95-E3553FCD733F",
  firstName = "Jane",
  lastName = "Doe",
  email = "jane.doe@email.com", 
  businessName = "Jane Doe's Business"
)
```

## Update a Personal Verified Customer

```kotlin
dwolla.customers.updateVerifiedPersonal(
  id = "FC451A7A-AE30-4404-AB95-E3553FCD733F",
  email = "jane.doe@email.com",
  ipAddress = "10.10.10.10",
  address1 = "360 Main St",
  address2 = "Building # ",
  city = "Some city",
  state = "IA",
  postalCode = "50309",
  phone = "5555555555"
)
```

## Update a Business Verified Customer

```kotlin
dwolla.customers.updateVerifiedBusiness(
  id = "FC451A7A-AE30-4404-AB95-E3553FCD733F",
  email = "jane.doe@email.com",
  ipAddress = "10.10.10.10",
  address1 = "360 Main St",
  address2 = "Building # ",
  city = "Some city",
  state = "IA",
  postalCode = "50309",
  phone = "5555555555",
  doingBusinessAs = "Jane's Canes",
  website = "https://www.example.com"
)
```

## Upgrade from an Unverified Customer to Personal Verified Customer

```kotlin
dwolla.customers.upgradeToVerifiedPersonal(
  id = "FC451A7A-AE30-4404-AB95-E3553FCD733F",
  firstName = "Jane",
  lastName = "Doe",
  email = "jane.doe@email.com",
  address1 = "360 Main St",
  address2 = "Building # ",
  city = "Some city",
  state = "IA",
  postalCode = "50309",
  dateOfBirth = "1970-03-15",
  ssn = "6789",
  phone = "5555555555",
  ipAddress = "10.10.10.10",
)
```

## Retry Personal Verified Customer

```kotlin
dwolla.customers.retryVerifiedPersonal(
  id = "FC451A7A-AE30-4404-AB95-E3553FCD733F",
  firstName = "Jane",
  lastName = "Doe",
  email = "jane.doe@email.com",
  address1 = "360 Main St",
  address2 = "Building # ",
  city = "Some city",
  state = "IA",
  postalCode = "50309",
  dateOfBirth = "1970-03-15",
  ssn = "123-45-6789",
  phone = "5555555555",
  ipAddress = "10.10.10.10",
)
```

## Retry Business Verified Customer

```kotlin
dwolla.customers.retryVerifiedBusiness(
  id = "FC451A7A-AE30-4404-AB95-E3553FCD733F",
  email = "jane.doe@email.com",
  address1 = "360 Main St",
  address2 = "Building # ",
  city = "Some city",
  state = "IA",
  postalCode = "50309",
  website =  "https://www.example.com",
  phone =  "5555555555",
  doingBusinessAs =  "Jane's Canes",
  ipAddress = "10.10.10.10",
)
```

## Suspend a Customer

```kotlin
dwolla.customers.suspend(
  id = "FC451A7A-AE30-4404-AB95-E3553FCD733F",
)
```

## Deactivate a Customer

```kotlin
dwolla.customers.deactivate(
  id = "FC451A7A-AE30-4404-AB95-E3553FCD733F",
)
```

## Reactivate a Customer

```kotlin
dwolla.customers.reactivate(
  id = "FC451A7A-AE30-4404-AB95-E3553FCD733F",
)
```
