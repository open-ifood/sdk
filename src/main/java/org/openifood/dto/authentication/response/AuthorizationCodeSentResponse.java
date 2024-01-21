package org.openifood.dto.authentication.response;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AuthorizationCodeSentResponse {
    /** temporary base-64 key registering the initiated authorization step */
    String key;
    Integer timeoutInSeconds;
}
