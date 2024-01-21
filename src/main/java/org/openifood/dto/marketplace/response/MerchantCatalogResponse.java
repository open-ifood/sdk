package org.openifood.dto.marketplace.response;

import lombok.Value;
import org.openifood.dto.marketplace.MerchantCategory;

import java.util.List;

@Value
public class MerchantCatalogResponse {

    /** request status (default for success: "00") */
    String code;

    /** All wrapped data are here. */
    MerchantCatalog data;

    @Value
    public static class MerchantCatalog {
        List<MerchantCategory> menu;
    }

}
