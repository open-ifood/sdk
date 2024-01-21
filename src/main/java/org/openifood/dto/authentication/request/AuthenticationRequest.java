package org.openifood.dto.authentication.request;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class AuthenticationRequest {
    /** Default: IFO */
    @NonNull String tenantId;
    /** Temporary access token received from {@link org.openifood.dto.authentication.response.ConfirmAuthCodeResponse} */
    @NonNull String token;
    /** UUID_V4 identifier of device using the API */
    @NonNull String deviceId;
    /** E-mail used in authentication. */
    @NonNull String email;
}
