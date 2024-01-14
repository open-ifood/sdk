package org.openifood.dto.authentication.request;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AuthorizationCodeRequest {
    String tenantId;
    String email;
    String type;
}
