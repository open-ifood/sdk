package org.openifood.dto.authentication.response;


import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.openifood.service.modules.AddressModule;
import org.openifood.service.modules.MerchantModule;
import org.openifood.service.modules.OrderModule;

@RequiredArgsConstructor(access = AccessLevel.MODULE)
public class UserSession {

    private final AuthenticationResponse authenticationResponse;

    /**
     * Refresh user session access token.
     */
    public UserSession refresh() {
        // TODO: refresh user session
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
