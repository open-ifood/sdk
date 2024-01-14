package org.openifood.dto.authentication.request;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AuthenticationRequest {
    /** Default: IFO */
    String tenantId;
    /** Temporary access token received from {@link org.openifood.dto.authentication.response.ConfirmAuthCodeResponse} */
    String token;
    /** UUID_V4 identifier of device using the API */
    String deviceId;
    /** E-mail used in authentication. */
    String email;
}
