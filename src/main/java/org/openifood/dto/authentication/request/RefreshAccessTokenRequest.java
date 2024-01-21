package org.openifood.dto.authentication.request;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class RefreshAccessTokenRequest {
    String refreshToken;
}
