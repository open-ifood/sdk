package org.openifood.dto.authentication;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthContext {
    String accessToken;

    public static AuthContext from(@NonNull String token) {
        return new AuthContext(token);
    }
}
