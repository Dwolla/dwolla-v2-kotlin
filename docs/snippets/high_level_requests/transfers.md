# High-Level Examples (Transfers)
Source Code: [`dwolla.transfers.*`](https://github.com/Dwolla/dwolla-v2-kotlin/blob/main/src/main/kotlin/com/dwolla/api/TransfersApi.kt)

## Create a Transfer (using funding source IDs)

```kotlin
dwolla.transfers.create(
    sourceFundingSourceId = "b9c87f1d-1cb2-4bee-8c88-7910f7d9ee3f",
    destinationFundingSourceId = "c9c87f1d-1cb2-4bee-8c88-7910f7d9ee3f",
    amount = Amount("10.00", "USD"),
    metadata = mapOf("paymentId" to "12345"),
    correlationId = "8a2cdc8d-629d-4a24-98ac-40b735229fe2"
)
```

## Create a Transfer (using FundingSource and Fee objects)

```kotlin
dwolla.transfers.create(
    sourceFundingSource = sourceFundingSource,
    destinationFundingSource = destinationFundingSource,
    amount = Amount("10.00", "USD"),
    metadata = mapOf("paymentId" to "12345"),
    fees = arrayOf(
        Fee(Amount("1.00", "USD"))
    ),
    clearing = Clearing(
        destination = "next-available"
    ),
    correlationId = "8a2cdc8d-629d-4a24-98ac-40b735229fe2",
    idempotencyKey = "19051a62-3403-11e6-ac61-9e71128cae77"
)
```

## Retrieve a Transfer by ID

```kotlin
dwolla.transfers.get("74c9129b-d14a-e511-80da-0aa34a9b2388")
```

## Get Transfer Failure Reason

```kotlin
dwolla.transfers.getFailureReason("74c9129b-d14a-e511-80da-0aa34a9b2388")
```

## List Transfer Fees

```kotlin
dwolla.transfers.listFees("74c9129b-d14a-e511-80da-0aa34a9b2388")
```

## List Transfers for an Account

```kotlin
dwolla.transfers.listForAccount(
    accountId = "ca32853c-48fa-40be-ae75-77b37504581b"
)
```

## List Transfers for an Account (including all optional query parameters)

```kotlin
dwolla.transfers.listForAccount(
    accountId = "ca32853c-48fa-40be-ae75-77b37504581b",
    search = "Jane",
    startAmount = Amount("10.00", "USD"),
    endAmount = Amount("100.00", "USD"),
    startDate = "2023-01-01",
    endDate = "2023-12-31",
    status = TransferStatus.PENDING,
    correlationId = "8a2cdc8d-629d-4a24-98ac-40b735229fe2",
    limit = 25,
    offset = 0
)
```

## List Transfers for a Customer 

```kotlin
dwolla.transfers.listForCustomer(
    customerId = "ca32853c-48fa-40be-ae75-77b37504581b"
)
```

## List Transfers for a Customer (including all optional query parameters)

```kotlin
dwolla.transfers.listForCustomer(
    customerId = "ca32853c-48fa-40be-ae75-77b37504581b",
    search = "Payment",
    startAmount = Amount("50.00", "USD"),
    endAmount = Amount("500.00", "USD"),
    startDate = "2023-06-01",
    endDate = "2023-06-30",
    status = TransferStatus.PROCESSED,
    correlationId = "8a2cdc8d-629d-4a24-98ac-40b735229fe2",
    limit = 10,
    offset = 20
)
```

## Cancel a Transfer

```kotlin
dwolla.transfers.cancel("74c9129b-d14a-e511-80da-0aa34a9b2388")
```




