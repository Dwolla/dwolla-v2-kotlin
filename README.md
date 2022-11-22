# Dwolla SDK for Kotlin

This repository contains the source code for Dwolla's Kotlin-based SDK, which allows developers to interact with Dwolla's server-side API via a Kotlin or JAVA API. Any action that can be performed via an HTTP request can be made using this SDK when executed within a server-side environment.

**Dwolla’s Kotlin SDK is intended for server-side use and should not be used by itself when developing client-side Android apps. Instead, this SDK should be installed on your web server with your Android app proxying any interaction with Dwolla through it.**

## Table of Contents

- [Getting Started](#getting-started)
  - [Installation](#installation)
  - [Initialization](#initialization)
- [Making Requests](#making-requests)
  - [High-Level Requests](#high-level-requests)
  - [Low-Level Requests](#low-level-requests)
- [Handling Errors](#handling-errors)
- [Changelog](#changelog)
- [Community](#community)
- [Docker](#docker)
- [Additional Resources](#additional-resources)

## Getting Started

### Installation

To begin using this SDK, you will first need to download it to your machine. You can use Maven or Gradle to do so, depending on which build tool your project is using.

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
    <version>0.7.0/version>
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
    implementation("com.github.Dwolla:dwolla-v2-kotlin:0.7.0")
}
```

### Initialization

Before any API requests can be made, you must first determine which environment you will be using, as well as fetch the application key and secret. To fetch your application key and secret, please visit one of the following links:

- Production: https://dashboard.dwolla.com/applications
- Sandbox: https://dashboard-sandbox.dwolla.com/applications

Finally, you can create an instance of `Dwolla` with `key` and `secret` replaced with the application key and secret that you fetched from one of the aforementioned links, respectively.

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

The Dwolla client provides high-level and low-level methods for interacting with the Dwolla API.

### High-Level Requests

> The best SDKs are not just simple; they’re intuitive. Developers would rather stay in the flow of their code than
> troubleshoot back-and-forth trying to figure out someone else’s code. Luckily, statically typed languages let us
> include information typically found in docs within type signatures.
>
> &mdash; [Taking Our SDKs Higher](https://www.dwolla.com/updates/improving-sdks/)

While the low-level methods are all you need, high-level methods exist to make things easier. They embed information
you would typically refer to the docs for in the SDK itself such as endpoints, request parameters, and response parameters.

As of now, a subset of the Dwolla API has high-level methods available:

- [x] [`dwolla.accounts.*`](https://github.com/Dwolla/dwolla-v2-kotlin/blob/main/docs/snippets/high_level_requests/accounts.md)
- [x] [`dwolla.beneficialOwners.*`](https://github.com/Dwolla/dwolla-v2-kotlin/blob/main/docs/snippets/high_level_requests/beneficial-owners.md)
- [x] [`dwolla.businessClassifications.*`](https://github.com/Dwolla/dwolla-v2-kotlin/blob/main/docs/snippets/high_level_requests/business-classifications.md)
- [x] [`dwolla.customers.*`](https://github.com/Dwolla/dwolla-v2-kotlin/blob/main/docs/snippets/high_level_requests/customers.md)
- [x] [`dwolla.documents.*`](https://github.com/Dwolla/dwolla-v2-kotlin/blob/main/docs/snippets/high_level_requests/documents.md)
- [x] [`dwolla.fundingSources.*`](https://github.com/Dwolla/dwolla-v2-kotlin/blob/main/docs/snippets/high_level_requests/funding-sources.md)
- [x] [`dwolla.fundingSourcesTokens.*`](https://github.com/Dwolla/dwolla-v2-kotlin/blob/main/docs/snippets/high_level_requests/funding-sources-tokens.md)
- [x] [`dwolla.root.*`](https://github.com/Dwolla/dwolla-v2-kotlin/blob/main/docs/snippets/high_level_requests/root.md)
- [ ] `dwolla.events.*`
- [ ] `dwolla.labels.*`
- [ ] `dwolla.massPayments.*`
- [ ] `dwolla.transfers.*`
- [ ] `dwolla.webhooks.*`
- [ ] `dwolla.webhookSubscriptions.*`

### Low-Level Requests

To make low-level HTTP requests, you can use the `get()`, `post()`, and `delete()` methods.

- `dwolla.get`
- `dwolla.post`
- `dwolla.delete`

Examples:

- [Kotlin](docs/snippets/low_level_requests/low_level_examples_kotlin.md)
- [Java](docs/snippets/low_level_requests/low_level_examples_java.md)

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

##### Kotlin

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

##### Java

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

## Changelog

- [**0.7.0**](https://github.com/Dwolla/dwolla-v2-kotlin/releases/tag/0.7.0)
  - Add Exchanges and Exchange Partners high-level API methods
- [**0.6.1**](https://github.com/Dwolla/dwolla-v2-kotlin/releases/tag/0.6.1)
  - Add `firstName`, `lastName` and `dateOfBirth` as required arguments when upgrading an Unverified Customer to a Personal Verified Customer.
- [**0.6.0**](https://github.com/Dwolla/dwolla-v2-kotlin/releases/tag/0.6.0)
  - Add `correlationId` optional argument when creating a new customer
  - Add `ForeignPassportNotAllowed` document failure reason. This is thrown when a foreign (non-U.S.) passport is uploaded for a personal Verified Customer.
- [**0.5.0**](https://github.com/Dwolla/dwolla-v2-kotlin/releases/tag/0.5.0)
  - Configure Dwolla environment to be more flexible to configuration
- [**0.4.0**](https://github.com/Dwolla/dwolla-v2-kotlin/releases/tag/0.4.0)
  - Updated `src/main/kotlin/com/dwolla/resource/documents/DocumentFailureReason.kt` to match failure reasons in [API Reference](https://developers.dwolla.com/api-reference/documents)
  - Update `gradle` from 5.3.1 to 7.3.1
- **0.3.0**
  - Updated `CutomerApi` to include SSN when upgrading a customer to verified
- [**0.2.0**](https://github.com/Dwolla/dwolla-v2-kotlin/releases/tag/0.2.0)
  - Add `DwollaException` base exception class
  - Swallow and rethrow exceptions using `DwollaException`
- [**0.1.2**](https://github.com/Dwolla/dwolla-v2-kotlin/releases/tag/0.1.2)
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

## Community

- If you have any feedback, please reach out to us on [our forums](https://discuss.dwolla.com/) or by [creating a GitHub issue](https://github.com/Dwolla/dwolla-v2-kotlin/issues/new).
- If you would like to contribute to this library, [bug reports](https://github.com/Dwolla/dwolla-v2-kotlin/issues) and [pull requests](https://github.com/Dwolla/dwolla-v2-kotlin/pulls) are always appreciated!

## Docker

If you prefer to use Docker to run dwolla-v2-kotlin locally, a Dockerfile file is included in the root directory. You can either build the Docker image with your API key and secret (by passing the values via CLI), or you can specify the values for the `app_key` and `app_secret` build arguments in Dockerfile. Finally, you will need to build and run the Docker image. More information on this topic can be found on [Docker's website](https://docs.docker.com/build/hellobuild/), or you can find some example commands below.

##### Building Docker Container

```shell
# Building container by specifying build arguments.
# In this configuration, you will not need to modify Dockerfile. All of the
# necessary arguments are passed via Docker's `--build-arg` option.
$ docker build \
    --build-arg app_key=YOUR_API_KEY \
    --build-arg app_secret=YOUR_APP_SECRET \
    -t dwolla/kotlin:latest .
    
# Building container without specifying build arguments.
# In this configuration, you will need to specify your account API key and 
# secret (retrieved from Dwolla) in the Dockerfile file.
$ docker build -t dwolla/kotlin:latest .
```

## Additional Resources

To learn more about Dwolla and how to integrate our product with your application, please consider visiting the following resources and becoming a member of our community!

- [Dwolla](https://www.dwolla.com/)
- [Dwolla Developers](https://developers.dwolla.com/)
- [SDKs and Tools](https://developers.dwolla.com/sdks-tools)
  - [Dwolla SDK for C#](https://github.com/Dwolla/dwolla-v2-csharp)
  - [Dwolla SDK for Node](https://github.com/Dwolla/dwolla-v2-node)
  - [Dwolla SDK for Python](https://github.com/Dwolla/dwolla-v2-python)
  - [Dwolla SDK for PHP](https://github.com/Dwolla/dwolla-swagger-php)
  - [Dwolla SDK for Ruby](https://github.com/Dwolla/dwolla-v2-ruby)
- [Developer Support Forum](https://discuss.dwolla.com/)
