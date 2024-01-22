package org.openifood.client;

import lombok.NonNull;
import org.openifood.client.impl.AddressClientImpl;
import org.openifood.config.InstanceConfig;
import org.openifood.dto.address.request.CreateAddressRequest;
import org.openifood.dto.address.response.CustomerAddress;
import org.openifood.dto.address.response.GeocodeAddressResponse;
import org.openifood.dto.authentication.response.UserSession;

import java.util.List;

public interface AddressClient {

    /**
     * Geocode an address line, address quality, latitude and longitude.
     *
     * @param addressLine a full length string with line (e.g. "Rua São da Silva, 123. São Paulo")
     * @return geocode response.
     */
    @NonNull GeocodeAddressResponse geocodeLegacy(@NonNull String addressLine);

    /**
     * Geocode an address line, address quality, latitude and longitude.
     * <br>
     * This use logistics iFood API.
     *
     * @param addressLine a full length string with line (e.g. "Rua São da Silva, 123. São Paulo")
     * @return geocode response.
     */
    @NonNull GeocodeAddressResponse geocode(@NonNull UserSession session, @NonNull String addressLine);

    /**
     * @return customer registered addresses.
     */
    @NonNull List<CustomerAddress> listAddresses(@NonNull UserSession session);

    /**
     * Create a new address to a customer account.
     */
    @NonNull CustomerAddress createAddress(@NonNull UserSession session,
                                           @NonNull CreateAddressRequest request);

    static AddressClient initialize(@NonNull InstanceConfig instanceConfig) {
        return AddressClientImpl.getInstance(instanceConfig);
    }

}
