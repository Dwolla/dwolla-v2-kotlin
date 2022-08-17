# High-Level Examples (Funding Sources)
Source Code: [`dwolla.fundingSources.*`](https://github.com/Dwolla/dwolla-v2-kotlin/blob/main/src/main/kotlin/com/dwolla/api/FundingSourcesApi.kt)

## Create a Funding Source for a Customer with On-Demand Authorization

```kotlin
dwolla.fundingSources.createForCustomer(
  customerId = "707177c3-bf15-4e7e-b37c-55c3898d9bf4",
  routingNumber = "222222226",
  accountNumber = "123456789",
  bankAccountType =  BankAccountType.CHECKING,
  name = "Jane Doe's Checking",
  onDemandAuthorizationId = "https://api-sandbox.dwolla.com/on-demand-authorizations/30e7c028-0bdf-e511-80de-0aa34a9b2388"
)
```

## Create a Plaid Funding Source for a Customer

```kotlin
dwolla.fundingSources.createForCustomer(
  customerId = "707177c3-bf15-4e7e-b37c-55c3898d9bf4",  
  plaidToken = "processor-sandbox-161c86dd-d470-47e9-a741-d381c2b2cb6f",
  name = "Jane Doe's Checking"
)
```

## Retrieve a Funding Source by ID

```kotlin
dwolla.fundingSources.get(
  id = "8a2cdc8d-629d-4a24-98ac-40b735229fe2",
)
```

## Retrieve the Balance of a Dwolla Balance Funding Source

```kotlin
dwolla.fundingSources.getBalance(
  id = "8a2cdc8d-629d-4a24-98ac-40b735229fe2",
)
```

## Retrieve a List of All Funding Sources for an Account

```kotlin
dwolla.fundingSources.listByAccount(
  listByAccount = "8a2cdc8d-629d-4a24-98ac-40b735229fe2",
)
```

## Retrieve a List of Removed Funding Sources for a Customer

```kotlin
dwolla.fundingSources.listByCustomer(
  customerId = "8a2cdc8d-629d-4a24-98ac-40b735229fe2",
  removed = true
)
```

## Update a Funding Source

```kotlin
dwolla.fundingSources.update(
  id = "707177c3-bf15-4e7e-b37c-55c3898d9bf4",
  name = "Jane Doe's Checking",
  routingNumber = "222222226",
  accountNumber = "123456789",
  bankAccountType =  BankAccountType.SAVINGS,
)
```

## Remove a Funding Sources

```kotlin
dwolla.fundingSources.remove(
  id = "8a2cdc8d-629d-4a24-98ac-40b735229fe2"
)
```

## Initiate Micro-Deposits to an Unverified Funding Source

```kotlin
dwolla.fundingSources.initiateMicroDeposits(
  id = "8a2cdc8d-629d-4a24-98ac-40b735229fe2"
)
```

## Retrieve Micro-Deposits Details

```kotlin
dwolla.fundingSources.getMicroDeposits(
  id = "8a2cdc8d-629d-4a24-98ac-40b735229fe2"
)
```

## Verify Micro-Deposits

```kotlin
dwolla.fundingSources.verifyMicroDeposits(
  id = "8a2cdc8d-629d-4a24-98ac-40b735229fe2",
  amount1 = "0.01",
  amount2 = "0.08"
)
```

