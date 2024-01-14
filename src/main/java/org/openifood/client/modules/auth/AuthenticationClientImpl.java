package org.openifood.client.modules.auth;

import com.squareup.okhttp.Request;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.NonNull;
import org.openifood.client.AbstractIFoodClient;
import org.openifood.client.interfaces.AuthenticationClient;
import org.openifood.dto.authentication.IdentityProvider;
import org.openifood.dto.authentication.request.AuthenticationRequest;
import org.openifood.dto.authentication.request.AuthorizationCodeRequest;
import org.openifood.dto.authentication.request.ConfirmAuthCodeRequest;
import org.openifood.dto.authentication.request.GetIdentityProvidersRequest;
import org.openifood.dto.authentication.response.AuthenticationResponse;
import org.openifood.dto.authentication.response.AuthorizationCodeResponse;
import org.openifood.dto.authentication.response.ConfirmAuthCodeResponse;

import java.util.List;

@ApplicationScoped
public class AuthenticationClientImpl extends AbstractIFoodClient implements AuthenticationClient{

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
    public @NonNull AuthorizationCodeResponse requestAuthorizationCode(@NonNull AuthorizationCodeRequest request) {
        return evaluate(
                new Request.Builder()
                        .url(resolve("v2/identity-providers/OTP/authorization-codes"))
                        .header("accept-language", "pt-BR,pt;q=1")
                        .post(body(request))
                        .build(),
                AuthorizationCodeResponse.class
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
