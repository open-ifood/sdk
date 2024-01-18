package org.openifood.client.interfaces;

import lombok.NonNull;
import org.openifood.dto.authentication.AuthContext;
import org.openifood.dto.marketplace.request.HomeRequest;
import org.openifood.dto.marketplace.request.HomeRequestParams;
import org.openifood.dto.marketplace.response.HomeResponse;

public interface MarketplaceClient {

    /**
     * List merchants and other card types (items exposed at iFood Home)
     *
     * @param auth auth context with access token.
     * @param request request with params
     * @param params query params
     *
     * @return headers, merchants, products and other card types.
     */
    @NonNull HomeResponse getHome(@NonNull AuthContext auth, @NonNull HomeRequest request,
                                  @NonNull HomeRequestParams params);



}
