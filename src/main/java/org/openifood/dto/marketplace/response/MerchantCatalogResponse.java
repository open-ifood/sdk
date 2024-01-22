package org.openifood.dto.marketplace.response;

import lombok.Builder;
import lombok.Value;
import org.openifood.dto.marketplace.MerchantCategory;

import java.util.List;

@Value
@Builder
public class MerchantCatalogResponse {

    /** request status (default for success: "00") */
    String code;

    /** All wrapped data are here. */
    MerchantCatalog data;

    @Value
    @Builder
    public static class MerchantCatalog {
        List<MerchantCategory> menu;
    }

}
