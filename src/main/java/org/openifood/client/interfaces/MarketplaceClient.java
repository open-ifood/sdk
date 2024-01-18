package org.openifood.client.interfaces;

import lombok.NonNull;
import org.openifood.dto.authentication.AuthContext;
import org.openifood.dto.marketplace.request.HomeRequest;
import org.openifood.dto.marketplace.request.HomeRequestParams;
import org.openifood.dto.marketplace.response.HomeResponse;

public interface MarketplaceClient {

    @NonNull HomeResponse getHome(@NonNull AuthContext auth, @NonNull HomeRequest request,
                                  @NonNull HomeRequestParams params);

}
