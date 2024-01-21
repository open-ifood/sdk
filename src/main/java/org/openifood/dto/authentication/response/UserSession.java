package org.openifood.dto.authentication.response;


import lombok.*;
import org.openifood.client.AuthenticationClient;
import org.openifood.config.InstanceConfig;
import org.openifood.dto.authentication.request.RefreshAccessTokenRequest;
import org.openifood.service.modules.AddressModule;
import org.openifood.service.modules.MerchantModule;
import org.openifood.service.modules.OrderModule;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.NONE, force = true)
public class UserSession {

    final AuthenticationResponse authenticationResponse;

    private final AuthenticationClient authenticationClient =
            AuthenticationClient.initialize(InstanceConfig.config());

    public static UserSession from(@NonNull AuthenticationResponse authenticationResponse) {
        return new UserSession(authenticationResponse);
    }

    /**
     * Refresh user session access token.
     */
    public UserSession refresh() {
        val tokens = authenticationClient.refresh(
                new RefreshAccessTokenRequest(authenticationResponse.getRefreshToken())
        );

        authenticationResponse.refresh(tokens.getAccessToken(), tokens.getRefreshToken());

        return this;
    }

    public @NonNull AddressModule address() {
        return null;
    }

    public @NonNull MerchantModule merchant() {
        return null;
    }

    public @NonNull OrderModule order() {
        return null;
    }

}
