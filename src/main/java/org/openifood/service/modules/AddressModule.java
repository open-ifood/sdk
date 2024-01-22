package org.openifood.service.modules;

import lombok.NonNull;
import org.openifood.dto.address.request.CreateAddressRequest;
import org.openifood.dto.address.response.CustomerAddress;
import org.openifood.dto.address.response.GeocodeAddressResponse;
import org.openifood.dto.authentication.response.UserSession;
import org.openifood.service.modules.impl.AddressModuleImpl;

import java.util.List;

/**
 * Module responsible for listing user address, geocode and create new one.
 */
public interface AddressModule {

    /**
     * @return all user address.
     */
    List<CustomerAddress> list();

    /**
     * Geocode getting information by searching address string line.
     *
     * @param addressLine string based address (e.g. Street Soa Vin, 22123)
     */
    GeocodeAddressResponse geocode(@NonNull String addressLine);

    /**
     * Register a new address to user account.
     *
     * @param address geocoded address format. see more {@link #geocode(String)}
     */
    CustomerAddress create(CreateAddressRequest address);

    static AddressModule initialize(@NonNull UserSession session) {
        return AddressModuleImpl.getInstance(session);
    }
}
