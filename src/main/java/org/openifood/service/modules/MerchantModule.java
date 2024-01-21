package org.openifood.service.modules;

import lombok.NonNull;
import org.openifood.dto.marketplace.Merchant;
import org.openifood.dto.marketplace.MerchantFilter;
import org.openifood.dto.marketplace.response.MerchantCatalogResponse;

import java.util.List;

/**
 * Offer methods for list merchant's by filters and
 * for getting merchant catalog.
 */
public interface MerchantModule {

    // TODO: to implementation, use UserSession to add required filters (e.g. latitude & longitude)

    /**
     * @return merchants by filter
     */
    List<Merchant> list(@NonNull MerchantFilter filter);

    /**
     * Get catalog by merchant id.
     */
    MerchantCatalogResponse.MerchantCatalog catalog(@NonNull String merchantId);
}
