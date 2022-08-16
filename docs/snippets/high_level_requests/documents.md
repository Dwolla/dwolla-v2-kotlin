# High-Level Examples (Documents)
Source Code: [`dwolla.documents.*`](https://github.com/Dwolla/dwolla-v2-kotlin/blob/main/src/main/kotlin/com/dwolla/api/DocumentsApi.kt)

## Create a Document for a Customer

```kotlin
dwolla.documents.createForCustomer(
  customerId = "707177c3-bf15-4e7e-b37c-55c3898d9bf4",  
  documentType = DocumentType.LICENSE, 
  file = File
)
```

## Create a Document for a Beneficial Owner

```kotlin
dwolla.documents.createForBeneficialOwner(
  customerId = "707177c3-bf15-4e7e-b37c-55c3898d9bf4",  
  documentType = DocumentType.ID_CARD, 
  file = File
)
```

## Retrieve a Document by ID

```kotlin
dwolla.documents.get(
  id = "8a2cdc8d-629d-4a24-98ac-40b735229fe2",
)
```

## Retrieve a List of Documents for a Customer

```kotlin
dwolla.documents.listByCustomer(
    customerId = "8a2cdc8d-629d-4a24-98ac-40b735229fe2",
)
```

## Retrieve a List of Documents for a Beneficial Owner

```kotlin
dwolla.documents.listByBeneficialOwner(
    beneficialOwnerId = "8a2cdc8d-629d-4a24-98ac-40b735229fe2",
)
```
