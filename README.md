# Dwolla V2 Kotlin Preview

Dwolla V2 Kotlin provides a [Dwolla V2 API](https://docs.dwolla.com) client for the Kotlin and Java programming languages.

Please note this library is currently a **PREVIEW**:

- A small subset of APIs are currently implemented:
  - [`dwolla.customers.{getById,list,createReceiveOnly,createUnverified,createVerifiedPersonal,createVerifiedSoleProp,createVerifiedBusiness,updateUnverified,updateVerified,updateVerifiedBusiness,upgradeToVerifiedPersonal,suspend,deactivate,reactivate,retryVerified}`](https://github.com/Dwolla/dwolla-v2-kotlin/blob/master/src/main/kotlin/com/dwolla/api/CustomersApi.kt)
  - [`dwolla.businessClassifications.list`](https://github.com/Dwolla/dwolla-v2-kotlin/blob/master/src/main/kotlin/com/dwolla/api/BusinessClassificationsApi.kt)
  - [`dwolla.documents.{createForCustomer,getById,listByCustomer}`](https://github.com/Dwolla/dwolla-v2-kotlin/blob/master/src/main/kotlin/com/dwolla/api/DocumentsApi.kt)
  - [`dwolla.root.get`](https://github.com/Dwolla/dwolla-v2-kotlin/blob/master/src/main/kotlin/com/dwolla/api/RootApi.kt)).
- Breaking changes could be introduced as we gather your [feedback](https://github.com/Dwolla/dwolla-v2-kotlin/issues).

## Installation

#### Maven

Add this to your project's POM:

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

```xml
<dependency>
    <groupId>com.github.Dwolla</groupId>
    <artifactId>dwolla-v2-kotlin</artifactId>
    <version>master-SNAPSHOT</version>
</dependency>
```

#### Gradle

Add this to your project's build file:

```groovy
repositories {
    // ...
    maven(url = "https://jitpack.io") {
        name = "jitpack"
    }
}
```

```groovy
dependencies {
    implementation("com.github.Dwolla:dwolla-v2-kotlin:master-SNAPSHOT")
}
```

## Getting Started

First, let's set up a Dwolla client using our application key and secret.

###### Kotlin

```kotlin
import com.dwolla.Client
import com.dwolla.Environment

val dwolla = Client(
    key = "yourClientKey",       // see dashboard.dwolla.com
    secret = "yourClientSecret", // for your client credentials
    environment = Environment.SANDBOX
)
```

###### Java

```java
import com.dwolla.Client;
import com.dwolla.Environment;

Client dwolla = new Client(
    "yourClientKey",    // see dashboard.dwolla.com
    "yourClientSecret", // for your client credentials
    Environment.SANDBOX
);
```

## Making requests

Dwolla V2 Kotlin is experimenting with providing higher-level APIs
in addition to the lower-level APIs found in our
[existing SDKs](https://docs.dwolla.com/#sdk-support).

These methods are intended to make the SDK more self-documenting by providing
information developers would typically refer to the docs for (such as request
parameters) in the SDK itself.

Below, we'll take a look at creating an unverified customer using the high-level
APIs compared to the lower-level APIs.

#### High-level example

###### Kotlin

```kotlin
val customer = dwolla.customers.createUnverified(
    firstName = "First",
    lastName = "Last",
    email = "first.last@gmail.com",
    idempotencyKey = "h532jk"
)
```

###### Java

```java
Customer customer = dwolla.customers.createUnverified(
    "First",
    "Last",
    "first.last@gmail.com",
    null,
    null,
    "h532jk"
);
```

#### Low-level example

###### Kotlin

```kotlin
val createCustomer: Response<String> = dwolla.post("customers",
    JsonBody(
        "firstName" to "First",
        "lastName" to "Last",
        "email" to "first.last@gmail.com"
    ),
    Headers("idempotency-key" to "h532jk")
)

val getCustomer: Response<Customer> = dwolla.get(Customer::class.java, createCustomer.headers.get("location")!!)
getCustomer.statusCode // 200
getCustomer.headers // Headers
getCustomer.body // Customer
```

###### Java

```java
Response<String> createCustomer = dwolla.post("customers",
    new JsonBody()
        .add("firstName", "First")
        .add("lastName", "Last")
        .add("email", "first.last@gmail.com"),
    new Headers()
        .add("idempotency-key", "h532jk")
);

Response<Customer> getCustomer = dwolla.get(Customer.class, createCustomer.headers.get("location"));
Integer statusCode = getCustomer.statusCode; // 200
Headers headers = getCustomer.headers; // Headers
Customer customer = getCustomer.body; // Customer
```

## Handling errors

Requests made with Dwolla V2 Kotlin throw two types of exceptions:

- `DwollaException`: Thrown when a request is unsuccessful. This could occur for a variety of reasons such as
  invalid request parameters. Details can be found in the exception's `DwollaError` object.
- `OAuthException`: Thrown when an error occurs obtaining a new token. You should not encounter this exception
  unless your `Client` key and/or secret is incorrect.

###### Kotlin

```kotlin
try {
    dwolla.customers.list()
} catch (e: DwollaException) {
    e.message // String
    e.statusCode // Int
    e.headers // Headers
    e.error // DwollaError
} catch (e: OAuthException) {
    e.message // String
    e.statusCode // Int
    e.headers // Headers
    e.error // OAuthError
}
```

###### Java

```java
try {
    Client dwolla = new Client("key", "secret", Environment.SANDBOX);
    dwolla.customers.list(null, null, null, null);
} catch (DwollaException e) {
    String message = e.message;
    Integer statusCode = e.statusCode;
    Headers headers = e.headers;
    DwollaError error = e.error;
} catch (OAuthException e) {
    String message = e.message;
    Integer statusCode = e.statusCode;
    Headers headers = e.headers;
    OAuthError error = e.error;
}
```

## Feedback

As mentioned previously, the Dwolla V2 Kotlin SDK is currently provided as
a preview to developers so we can gather feedback prior to release.

If you have any feedback feel free to reach out to us or
[create an issue](https://github.com/Dwolla/dwolla-v2-kotlin/issues).
