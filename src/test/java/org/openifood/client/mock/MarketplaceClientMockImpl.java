package org.openifood.client.mock;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.NonNull;
import org.openifood.client.MarketplaceClient;
import org.openifood.dto.authentication.response.UserSession;
import org.openifood.dto.marketplace.*;
import org.openifood.dto.marketplace.request.GetMerchantCatalogParams;
import org.openifood.dto.marketplace.request.HomeRequest;
import org.openifood.dto.marketplace.request.HomeRequestParams;
import org.openifood.dto.marketplace.response.HomeResponse;
import org.openifood.dto.marketplace.response.MerchantCatalogResponse;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class MarketplaceClientMockImpl implements MarketplaceClient {
    @Override
    public @NonNull HomeResponse getHome(@NonNull UserSession session, @NonNull HomeRequest request, @NonNull HomeRequestParams params) {
        return HomeResponse.builder()
                .alias("ASD")
                .sections(
                        List.of(
                                HomeSection.builder()
                                        .type("RANKING")
                                        .cards(
                                                List.of(
                                                        SectionCard.builder()
                                                                .cardType("MERCHANT_LIST_V2")
                                                                .data(createContentsObject())
                                                                .build()
                                                )
                                        )
                                        .build()
                        )
                )
                .build();
    }

    @Override
    public @NonNull MerchantCatalogResponse getMerchantCatalog(@NonNull UserSession session,
                                                               @NonNull GetMerchantCatalogParams params) {

        return MerchantCatalogResponse.builder()
                .code("123123")
                .data(
                        MerchantCatalogResponse.MerchantCatalog.builder()
                                .menu(
                                        List.of(
                                                MerchantCategory.builder()
                                                        .code("1231")
                                                        .name("Category")
                                                        .itens(
                                                                List.of(
                                                                        Product.builder()
                                                                                .id("id")
                                                                                .description("product")
                                                                                .details("details details")
                                                                                .logoUrl("/logo/url/10101")
                                                                                .needChoices(false)
                                                                                .unitMinPrice(BigDecimal.ONE)
                                                                                .unitPrice(BigDecimal.TEN)
                                                                                .build()
                                                                )
                                                        )
                                                        .build()
                                        )
                                )
                                .build()
                )
                .build();
    }

    private JsonObject createContentsObject() {
        JsonObject object = new JsonObject();

        object.add("contents", convertObjectToJsonObject(
                List.of(
                        Merchant.builder()
                                .id(UUID.randomUUID().toString())
                                .action("")
                                .formattedName("")
                                .name("Robin Restaurant")
                                .isFavorite(true)
                                .userRating(5.0)
                                .available(true)
                                .supportsTracking(true)
                                .mainCategory("Hamburger")
                                .isSuperRestaurant(false)
                                .isNew(true)
                                .isIfoodDelivery(true)
                                .imageUrl("/url/image/515050510150-5-15-51-5121")
                                .currency("BRL")
                                .distance(2.5)
                                .build()
                )
        ));

        return object;
    }

    private JsonArray convertObjectToJsonObject(Object yourObject) {
        Gson gson = new Gson();

        return gson.toJsonTree(yourObject).getAsJsonArray();
    }
}
