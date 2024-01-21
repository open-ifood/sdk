package org.openifood.dto.authentication.response;


import lombok.*;
import org.openifood.client.AuthenticationClient;
import org.openifood.config.InstanceConfig;
import org.openifood.dto.authentication.request.AuthenticationRequest;
import org.openifood.dto.authentication.request.ConfirmAuthCodeRequest;
import org.openifood.dto.authentication.request.EmailAuthenticationRequest;

@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class UserAuthenticationSession {

    private final AuthorizationCodeSentResponse authorizationCodeResponse;

    private final EmailAuthenticationRequest authenticationRequest;

    private final InstanceConfig instanceConfig = InstanceConfig.config();

    private final AuthenticationClient authenticationClient =
            AuthenticationClient.initialize(instanceConfig);

    /**
     * Confirm authentication by sending authentication code to iFood.
     *
     * @param authCode string based code sent to your provider (e.g. e-mail)
     */
    public @NonNull UserSession confirm(@NonNull String authCode) {

        val response = authenticationClient.confirmAuthorizationCode(
                ConfirmAuthCodeRequest.builder()
                        .key(authorizationCodeResponse.getKey())
                        .authCode(authCode)
                        .build()
        );

        val authenticationResponse = authenticationClient.authenticate(
                AuthenticationRequest.builder()
                        .token(response.getAccessToken())
                        .tenantId(authenticationRequest.getTenantId())
                        .email(authenticationRequest.getEmail())
                        .deviceId(instanceConfig.getRandomDeviceUUID())
                        .build()
        );

        return UserSession.from(authenticationResponse);
    }
}
