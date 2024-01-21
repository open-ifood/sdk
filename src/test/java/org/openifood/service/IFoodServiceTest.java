package org.openifood.service;

import lombok.NonNull;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.openifood.client.AuthenticationClient;
import org.openifood.client.mock.AuthenticationClientFailedMockImpl;
import org.openifood.client.mock.AuthenticationClientMockImpl;
import org.openifood.dto.authentication.request.EmailAuthenticationRequest;
import org.openifood.dto.authentication.response.UserAuthenticationSession;
import org.openifood.exception.IFoodBusinessException;

import static org.junit.jupiter.api.Assertions.*;

class IFoodServiceTest {

    @Test
    void shouldGetServiceInstance() {
        val service = IFoodService.initialize();

        assertNotNull(service);
        assertInstanceOf(IFoodService.class, service);
    }

    @Test
    void shouldInitializeAuthentication() {
        val client = Mockito.mockStatic(AuthenticationClient.class);
        Mockito.when(AuthenticationClient.initialize(Mockito.any()))
                .thenReturn(new AuthenticationClientMockImpl());

        val authenticationSession = initializeAuthenticationSession();

        assertNotNull(authenticationSession);
        client.close();
    }

    @Test
    void shouldNotAuthenticate() {
        val client = Mockito.mockStatic(AuthenticationClient.class);
        Mockito.when(AuthenticationClient.initialize(Mockito.any()))
                .thenReturn(new AuthenticationClientFailedMockImpl());

        assertThrows(IFoodBusinessException.class, this::initializeAuthenticationSession);

        client.close();
    }

    @Test
    void shouldConfirmAuthCodeAndAuthenticate() {
        val client = Mockito.mockStatic(AuthenticationClient.class);
        Mockito.when(AuthenticationClient.initialize(Mockito.any()))
                .thenReturn(new AuthenticationClientMockImpl());

        val session = initializeAuthenticationSession();
        val authenticatedSession = session.confirm("020202");

        assertNotNull(authenticatedSession);

        client.close();
    }

    private @NonNull UserAuthenticationSession initializeAuthenticationSession() {
        val service = IFoodService.initialize();

        return service.authenticate(
                EmailAuthenticationRequest.builder()
                        .email("email@domain.com")
                        .tenantId("IFO")
                        .type("email")
                        .build()
        );
    }

}