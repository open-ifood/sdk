package org.openifood.client.interfaces;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.openifood.dto.authentication.request.AuthenticationRequest;
import org.openifood.dto.authentication.request.AuthorizationCodeRequest;
import org.openifood.dto.authentication.request.ConfirmAuthCodeRequest;
import org.openifood.dto.authentication.request.GetIdentityProvidersRequest;
import org.openifood.exception.IFoodBusinessException;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class AuthenticationClientTest {

    @Inject
    AuthenticationClient client;

    @Test
    void shouldReturnIdentityProviders() {
        val response = client.getIdentityProviders(
                GetIdentityProvidersRequest.builder()
                        .tenantId("IFO")
                        .countryCode(55)
                        .areaCode(11)
                        .phoneNumber("999999999")
                        .build()
        );

        assertNotNull(response);
    }

    @Test
    void shouldInitializeRequestAuthorization() {
        val response = client.requestAuthorizationCode(
            AuthorizationCodeRequest.builder()
                    .email("email@domain.com")
                    .type("EMAIL")
                    .tenantId("IFO")
                    .build()
        );

        assertNotNull(response);
        assertNotNull(response.getKey());
    }

    @Test
    void shouldReturnInvalidConfirmAuthCode() {

        val exception =  assertThrows(IFoodBusinessException.class, () -> client.confirmAuthorizationCode(
                ConfirmAuthCodeRequest.builder()
                        .key("a")
                        .authCode("12312")
                        .build()
        ));

        assertNotNull(exception.getResponse());

    }

    @Test
    void shouldAuthenticateSuccessfully() {
        val exception = assertThrows(IFoodBusinessException.class, () -> client.authenticate(
                AuthenticationRequest.builder()
                        .deviceId(UUID.randomUUID().toString())
                        .token("TOKEN-HERE")
                        .email("email@domain.com")
                        .tenantId("IFO")
                        .build()
        ));

        val response = exception.getResponse();

        assertNotNull(response.getCode());
    }
}