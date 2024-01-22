package org.openifood.client.mock;

import lombok.NonNull;
import org.openifood.client.AddressClient;
import org.openifood.dto.address.request.CreateAddressRequest;
import org.openifood.dto.address.response.CustomerAddress;
import org.openifood.dto.address.response.GeocodeAddressResponse;
import org.openifood.dto.authentication.response.UserSession;

import java.util.List;

public class AddressClientMockImpl implements AddressClient {
    @Override
    public @NonNull GeocodeAddressResponse geocodeLegacy(@NonNull String addressLine) {
        return GeocodeAddressResponse.builder()
                .build();
    }

    @Override
    public @NonNull GeocodeAddressResponse geocode(@NonNull UserSession session, @NonNull String addressLine) {
        return geocodeLegacy(addressLine);
    }

    @Override
    public @NonNull List<CustomerAddress> listAddresses(@NonNull UserSession session) {
        return List.of(
                CustomerAddress.builder()
                        .city("city")
                        .alias("alias")
                        .build()
        );
    }

    @Override
    public @NonNull CustomerAddress createAddress(@NonNull UserSession session, @NonNull CreateAddressRequest request) {
        return CustomerAddress.builder().build();
    }
}
