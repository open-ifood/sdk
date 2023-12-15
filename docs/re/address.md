# Address

## Summary

- [Geocode Address V1](#geocode-address-v1)

## Geocode Address V1

Geocode an address line to specific address for registering at iFood.

```bash
curl --location 'https://marketplace.ifood.com.br/v1/addresses:geocode?query=rua%20coronel' \
--header 'x-application-key: 54z2laLEcZ0gzfERl27dEu1N'
```

## Geocode Address (Logistics)

```bash
curl --location 'https://logistics-api.ifood.com.br/location/v1/addresses:geocode?query=Ramal%2BBujari%2C%2BBujari%2B-%2BState%2Bof%2BAcre%2C%2BBrazil' \
--header 'x-application-key: Zw@W#GbKp@UyXMYcBTsZFavt3'
```

## List Addresses

For get the list of created addresses.

```bash
curl --location 'https://marketplace.ifood.com.br/v1/customers/me/addresses' \
--header 'Authorization: Bearer ACCESS_TOKEN'
```

## Create Address

Create a new address for an account.

```bash
curl --location 'https://marketplace.ifood.com.br/v1/customers/me/addresses' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer ACCESS_TOKEN' \
--data '{
    "city": "Bujari",
    "state": "AC",
    "country": "BR",
    "streetName": "Ramal Bujari asd",
    "streetNumber": "7156",
    "neighborhood": "Centro",
    "coordinates": {
        "latitude": -9.819030699350904,
        "longitude": -67.94686819630353
    },
    "postalCode": 0,
    "reference": "ponto referencia",
    "complement": "complemento",
    "provider": "GOOGLE"
}'
```
