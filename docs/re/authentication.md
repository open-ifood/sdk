# authentication

These documents provide a reverse engineering analysis in
auth module.

## Summary

- [Types](#authentication-types)
- [OTP Authentication](#otp-authentication)
- [oAuth2 Authentication](#oauth2-authentication)

## Authentication Types

- OTP (One Time Password)
  - SMS
  - Email
  - WhatsApp
- oAuth 2
  - Google
  - Facebook
  - Apple

## 1. Defining an authentication method

Evaluate the following request for getting the available list of identity providers.

```bash
curl 'https://marketplace.ifood.com.br/v4/identity-providers' \
  -H 'authority: marketplace.ifood.com.br' \
  -H 'accept: application/json, text/plain, */*' \
  -H 'accept-language: pt-BR,pt;q=1' \
  -H 'app_version: 9.102.21' \
  -H 'browser: Mac OS' \
  -H 'cache-control: no-cache, no-store' \
  -H 'content-type: application/json' \
  --data-raw '{"tenant_id":"IFO"}' \
  --compressed
```

With the following response, select an authentication method to be used.

```json
[
    {
        "name": "GOOGLE"
    },
    {
        "name": "FACEBOOK"
    },
    {
        "name": "APPLE"
    },
    {
        "name": "OTP_EMAIL"
    },
    {
        "name": "OTP_WHATSAPP"
    },
    {
        "name": "OTP_SMS"
    }
]
```

## OTP Authentication

One Time Password authentication method, will send a temporary code to the selected provider (SMS, 
WhatsApp, E-mail) and you will check the code received sending to iFood.

### 1. Request a new authorization code

#### 1.A. E-mail

```bash
curl 'https://marketplace.ifood.com.br/v2/identity-providers/OTP/authorization-codes' \
  -H 'authority: marketplace.ifood.com.br' \
  -H 'accept: application/json, text/plain, */*' \
  -H 'X-Ifood-Device-Id: UUIDV4' \
  -H 'accept-language: pt-BR,pt;q=1' \
  -H 'app_version: 9.102.21' \
  -H 'cache-control: no-cache, no-store' \
  -H 'content-type: application/json' \
  --data-raw '{"tenant_id":"IFO","email":"example@example.com","type":"EMAIL"}' \
  --compressed
```

--- 

#### 1.B. WhatsApp

```bash
curl --location 'https://marketplace.ifood.com.br/v2/identity-providers/OTP/authorization-codes' \
--header 'authority: marketplace.ifood.com.br' \
--header 'accept: application/json, text/plain, */*' \
--header 'accept-language: pt-BR,pt;q=1' \
--header 'X-Ifood-Device-Id: UUIDV4' \
--header 'app_version: 9.102.21' \
--header 'cache-control: no-cache, no-store' \
--header 'content-type: application/json' \
--data '{
    "tenant_id": "IFO",
    "phone": {
        "country_code": 55,
        "area_code": 15,
        "number": "999999999"
    },
    "type": "WHATSAPP"
}'
```

---

#### 1.C. SMS

[WIP: Pretty soon...](https://github.com/open-ifood/sdk/issues/2)

---

And then, as the response, we receive an authorization key and the specified timeout for requesting a new 
auth code.

Store the key, it will be used in the [verification step](#2-verify-the-auth-code)

```json
{
    "key": "AUTH_KEY",
    "timeout_in_seconds": 60
}
```

### 2. Verify the auth code

Now, check your OTP provider (SMS, WhatsApp, or E-mail) and read the received code, and finally authenticate
sending the following request.

```bash
curl 'https://marketplace.ifood.com.br/v2/identity-providers/OTP/access-tokens' \
  -H 'authority: marketplace.ifood.com.br' \
  -H 'accept: application/json, text/plain, */*' \
  -H 'accept-language: pt-BR,pt;q=1' \
  -H 'browser: Mac OS' \
  -H 'cache-control: no-cache, no-store' \
  -H 'content-type: application/json' \
  -H 'origin: https://www.ifood.com.br' \
  --data-raw '{"key":"AUTH_KEY","auth_code":"AUTH_CODE"}' \
  --compressed
```

And then, the server will return the `access_token=TEMP_ACCESS_TOKEN` used in [Generate Access Token](#3-generate-access-token)

```json
{"access_token":"TEMP_ACCESS_TOKEN"}
```

### 2.1. Challenge verification (Optional)

If the iFood suspect from your login because too much retry, it will require you to verify some other 
information.

[WIP: In construction](https://github.com/open-ifood/sdk/issues/3)/

### 3. Generate Access token

#### 3.1. E-mail

```bash
curl 'https://marketplace.ifood.com.br/v3/identity-providers/OTP/authentications' \
  -H 'authority: marketplace.ifood.com.br' \
  -H 'accept: application/json, text/plain, */*' \
  -H 'accept-language: pt-BR,pt;q=1' \
  -H 'account_id: de5659e1-dbbc-4c30-9188-c327e815002d' \
  -H 'app_version: 9.102.21' \
  -H 'browser: Mac OS' \
  -H 'cache-control: no-cache, no-store' \
  -H 'content-type: application/json' \
  -H 'x-device-model: Macintosh Chrome' \
  --data-raw '{"tenant_id":"IFO","token":"TEMP_ACCESS_TOKEN",
  "device_id":"UUIDV4",
  "email":"example@example.com"}' \
  --compressed
```

And then, the access token to authorize will be received at response.

```json
{
    "authenticated": true,
    "account_id": "UUIDV4",
    "access_token": "JWT_TOKEN",
    "refresh_token": "JWT_TOKEN",
    "external_id": null
}
```

## oAuth2 Authentication

[WIP: Pretty soon...](https://github.com/open-ifood/sdk/issues/1)