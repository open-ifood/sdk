# Product

Documents related to products.

## Get categories

```bash
curl --location 'https://marketplace.ifood.com.br/v2/home?latitude=-22.024173&longitude=-47.894354&channel=IFOOD&size=20&alias=HOME_FOOD_DELIVERY' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer ACCESS_TOKEN' \
--data '{
    "supported-headers": [
        "OPERATION_HEADER"
    ],
    "supported-cards": [
        "MERCHANT_LIST",
        "CATALOG_ITEM_LIST",
        "CATALOG_ITEM_LIST_V2",
        "CATALOG_ITEM_LIST_V3",
        "FEATURED_MERCHANT_LIST",
        "CATALOG_ITEM_CAROUSEL",
        "CATALOG_ITEM_CAROUSEL_V2",
        "CATALOG_ITEM_CAROUSEL_V3",
        "BIG_BANNER_CAROUSEL",
        "IMAGE_BANNER",
        "MERCHANT_LIST_WITH_ITEMS_CAROUSEL",
        "SMALL_BANNER_CAROUSEL",
        "NEXT_CONTENT",
        "MERCHANT_CAROUSEL",
        "MERCHANT_TILE_CAROUSEL",
        "SIMPLE_MERCHANT_CAROUSEL",
        "INFO_CARD",
        "MERCHANT_LIST_V2",
        "ROUND_IMAGE_CAROUSEL",
        "BANNER_GRID",
        "MEDIUM_IMAGE_BANNER",
        "MEDIUM_BANNER_CAROUSEL",
        "RELATED_SEARCH_CAROUSEL",
        "ADS_BANNER"
    ],
    "supported-actions": [
        "catalog-item",
        "merchant",
        "page",
        "card-content",
        "last-restaurants",
        "webmiddleware",
        "reorder",
        "search",
        "groceries",
        "home-tab"
    ],
    "feed-feature-name": "",
    "faster-overrides": ""
}'
```

And then, evaluate the following script for getting the categories.

```js
const categories = response?.sections
    ?.filter(section => section.type === "CARDS")[0]
    ?.cards
    ?.filter(card => card.cardType === "SMALL_BANNER_CAROUSEL")[0]
    ?.data
    ?.contents;
```

So, the response will be.

```json
[
  {
    "action": "page?identifier=3e440c89-651a-48af-a6bc-9f46db5ca953&title=Saud%C3%A1vel",
    "id": "3fa1eba1-3177-4f3a-bdb1-485738eaeb76",
    "imageUrl": ":resolution/discoveries/saudaveis_SJsV.png",
    "title": "Saudável"
  }
]
```

## Get merchant catalog

```bash
curl --location 'https://marketplace.ifood.com.br/v1/merchants/MERCHANT_ID/catalog?
latitude=LATITUDE&longitude=LONGITUDE' \
--header 'Authorization: Bearer ACCESS_TOKEN'
```

If request was evaluate successfully, then the response will be something like that.

```json
{
    "code": "00",
    "data": {
        "menu": [
            {
                "code": "3108f2af-027f-4b9c-bf95-c47f78accb6b",
                "name": "Açaí No Copo",
                "itens": [
                    {
                        "id": "08717f80-df2f-4524-bbd1-b99e212e26fd",
                        "code": "08717f80-df2f-4524-bbd1-b99e212e26fd",
                        "description": "Açaí No Copo 300ml",
                        "needChoices": true,
                        "choices": [
                            {
                                "code": "1FA7F6",
                                "name": "Opções",
                                "min": 1,
                                "max": 2,
                                "garnishItens": [
                                    {
                                        "id": "194507c5-acbc-445d-b9b5-6e49058d0c25",
                                        "code": "194507c5-acbc-445d-b9b5-6e49058d0c25",
                                        "description": "Morango",
                                        "unitPrice": 0.00
                                    },
                                    {
                                        "id": "a2f78a6e-cef4-4734-a98d-bad170692cfa",
                                        "code": "a2f78a6e-cef4-4734-a98d-bad170692cfa",
                                        "description": "Hortela",
                                        "unitPrice": 0.00
                                    }
                                ]
                            },
                            {
                                "code": "85804D",s
                                "name": "Opcionais",
                                "min": 0,
                                "max": 5,
                                "garnishItens": [
                                    {
                                        "id": "f522b71d-8185-43ef-858b-f109f6cf4fd6",
                                        "code": "f522b71d-8185-43ef-858b-f109f6cf4fd6",
                                        "description": "Confete",
                                        "unitPrice": 0.00
                                    }
                                ]
                            }
                        ],
                        "unitPrice": 25.99,
                        "unitMinPrice": 25.99,
                        "productTags": [
                            {
                                "group": "PORTION_SIZE",
                                "tags": [
                                    "NOT_APPLICABLE"
                                ]
                            }
                        ],
                        "productInfo": {
                            "id": "08717f80-df2f-4524-bbd1-b99e212e26fd",
                            "quantity": 0,
                            "unit": "g"
                        }
                    }
                ]
            }
        ]
    }
}
```