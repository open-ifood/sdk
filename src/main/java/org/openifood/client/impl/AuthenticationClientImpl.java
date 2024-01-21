package org.openifood.client.impl;

import com.squareup.okhttp.Request;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.openifood.client.AuthenticationClient;
import org.openifood.config.InstanceConfig;
import org.openifood.dto.authentication.IdentityProvider;
import org.openifood.dto.authentication.request.AuthenticationRequest;
import org.openifood.dto.authentication.request.ConfirmAuthCodeRequest;
import org.openifood.dto.authentication.request.EmailAuthenticationRequest;
import org.openifood.dto.authentication.request.GetIdentityProvidersRequest;
import org.openifood.dto.authentication.response.AuthenticationResponse;
import org.openifood.dto.authentication.response.AuthorizationCodeSentResponse;
import org.openifood.dto.authentication.response.ConfirmAuthCodeResponse;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.NONE)
public class AuthenticationClientImpl extends AbstractIFoodClient implements AuthenticationClient{

    private AuthenticationClientImpl(@NonNull InstanceConfig instanceConfig) {
        super(instanceConfig);
    }

    public static AuthenticationClient getInstance(@NonNull InstanceConfig instanceConfig) {
        return new AuthenticationClientImpl(instanceConfig);
    }

    @Override
    public @NonNull List<IdentityProvider> getIdentityProviders(@NonNull GetIdentityProvidersRequest request) {
        return evaluateList(
                new Request.Builder()
                        .url(resolve("v4/identity-providers"))
                        .post(body(request))
                        .build(),
                IdentityProvider.class
        );
    }

    @Override
    public @NonNull AuthorizationCodeSentResponse requestAuthorizationCode(@NonNull EmailAuthenticationRequest request) {
        return evaluate(
                new Request.Builder()
                        .url(resolve("v2/identity-providers/OTP/authorization-codes"))
                        .header("accept-language", "pt-BR,pt;q=1")
                        .post(body(request))
                        .build(),
                AuthorizationCodeSentResponse.class
        );
    }

    @Override
    public @NonNull ConfirmAuthCodeResponse confirmAuthorizationCode(@NonNull ConfirmAuthCodeRequest request) {
        return evaluate(
                new Request.Builder()
                        .url(resolve("v2/identity-providers/OTP/access-tokens"))
                        .post(body(request))
                        .build(),
                ConfirmAuthCodeResponse.class
        );
    }

    @Override
    public @NonNull AuthenticationResponse authenticate(@NonNull AuthenticationRequest request) {
        return evaluate(
                new Request.Builder()
                        .url(resolve("v3/identity-providers/OTP/authentications"))
                        .post(body(request))
                        .build(),
                AuthenticationResponse.class
        );
    }
}
