package org.openifood.service;

import lombok.NonNull;
import org.openifood.dto.authentication.request.EmailAuthenticationRequest;
import org.openifood.dto.authentication.response.UserAuthenticationSession;
import org.openifood.service.impl.IFoodServiceImpl;

/**
 * Entry main interface for grouping all client modules in order to facilitate usage.
 *
 * @author Leonardo ELias | leonardo@adaptus.tech
 */
public interface IFoodService {

    /**
     * Authenticate to iFood customer platform through e-mail.
     *
     * @param request user data.
     * @return An authentication session (it's not final process for authentication).
     */
    @NonNull UserAuthenticationSession authenticate(
            @NonNull EmailAuthenticationRequest request
    );

    /**
     * @return a default service instance (entry point for all other services like authentication,
     * merchants, order, address)
     */
    default IFoodService initialize() {
        return IFoodServiceImpl.getInstance();
    }
}
