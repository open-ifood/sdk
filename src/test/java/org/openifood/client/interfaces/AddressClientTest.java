package org.openifood.client.interfaces;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.inject.Inject;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openifood.dto.address.request.CreateAddressRequest;
import org.openifood.dto.authentication.AuthContext;
import org.openifood.exception.IFoodBusinessException;

@QuarkusTest
public class AddressClientTest {

    @Inject
    AddressClient client;

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
