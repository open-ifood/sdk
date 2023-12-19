# Order 

Some API endpoints and reverse engineering information.

## get order status

### Status Enum

```java
enum Status {
    /** The First status, order created */
    PLACED,
    
    /** When the merchant receive the order and approve it */
    CONFIRMED,
    
    /** When the deliveryman get the order */
    DISPATCHED
}
```

```bash
curl --location 'https://marketplace.ifood.com.br/v1/customers/me/orders/95b156e7-9f62-4a66-a5b2-0a28350bc85c/events' \
--header 'Authorization: Bearer ACCESS_TOKEN'
```


## get delivery confirmation code

When the deliveryman will leave the order in client location, then the client will need the confirmation code.

```bash
curl --location 'https://marketplace.ifood.com.br/v1/customers/me/orders/95b156e7-9f62-4a66-a5b2-0a28350bc85c/events' \
--header 'Authorization: Bearer ACCESS_TOKEN'
```

```javascript
const events = pm.response.json()

const code = events.filter(event => event.value === 'HANDSHAKE_CODE_VALUE')
    .map(event => event.metadata.handshakeCode)[0]

console.log(code);
```