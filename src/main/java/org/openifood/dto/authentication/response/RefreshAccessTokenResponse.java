package org.openifood.dto.authentication.response;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class RefreshAccessTokenResponse {
    String accessToken;
    String refreshToken;
}
