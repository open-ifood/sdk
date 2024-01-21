package org.openifood.dto.authentication.response;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class UserAuthenticationSession {

    private final AuthorizationCodeSentResponse authorizationCodeResponse;

    /**
     * Confirm authentication by sending authentication code to iFood.
     *
     * @param authCode string based code sent to your provider (e.g. e-mail)
     */
    public @NonNull UserSession confirm(@NonNull String authCode) {
        return null;
    }
}
