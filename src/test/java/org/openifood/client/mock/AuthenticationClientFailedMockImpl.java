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
import org.openifood.dto.authentication.response.BusinessErrorResponse;
import org.openifood.dto.authentication.response.ConfirmAuthCodeResponse;
import org.openifood.exception.IFoodBusinessException;

import java.util.List;

public class AuthenticationClientFailedMockImpl implements AuthenticationClient {
    @Override
    public @NonNull List<IdentityProvider> getIdentityProviders(@NonNull GetIdentityProvidersRequest request) {
        return null;
    }

    @Override
    public @NonNull AuthorizationCodeSentResponse requestAuthorizationCode(@NonNull EmailAuthenticationRequest request) {
        throw new IFoodBusinessException(
                BusinessErrorResponse.builder()
                        .code("0032")
                        .message("mocked error message description")
                        .build()
        );
    }

    @Override
    public @NonNull ConfirmAuthCodeResponse confirmAuthorizationCode(@NonNull ConfirmAuthCodeRequest request) {
        return null;
    }

    @Override
    public @NonNull AuthenticationResponse authenticate(@NonNull AuthenticationRequest request) {
        return null;
    }
}
