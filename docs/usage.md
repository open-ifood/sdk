# SDK usage - Definition

## User - authentication module

```js
// -> request auth code
UserAuthenticationSession = IFoodClient.user()
    .auth()
    .authenticate(
        AuthRequest.builder()
            // automatically refresh access token option
            // fields here
            .build()
    )

// confirm auth code received
UserSession = UserAuthenticationSession.confirm("001010")

// force refresh token
UserSession.refresh(accessToken)
```

## User - address 

```js
// list all user address
UserSession.address()
    .list()

// geocode address line
UserSession.address()
    .geocode("Street fulano de tal, XX. City ciclano")

// create address
UserSession.address()
    .create(
        UserAddress.builder()
            // user address fields
            .build()
    )
```

## User - merchant

```js
// list merchants by filter
ListMerchant = UserSession.merchant()
    .list(
        ListMerchantFilter.builder()
            // merchant filter's
            .build()
    )

// get current merchant catalog
MerchantCatalog = UserSession.merchant()
    .catalog()
```

## User - order 

```js
// checkout order
OrderCheckout = UserSession.order()
    .checkout(
        OrderCheckout.builder()
            // checkout fields (reuse DTO's from GET responses)
            .build()
    )

// get order status
OrderStatus = UserSession.order()
    .status(OrderId)

OrderEvents = UserSession.order()
    .events(OrderId)
```

