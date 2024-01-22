package org.openifood.dto.authentication.response;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.openifood.client.AuthenticationClient;
import org.openifood.client.mock.AuthenticationClientMockImpl;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserSessionTest {

    @Test
    void shouldRefreshAccessToken() {

        val client = Mockito.mockStatic(AuthenticationClient.class);
        Mockito.when(AuthenticationClient.initialize(Mockito.any()))
                .thenReturn(new AuthenticationClientMockImpl());

        val session = UserSession.from(
                AuthenticationResponse.builder()
                        .accessToken("access_token")
                        .refreshToken("refresh_token")
                        .authenticated(true)
                        .accountId(UUID.randomUUID().toString())
                        .build()
        ).refresh();

        assertNotNull(session);
        assertEquals("new_access_token", session.getAccessToken());
        assertEquals("new_refresh_token", session.getRefreshToken());

        client.close();
    }
}