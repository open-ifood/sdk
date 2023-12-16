# Merchant

This documentation stores all requests and information about merchant.

## Get available merchants

- [PRODUCT_CATEGORY_ID](product.md#get-categories)
- LATITUDE -> Current latitude to connected user address
- LONGITUDE -> Current longitude to connect user address
- CHANNEL -> Default = IFOOD

```bash
curl --location 'https://marketplace.ifood.com.br/v1/page/PRODUCT_CATEGORY_ID?
latitude=LATITUDE&longitude=LONGITUDE&channel=CHANNEL' \
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

The script below will create a list of available merchants.

```js
const response = pm.response.json()

const merchants = response?.sections[0]?.cards[0]?.data?.contents.map(merchant => ({
    opened: merchant.deliveryInfo?.type === "DELIVERY",
    ...merchant
})).filter(merchant => merchant.opened);
```

```js
{
    opened: true 
    id: "274ba0df-e634-48c5-a037-6b95268197cc"
    action: "merchant?channel=IFOOD&id=3cc6792f-9b33-4fe3-ad84-5129b59a9b33&identifier=274ba0df-e634-48c5-a037-6b95268197cc&latitude=-22.024173&longitude=-47.894354&name=Espetaria%20743&size=100&slug=sao-carlos-sp%2Fespetaria-743-jardim-macarengo"
    adsMetadata: null
    available: true
    currency: "BRL"
    deliveryInfo: {…}
    distance: 1.75
    imageUrl: ":resolution/logosgde/274ba0df-e634-48c5-a037-6b95268197cc/202105071129_9Ms1_i.jpg"
    isIfoodDelivery: false
    isNew: false
    isSuperRestaurant: false
    mainCategory: "Lanches"
    name: "Espetaria 743"
    formattedName: "[#141414: Espetaria 743]"
    supportsTracking: false
    userRating: 4.900000095367432
    isFavorite: false
}
```
