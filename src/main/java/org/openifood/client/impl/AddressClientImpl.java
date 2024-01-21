package org.openifood.client.impl;

import com.squareup.okhttp.Request;
import lombok.*;
import org.openifood.client.impl.AbstractIFoodClient;
import org.openifood.client.AddressClient;
import org.openifood.config.InstanceConfig;
import org.openifood.dto.address.request.CreateAddressRequest;
import org.openifood.dto.address.response.CustomerAddress;
import org.openifood.dto.address.response.GeocodeAddressResponse;
import org.openifood.dto.authentication.AuthContext;

import java.util.List;

@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.NONE)
public class AddressClientImpl extends AbstractIFoodClient implements AddressClient {

    private AddressClientImpl(@NonNull InstanceConfig instanceConfig) {
        super(instanceConfig);
    }

    public static AddressClient getInstance(@NonNull InstanceConfig instanceConfig) {
        return new AddressClientImpl(instanceConfig);
    }

    @Override
    @SneakyThrows
    public @NonNull GeocodeAddressResponse geocodeLegacy(@NonNull String addressLine) {
        return evaluate(
                new Request.Builder()
                        .get()
                        .url(resolve("v1/addresses:geocode?query=" + encode(addressLine)))
                        .header("x-application-key", config.getLegacyAppKey())
                        .build(),
                GeocodeAddressResponse.class
        );
    }

    @Override
    public @NonNull GeocodeAddressResponse geocode(@NonNull AuthContext authContext,@NonNull String addressLine) {
        throw new IllegalStateException("Geocode by logistics not implemented yet");
    }

    @Override
    public @NonNull List<CustomerAddress> listAddresses(@NonNull AuthContext authContext) {
        return evaluateList(
                new Request.Builder()
                        .get()
                        .url(resolve("v1/customers/me/addresses"))
                        .build(),
                CustomerAddress.class,
                authContext
        );
    }

    @Override
    public @NonNull CustomerAddress createAddress(@NonNull AuthContext authContext,
                                                  @NonNull CreateAddressRequest request) {
        return evaluate(
                new Request.Builder()
                        .post(body(request))
                        .url(resolve("v1/customers/me/addresses"))
                        .build(),
                GeocodeAddressResponse.class,
                authContext
        );
    }
}
