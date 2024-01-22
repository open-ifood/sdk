package org.openifood.client;

import lombok.NonNull;
import org.openifood.client.impl.MarketplaceClientImpl;
import org.openifood.config.InstanceConfig;
import org.openifood.dto.authentication.response.UserSession;
import org.openifood.dto.marketplace.request.GetMerchantCatalogParams;
import org.openifood.dto.marketplace.request.HomeRequest;
import org.openifood.dto.marketplace.request.HomeRequestParams;
import org.openifood.dto.marketplace.response.HomeResponse;
import org.openifood.dto.marketplace.response.MerchantCatalogResponse;

public interface MarketplaceClient {

    /**
     * List merchants and other card types (items exposed at iFood Home)
     *
     * @param session auth context with access token.
     * @param request request with params
     * @param params query params
     *
     * @return headers, merchants, products and other card types.
     */
    @NonNull HomeResponse getHome(@NonNull UserSession session, @NonNull HomeRequest request,
                                  @NonNull HomeRequestParams params);

    /**
     * Get a merchant catalog with categories and products.
     *
     * @param session user auth access token.
     * @param params localization params.
     *
     * @return merchant menu.
     */
    @NonNull MerchantCatalogResponse getMerchantCatalog(@NonNull UserSession session,
                                                        @NonNull GetMerchantCatalogParams params);

    static MarketplaceClient initialize(@NonNull InstanceConfig instanceConfig) {
        return MarketplaceClientImpl.getInstance(instanceConfig);
    }

}
