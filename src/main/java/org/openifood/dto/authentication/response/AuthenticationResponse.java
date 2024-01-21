package org.openifood.dto.authentication.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Value;

@Builder
@Getter
public class AuthenticationResponse {
    /** indicates if user are authenticated successfully */
    private final Boolean authenticated;
    /** UUID_V4 account id identifier */
    private final String accountId;
    /** access token to be used in private requests */
    private String accessToken;
    /** token to refresh access token */
    private String refreshToken;

    public void refresh(@NonNull String accessToken, @NonNull String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
