package org.openifood.dto.authentication.response;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AuthenticationResponse {
    /** indicates if user are authenticated successfully */
    Boolean authenticated;
    /** UUID_V4 account id identifier */
    String accountId;
    /** access token to be used in private requests */
    String accessToken;
    /** token to refresh access token */
    String refreshToken;
}
