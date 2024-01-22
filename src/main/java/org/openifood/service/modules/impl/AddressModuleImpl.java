package org.openifood.service.modules.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.openifood.client.AddressClient;
import org.openifood.config.InstanceConfig;
import org.openifood.dto.address.request.CreateAddressRequest;
import org.openifood.dto.address.response.CustomerAddress;
import org.openifood.dto.address.response.GeocodeAddressResponse;
import org.openifood.dto.authentication.response.UserSession;
import org.openifood.service.modules.AddressModule;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.NONE, force = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressModuleImpl implements AddressModule {

    private final UserSession userSession;

    private final AddressClient addressClient = AddressClient.initialize(InstanceConfig.config());

    public static AddressModule getInstance(@NonNull UserSession session) {
        return new AddressModuleImpl(session);
    }

    @Override
    public List<CustomerAddress> list() {
        return addressClient.listAddresses(userSession);
    }

    @Override
    public GeocodeAddressResponse geocode(@NonNull String addressLine) {
        return addressClient.geocodeLegacy(addressLine);
    }

    @Override
    public CustomerAddress create(CreateAddressRequest address) {
        return addressClient.createAddress(userSession, address);
    }
}
