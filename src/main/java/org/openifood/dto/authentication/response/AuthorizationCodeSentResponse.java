package org.openifood.dto.authentication.response;

import lombok.Value;

@Value
public class AuthorizationCodeSentResponse {
    /** temporary base-64 key registering the initiated authorization step */
    String key;
    Integer timeoutInSeconds;
}
