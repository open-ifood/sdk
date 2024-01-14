package org.openifood.dto.authentication.response;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ConfirmAuthCodeResponse {
    /** Temporary access token, this cannot be used for authorize private requests */
    String accessToken;
}
