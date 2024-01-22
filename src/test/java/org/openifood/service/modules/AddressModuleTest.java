package org.openifood.service.modules;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.openifood.client.AddressClient;
import org.openifood.client.mock.AddressClientMockImpl;
import org.openifood.dto.address.request.CreateAddressRequest;
import org.openifood.dto.authentication.response.AuthenticationResponse;
import org.openifood.dto.authentication.response.UserSession;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AddressModuleTest {

    @Test
    void shouldListAddress() {
        val client = mockClient();
        val module = AddressModule.initialize(createSession());
        val addresses = module.list();

        assertNotNull(addresses);
        assertFalse(addresses.isEmpty());

        client.close();
    }

    @Test
    void shouldGeocodeAddress() {
        val client = mockClient();
        val module = AddressModule.initialize(createSession());

        val response = module.geocode("street non nan nin, 21251. City State");

        assertNotNull(response);

        client.close();
    }

    @Test
    void shouldCreateAddress() {
        val client = mockClient();
        val module = AddressModule.initialize(createSession());

        val response = module.create(
                CreateAddressRequest.builder()
                        .city("city")
                        .country("country")
                        .portalCode(1200000)
                        .build()
        );

        assertNotNull(response);

        client.close();
    }

    private MockedStatic<AddressClient> mockClient() {
        val client = Mockito.mockStatic(AddressClient.class);
        Mockito.when(AddressClient.initialize(Mockito.any()))
                .thenReturn(new AddressClientMockImpl());

        return client;
    }

    private UserSession createSession() {
        return UserSession.from(
            AuthenticationResponse.builder()
                    .accountId(UUID.randomUUID().toString())
                    .authenticated(Boolean.TRUE)
                    .refreshToken("refreshToken")
                    .accessToken("accessToken")
                    .build()
        );
    }
}