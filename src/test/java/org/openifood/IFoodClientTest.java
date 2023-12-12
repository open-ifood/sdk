package org.openifood;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class IFoodClientTest {

    @Inject
    IFoodClient iFoodClient;

    @Test
    void shouldAuthenticationInjected() {

        Assertions.assertNotNull(iFoodClient);
        Assertions.assertNotNull(iFoodClient.authentication());
    }
}
