# Low-level examples (Java)

## `GET /customers?limit=10&offset=20`

```java
Response<MyCustomers> res = dwolla.get(
    MyCustomers.class,
    "customers",
    new Query()
        .add("limit", 10)
        .add("offset", 20)
);
```

## `POST /customers`

```java
Response<String> res1 = dwolla.post(
    "customers",
    new JsonBody()
        .add("firstName", "Foo")
        .add("lastName", "Bar")
        .add("email", "foo@bar.com")
);
String customerUrl = res1.headers.get("location");
Response<MyCustomer> res2 = dwolla.get(MyCustomer.class, customerUrl);
```

## `POST /customers/:id/documents`

```java
File file = new File("/path/to/license.jpg");
Response<String> res = dwolla.post(
    customerUrl + "/documents",
    new MultipartBody(
        new InlineDataPart("license", "documentType"),
        new FileDataPart(file, "file")
    )
);
```

## `DELETE /customers/:id`

```java
dwolla.delete("customers/32363a5d-4cf5-4423-99cc-de09ca3cfcd3");
```
