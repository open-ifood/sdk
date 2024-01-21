package org.openifood.client.mock;

import lombok.NonNull;
import org.openifood.client.AuthenticationClient;
import org.openifood.dto.authentication.IdentityProvider;
import org.openifood.dto.authentication.request.AuthenticationRequest;
import org.openifood.dto.authentication.request.ConfirmAuthCodeRequest;
import org.openifood.dto.authentication.request.EmailAuthenticationRequest;
import org.openifood.dto.authentication.request.GetIdentityProvidersRequest;
import org.openifood.dto.authentication.response.AuthenticationResponse;
import org.openifood.dto.authentication.response.AuthorizationCodeSentResponse;
import org.openifood.dto.authentication.response.ConfirmAuthCodeResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AuthenticationClientMockImpl implements AuthenticationClient {
    @Override
    public @NonNull List<IdentityProvider> getIdentityProviders(@NonNull GetIdentityProvidersRequest request) {
        return new ArrayList<>();
    }

    @Override
    public @NonNull AuthorizationCodeSentResponse requestAuthorizationCode(@NonNull EmailAuthenticationRequest request) {
        return AuthorizationCodeSentResponse.builder()
                .key("key")
                .timeoutInSeconds(123)
                .build();
    }

    @Override
    public @NonNull ConfirmAuthCodeResponse confirmAuthorizationCode(@NonNull ConfirmAuthCodeRequest request) {
        return ConfirmAuthCodeResponse.builder()
                .accessToken("TEMP_ACCESS_TOKEN")
                .build();
    }

    @Override
    public @NonNull AuthenticationResponse authenticate(@NonNull AuthenticationRequest request) {
        return AuthenticationResponse.builder()
                .authenticated(true)
                .accountId(UUID.randomUUID().toString())
                .accessToken("VALID_ACCESS_TOKEN")
                .refreshToken("REFRESH_TOKEN")
                .build();
    }
}
