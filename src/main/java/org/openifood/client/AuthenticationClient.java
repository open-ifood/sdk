package org.openifood.client;

import lombok.NonNull;
import org.openifood.client.impl.AuthenticationClientImpl;
import org.openifood.config.InstanceConfig;
import org.openifood.dto.authentication.IdentityProvider;
import org.openifood.dto.authentication.request.AuthenticationRequest;
import org.openifood.dto.authentication.request.EmailAuthenticationRequest;
import org.openifood.dto.authentication.request.ConfirmAuthCodeRequest;
import org.openifood.dto.authentication.request.GetIdentityProvidersRequest;
import org.openifood.dto.authentication.response.AuthenticationResponse;
import org.openifood.dto.authentication.response.AuthorizationCodeSentResponse;
import org.openifood.dto.authentication.response.ConfirmAuthCodeResponse;

import java.util.List;

public interface AuthenticationClient {

    /**
     * @return oAuth2 authorization providers available for login.
     */
    @NonNull List<IdentityProvider> getIdentityProviders(@NonNull GetIdentityProvidersRequest request);

    /**
     * This is the first request for doing authentication.
     * Send a new authentication confirmation code to e-mail and generate a new session key.
     *
     * @param request auth type and information (e.g. email).
     * @return a session key.
     */
    @NonNull AuthorizationCodeSentResponse requestAuthorizationCode(@NonNull EmailAuthenticationRequest request);

    /**
     * Confirm the previously generated authentication code received in e-mail.
     *
     * @param request session key generated at {@link #requestAuthorizationCode(EmailAuthenticationRequest)}
     *               and auth code received in e-mail.
     * @return a temporary access token (cannot be used for authorization private requests)
     */
    @NonNull ConfirmAuthCodeResponse confirmAuthorizationCode(@NonNull ConfirmAuthCodeRequest request);

    /**
     * Final step authentication -> generate access token to be used during private requests.
     *
     * @param request email and temporary access token received from {@link #confirmAuthorizationCode(ConfirmAuthCodeRequest)}
     * @return accessToken and refreshToken to complete account access.
     */
    @NonNull AuthenticationResponse authenticate(@NonNull AuthenticationRequest request);

    static AuthenticationClient initialize(@NonNull InstanceConfig config) {
        return AuthenticationClientImpl.getInstance(config);
    }
}
