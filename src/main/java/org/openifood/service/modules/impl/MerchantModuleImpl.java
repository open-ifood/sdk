package org.openifood.service.modules.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.*;
import org.checkerframework.checker.units.qual.A;
import org.openifood.client.MarketplaceClient;
import org.openifood.config.InstanceConfig;
import org.openifood.dto.authentication.response.UserSession;
import org.openifood.dto.marketplace.HomeSection;
import org.openifood.dto.marketplace.Merchant;
import org.openifood.dto.marketplace.MerchantFilter;
import org.openifood.dto.marketplace.SectionCard;
import org.openifood.dto.marketplace.request.GetMerchantCatalogParams;
import org.openifood.dto.marketplace.request.HomeRequest;
import org.openifood.dto.marketplace.request.HomeRequestParams;
import org.openifood.dto.marketplace.response.MerchantCatalogResponse;
import org.openifood.service.modules.MerchantModule;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.NONE, force = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class MerchantModuleImpl implements MerchantModule {

    private final UserSession session;

    private final MarketplaceClient marketplaceClient = MarketplaceClient.initialize(InstanceConfig.config());

    public static MerchantModule getInstance(@NonNull UserSession session) {
        return new MerchantModuleImpl(session);
    }

    @Override
    public List<Merchant> list(@NonNull MerchantFilter filter) {
        val homeResponse = marketplaceClient.getHome(
                session,
                HomeRequest.builder()
                        .supportedActions(
                                List.of(
                                        "merchant",
                                        "page"
                                )
                        )
                        .supportedCards(
                                List.of(
                                        "MERCHANT_LIST",
                                        "FEATURED_MERCHANT_LIST",
                                        "MERCHANT_LIST_WITH_ITEMS_CAROUSEL",
                                        "MERCHANT_LIST_V2"
                                )
                        )
                        .build(),
                HomeRequestParams.builder()
                        .latitude(filter.getCoordinate().getLatitude())
                        .longitude(filter.getCoordinate().getLongitude())
                        .channel("IFOOD")
                        .size(filter.getSize())
                        .payment_types(filter.getPaymentTypes().stream().reduce((s, s2) -> s + ", " + s2)
                                .orElse(""))
                        .sort(filter.getSort())
                        .alias("HOME_FOOD_DELIVERY")
                        .build());

        return homeResponse.getSections().stream()
                .filter(homeSection -> "RANKING".equals(homeSection.getType()))
                .map(HomeSection::getCards)
                .flatMap(
                        sectionCards -> sectionCards.stream().filter(
                            sectionCard -> "MERCHANT_LIST_V2".equals(sectionCard.getCardType())
                        )
                ).map(SectionCard::getData)
                .map(data -> data.getAsJsonArray("contents"))
                .map(JsonArray::asList)
                .map(this::fromJsonObjects)
                .reduce(new ArrayList<>(), (merchants, merchants2) -> {
                    merchants.addAll(merchants2);

                    return merchants;
                });
    }

    @Override
    public MerchantCatalogResponse.MerchantCatalog catalog(@NonNull String merchantId) {
        return marketplaceClient.getMerchantCatalog(
                session,
                GetMerchantCatalogParams.builder()
                        .merchantId(merchantId)
                        .build()
        ).getData();
    }

    private List<Merchant> fromJsonObjects(@NonNull List<JsonElement> objects) {
        return objects.stream()
            .map(JsonElement::getAsJsonObject)
            .map(this::fromJson)
            .collect(Collectors.toList());
    }

    private Merchant fromJson(@NonNull JsonObject object) {
        return Merchant.builder()
                .id(object.get("id").getAsString())
                .isFavorite(object.get("isFavorite").getAsBoolean())
                .userRating(object.get("userRating").getAsDouble())
                .action(object.get("action").getAsString())
                .available(object.get("available").getAsBoolean())
                .currency(object.get("currency").getAsString())
                .distance(object.get("distance").getAsDouble())
                .formattedName(object.get("formattedName").getAsString())
                .imageUrl(object.get("imageUrl").getAsString())
                .isIfoodDelivery(object.get("isIfoodDelivery").getAsBoolean())
                .isNew(object.get("isNew").getAsBoolean())
                .isSuperRestaurant(object.get("isSuperRestaurant").getAsBoolean())
                .mainCategory(object.get("mainCategory").getAsString())
                .name(object.get("name").getAsString())
                .supportsTracking(object.get("supportsTracking").getAsBoolean())
                .userRating(object.get("userRating").getAsDouble())
                .isFavorite(object.get("isFavorite").getAsBoolean())
                .build();
    }

}
