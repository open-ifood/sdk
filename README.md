# OpenIFood SDK

An unofficial iFood SDK that exposes the following functionalities.

## Summary

- [Functionalities](#functionalities)
- [Installation](#installation)
- [Complete documentation](#documentation)

## Functionalities

- Authentication
    - Send auth code
    - Confirm auth code
    - Refresh access token
- Address
    - List
    - Geocode
    - Create
- Merchant
    - List merchants by filters
        - Payment type
        - Geolocation (latitude & longitude)
        - Sort (asc:desc:field)
    - Get current merchant catalog
- Order
    - Checkout a new order
    - Get order events
    - Get order status

## Installation

The SDK library are published at Maven Central Repository, for using this in your project add the 
following dependency on your `pom.xml` or `build.gradle`.

Check the latest version at [Maven Central Repository](https://central.sonatype.com/artifact/io.github.open-ifood/sdk)

### Maven

```xml
<dependency>
    <groupId>io.github.open-ifood</groupId>
    <artifactId>sdk</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Gradle

```kotlin
implementation 'io.github.open-ifood:sdk:1.0.0'
```

## Documentation

Access [gitbook](https://openifood.gitbook.io/open-ifood-sdk/) for the complete documentation and getting 
started guide.