package org.openifood.service.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.openifood.client.AuthenticationClient;
import org.openifood.config.InstanceConfig;
import org.openifood.dto.authentication.request.EmailAuthenticationRequest;
import org.openifood.dto.authentication.response.UserAuthenticationSession;
import org.openifood.service.IFoodService;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IFoodServiceImpl implements IFoodService {

    private final AuthenticationClient authenticationClient =
            AuthenticationClient.initialize(InstanceConfig.config());

    public static IFoodService getInstance() {
        return new IFoodServiceImpl();
    }

    @Override
    public @NonNull UserAuthenticationSession authenticate(@NonNull EmailAuthenticationRequest request) {
        return new UserAuthenticationSession(
                authenticationClient.requestAuthorizationCode(request),
                request
        );
    }

}
