package org.openifood.dto.authentication.request;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

/**
 * This is the user data for initializing an authentication process.
 * <br>
 * Disclaimer: Currently only support's e-mail authentication.
 *
 */
@Value
@Builder
public class EmailAuthenticationRequest {

    /** chosen tenant (default: IFO) */
    @NonNull String tenantId;

    /** user e-mail */
    @NonNull String email;

    /** authorization type */
    @NonNull String type;
}
