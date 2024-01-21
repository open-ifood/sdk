package org.openifood.client.mock;

import lombok.NonNull;
import org.openifood.client.AuthenticationClient;
import org.openifood.dto.authentication.IdentityProvider;
import org.openifood.dto.authentication.request.*;
import org.openifood.dto.authentication.response.AuthenticationResponse;
import org.openifood.dto.authentication.response.AuthorizationCodeSentResponse;
import org.openifood.dto.authentication.response.ConfirmAuthCodeResponse;
import org.openifood.dto.authentication.response.RefreshAccessTokenResponse;

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

    @Override
    public @NonNull RefreshAccessTokenResponse refresh(@NonNull RefreshAccessTokenRequest request) {
        return new RefreshAccessTokenResponse(
                "new_access_token",
                "new_refresh_token"
        );
    }
}
