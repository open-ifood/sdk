package org.openifood.client.interfaces;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openifood.dto.authentication.AuthContext;
import org.openifood.dto.marketplace.request.GetMerchantCatalogParams;
import org.openifood.dto.marketplace.request.HomeRequest;
import org.openifood.dto.marketplace.request.HomeRequestParams;
import org.openifood.exception.IFoodBusinessException;

import java.util.List;

@QuarkusTest
public class MarketplaceClientTest {

    @Inject
    MarketplaceClient client;

    @Test
    void shouldGetMerchantCatalog() {
        Assertions.assertThrows(IFoodBusinessException.class, () -> client.getMerchantCatalog(
                AuthContext.from("ACCESS_TOKEN_HERE"),
                GetMerchantCatalogParams.builder()
                        .merchantId("65847f1f-743f-4317-a877-b1ee7923d0c6")
                        .build()
        ));
    }

    @Test
    void shouldGetHomeSections() {
        Assertions.assertThrows(IFoodBusinessException.class, () -> {
            val home = client.getHome(
                    AuthContext.from("ACCESS_TOKEN_HERE"),
                    HomeRequest.builder()
                            .supportedCards(
                                    List.of(
                                            "MERCHANT_LIST",
                                            "FEATURED_MERCHANT_LIST",
                                            "MERCHANT_LIST_V2"
                                    )
                            )
                            .supportedActions(
                                    List.of(
                                            "merchant",
                                            "page",
                                            "card-content",
                                            "last-restaurants"
                                    )
                            )
                            .build(),
                    HomeRequestParams.builder()
                            .latitude("-19.8589944")
                            .longitude("-44.0211396")
                            .channel("IFOOD")
                            .alias("HOME_FOOD_DELIVERY")
                            .size("20")
                            .build()
            );
        });

    }
}
