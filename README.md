# Dwolla V2 Kotlin

Dwolla V2 Kotlin provides a [Dwolla V2 API](https://docs.dwolla.com) client for the **Kotlin** and **Java**
programming languages.

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
    <version>0.3.0</version>
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
    implementation("com.github.Dwolla:dwolla-v2-kotlin:0.2.0")
}
```

## Getting Started

Before making requests, you'll need to set up a Dwolla client using your app credentials.

Your app credentials can be found on the applications page of the Dwolla Dashboard
([sandbox](https://dashboard-sandbox.dwolla.com/applications),
[production](https://dashboard.dwolla.com/applications)).

#### Kotlin

```kotlin
import com.dwolla.Dwolla
import com.dwolla.DwollaEnvironment

val dwolla = Dwolla(
    key = "YOUR_APP_KEY",
    secret = "YOUR_APP_SECRET",
    environment = DwollaEnvironment.SANDBOX // defaults to PRODUCTION
)
```

#### Java

```java
import com.dwolla.Dwolla;
import com.dwolla.DwollaEnvironment;

Dwolla dwolla = new Dwolla(
    "YOUR_APP_KEY",
    "YOUR_APP_SECRET",
    DwollaEnvironment.SANDBOX // defaults to PRODUCTION
);
```

## Making Requests

The Dwolla client provides **low-level** and **high-level** methods for interacting with the Dwolla API.

### Low-level methods

The low-level methods `get`, `post`, and `delete` send HTTP requests to the Dwolla API using your app credentials.
These methods are all you need to use the Dwolla API. You can refer to the [Dwolla API Docs](https://docs.dwolla.com/)
for information on supported endpoints, request parameters, and response parameters.

- `dwolla.get`
- `dwolla.post`
- `dwolla.delete`

Examples:
- [Kotlin](docs/low_level_examples_kotlin.md)
- [Java](docs/low_level_examples_java.md)

### High-level methods (🚧 Under construction)

> The best SDKs are not just simple; they’re intuitive. Developers would rather stay in the flow of their code than
> troubleshoot back-and-forth trying to figure out someone else’s code. Luckily, statically typed languages let us
> include information typically found in docs within type signatures.
>
> &mdash; [Taking Our SDKs Higher](https://www.dwolla.com/updates/improving-sdks/)

While the low-level methods are all you need, high-level methods exist to make things easier. They embed information
you would typically refer to the docs for in the SDK itself such as endpoints, request parameters, and response parameters.

As of now, a subset of the Dwolla API has high-level methods available:

- [x] [`dwolla.accounts.*`](https://github.com/Dwolla/dwolla-v2-kotlin/blob/master/src/main/kotlin/com/dwolla/api/AccountsApi.kt)
- [x] [`dwolla.beneficialOwners.*`](https://github.com/Dwolla/dwolla-v2-kotlin/blob/master/src/main/kotlin/com/dwolla/api/BeneficialOwnersApi.kt)
- [x] [`dwolla.businessClassifications.*`](https://github.com/Dwolla/dwolla-v2-kotlin/blob/master/src/main/kotlin/com/dwolla/api/BusinessClassificationsApi.kt)
- [x] [`dwolla.customers.*`](https://github.com/Dwolla/dwolla-v2-kotlin/blob/master/src/main/kotlin/com/dwolla/api/CustomersApi.kt)
- [x] [`dwolla.documents.*`](https://github.com/Dwolla/dwolla-v2-kotlin/blob/master/src/main/kotlin/com/dwolla/api/DocumentsApi.kt)
- [x] [`dwolla.fundingSources.*`](https://github.com/Dwolla/dwolla-v2-kotlin/blob/master/src/main/kotlin/com/dwolla/api/FundingSourcesApi.kt)
- [x] [`dwolla.fundingSourcesTokens.*`](https://github.com/Dwolla/dwolla-v2-kotlin/blob/master/src/main/kotlin/com/dwolla/api/FundingSourcesTokensApi.kt)
- [x] [`dwolla.iavTokens.*`](https://github.com/Dwolla/dwolla-v2-kotlin/blob/master/src/main/kotlin/com/dwolla/api/IavTokensApi.kt)
- [x] [`dwolla.root.*`](https://github.com/Dwolla/dwolla-v2-kotlin/blob/master/src/main/kotlin/com/dwolla/api/RootApi.kt)
- [ ] `dwolla.events.*`
- [ ] `dwolla.labels.*`
- [ ] `dwolla.massPayments.*`
- [ ] `dwolla.transfers.*`
- [ ] `dwolla.webhooks.*`
- [ ] `dwolla.webhookSubscriptions.*`

## Handling errors

Dwolla V2 Kotlin has 3 types of exceptions:

```
DwollaException
├── DwollaApiException
└── DwollaAuthException
```

- `DwollaApiException`: Thrown when the Dwolla API returns an error response. This could occur
  for a variety of reasons such as invalid request parameters.
- `DwollaAuthException`: Thrown when an error occurs obtaining authenticating with the API. You should not encounter
  this exception unless your `Dwolla` key/secret are incorrect.
- `DwollaException`: The base class other exceptions inherit from.

#### Kotlin

```kotlin
try {
    dwolla.customers.list()
} catch (e: DwollaApiException) {
    e.message // String
    e.statusCode // Int
    e.headers // Headers
    e.error // DwollaError
} catch (e: DwollaAuthException) {
    e.message // String
    e.statusCode // Int
    e.headers // Headers
    e.error // OAuthError
} catch (e: DwollaException) {
    e.message // String
    e.cause // Throwable?
}
```

#### Java

```java
try {
    dwolla.customers.list();
} catch (DwollaApiException e) {
    String message = e.message;
    Integer statusCode = e.statusCode;
    Headers headers = e.headers;
    DwollaError error = e.error;
} catch (DwollaAuthException e) {
    String message = e.message;
    Integer statusCode = e.statusCode;
    Headers headers = e.headers;
    OAuthError error = e.error;
} catch (DwollaAuthException e) {
    String message = e.message;
    Throwable cause = e.cause;
}
```

## Feedback

If you have any feedback please [reach out](https://discuss.dwolla.com/) to us or
[create an issue](https://github.com/Dwolla/dwolla-v2-kotlin/issues).

## Changelog

- **0.2.0**
  - Add `DwollaException` base exception class
  - Swallow and rethrow exceptions using `DwollaException`
- **0.1.2**
  - Add `delete` methods to `DwollaClient`
- **0.1.1**
  - Add serializer for `JsonBody` ([#13](/Dwolla/dwolla-v2-kotlin/pull/13))
- **0.1.0**
  - Refactoring
    - `Client` => `Dwolla`
    - `Environment` => `DwollaEnvironment`
    - `DwollaException` => `DwollaApiException`
    - `OAuthException` => `DwollaAuthException`
  - Add OpenID support
    - `dwolla.auth()`
    - `dwolla.token()`
    - `dwolla.refreshToken()`
  - Additional high-level APIs
    - `dwolla.accounts.*`
    - `dwolla.beneficialOwners.*`
    - `dwolla.fundingSources.*`
    - `dwolla.fundingSourcesTokens.*`
    - `dwolla.iavTokens.*`
  - Refactored high-level APIs
- **0.1.0-pre1**
  - Initial release
