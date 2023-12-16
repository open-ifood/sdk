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