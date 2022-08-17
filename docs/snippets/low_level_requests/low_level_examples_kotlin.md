# Low-level examples (Java)

## `dwolla.get`

```java
val res: Response<MyCustomers> = dwolla.get(
    MyCustomers::class.java,
    "customers",
    Query(
        "limit" to 10,
        "offset" to 20
    )
)
```

## `dwolla.post (JsonBody)`

```java
val res1: Response<String> = dwolla.post(
    "customers",
    JsonBody(
        "firstName" to "Foo",
        "lastName" to "Bar",
        "email" to "foo@bar.com"
    )
)
val customerUrl: String = res1.headers.get("location")!!
val res2: Response<MyCustomer> = dwolla.get(MyCustomer::class.java, customerUrl)
```

## `dwolla.post (MultipartBody)`

```java
val file = File("/path/to/license.jpg")
val res: Response<String> = dwolla.post(
    "$customerUrl/documents",
    MultipartBody(
        InlineDataPart("license", "documentType"),
        FileDataPart(file, "file")
    )
)
```

## `dwolla.delete`

```java
val res: Response<String> = dwolla.delete("customers/32363a5d-4cf5-4423-99cc-de09ca3cfcd3");
```
