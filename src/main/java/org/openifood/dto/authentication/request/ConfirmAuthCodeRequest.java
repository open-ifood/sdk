package org.openifood.dto.authentication.request;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ConfirmAuthCodeRequest {
    /** Authentication key generated in send auth token request. */
    String key;
    /** Auth confirmation code */
    String authCode;
}
