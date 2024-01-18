package org.openifood.dto.marketplace.request;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class GetMerchantCatalogParams {
    /** Merchant UUID_V4 representation. */
    @NonNull String merchantId;
    String latitude;
    String longitude;
}
