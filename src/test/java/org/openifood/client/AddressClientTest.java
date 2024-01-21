package org.openifood.client;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openifood.client.AddressClient;
import org.openifood.config.InstanceConfig;
import org.openifood.dto.address.request.CreateAddressRequest;
import org.openifood.dto.authentication.AuthContext;
import org.openifood.exception.IFoodBusinessException;

public class AddressClientTest {

    private final AddressClient client = AddressClient.initialize(InstanceConfig.config());

    @Test
    void shouldGeocodeAddressLine() {
        val geocodeResponse = client.geocodeLegacy("Rua teste do teste, 123. São Paulo");

        Assertions.assertNotNull(geocodeResponse.getAddresses());
    }

    @Test
    void shouldCreateNewAddress() {
        val exception = Assertions.assertThrows(IFoodBusinessException.class, () -> client.createAddress(
                AuthContext.from("INVALID_TOKEN"),
                CreateAddressRequest.builder()
                        .build()
        ));

        Assertions.assertNotNull(exception);
    }

}
