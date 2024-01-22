package org.openifood.service.modules;

import lombok.NonNull;
import org.openifood.dto.authentication.response.UserSession;
import org.openifood.dto.marketplace.Merchant;
import org.openifood.dto.marketplace.MerchantFilter;
import org.openifood.dto.marketplace.response.MerchantCatalogResponse;
import org.openifood.service.modules.impl.MerchantModuleImpl;

import java.util.List;

/**
 * Offer methods for list merchant's by filters and
 * for getting merchant catalog.
 */
public interface MerchantModule {

    /**
     * @return merchants by filter
     */
    List<Merchant> list(@NonNull MerchantFilter filter);

    /**
     * Get catalog by merchant id.
     */
    MerchantCatalogResponse.MerchantCatalog catalog(@NonNull String merchantId);

    static MerchantModule initialize(@NonNull UserSession session) {
        return MerchantModuleImpl.getInstance(session);
    }
}
