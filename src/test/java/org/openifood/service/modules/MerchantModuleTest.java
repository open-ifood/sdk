package org.openifood.service.modules;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.openifood.client.MarketplaceClient;
import org.openifood.client.mock.MarketplaceClientMockImpl;
import org.openifood.dto.address.Coordinate;
import org.openifood.dto.authentication.response.AuthenticationResponse;
import org.openifood.dto.authentication.response.UserSession;
import org.openifood.dto.marketplace.MerchantFilter;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MerchantModuleTest {

    @Test
    void shouldListMerchants() {
        val client = Mockito.mockStatic(MarketplaceClient.class);
        Mockito.when(MarketplaceClient.initialize(Mockito.any()))
                .thenReturn(new MarketplaceClientMockImpl());

        val module = MerchantModule.initialize(UserSession.from(
                AuthenticationResponse.builder().build()
        ));

        val merchants = module.list(
                MerchantFilter.builder()
                        .coordinate(
                                Coordinate.builder()
                                        .latitude("-12.1251515")
                                        .longitude("52.15161616")
                                        .build()
                        )
                        .paymentTypes(
                                List.of("PIX")
                        )
                        .size(20)
                        .sort("sort:user_rating:desc")
                        .build()
        );

        assertNotNull(merchants);
        assertFalse(merchants.isEmpty());

        client.close();
    }

    @Test
    void shouldReturnCatalog() {
        val client = Mockito.mockStatic(MarketplaceClient.class);
        Mockito.when(MarketplaceClient.initialize(Mockito.any()))
                .thenReturn(new MarketplaceClientMockImpl());

        val module = MerchantModule.initialize(UserSession.from(
                AuthenticationResponse.builder().build()
        ));

        val catalog = module.catalog(UUID.randomUUID().toString());

        assertNotNull(catalog);

        client.close();
    }

}