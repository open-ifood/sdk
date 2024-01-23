package org.openifood.client;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openifood.config.InstanceConfig;
import org.openifood.dto.authentication.response.AuthenticationResponse;
import org.openifood.dto.authentication.response.UserSession;
import org.openifood.dto.order.*;
import org.openifood.exception.IFoodBusinessException;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class OrderClientTest {

    private final OrderClient orderClient = OrderClient.initialize(InstanceConfig.config());

    @Test
    void shouldCreateOrder() {
        val exception = Assertions.assertThrows(IFoodBusinessException.class, () -> orderClient.create(
                createSession(),
                OrderCheckout.builder()
                        .address(
                                new OrderCheckout.Address("832243255")
                        )
                        .account(
                                new OrderCheckout.Account("09101010")
                        )
                        .restaurantOrder(
                                Collections.singletonList(
                                        RestaurantOrder.builder()
                                                .restaurant(
                                                        new Restaurant(UUID.randomUUID().toString())
                                                )
                                                .itens(
                                                        List.of(
                                                                Item.builder()
                                                                        .code(UUID.randomUUID().toString())
                                                                        .obs("")
                                                                        .qty(1)
                                                                        .unitPrice(BigDecimal.TEN)
                                                                        .choices(
                                                                                List.of(
                                                                                        ItemChoice.builder()
                                                                                                .code("012")
                                                                                                .garnishItens(
                                                                                                        List.of(
                                                                                                                GarnishItem.builder()
                                                                                                                        .code("010101")
                                                                                                                        .qty(1)
                                                                                                                        .unitPrice(BigDecimal.ONE)
                                                                                                                        .build(),
                                                                                                                GarnishItem.builder()
                                                                                                                        .code("010102")
                                                                                                                        .qty(1)
                                                                                                                        .unitPrice(BigDecimal.ONE)
                                                                                                                        .build()
                                                                                                        )
                                                                                                )
                                                                                                .build()
                                                                                )
                                                                        )
                                                                        .build()
                                                        )
                                                )
                                                .build()
                                )
                        )
                        .deliveryAdditionalInfo("MOTOCA AVISAR -> Enviado por {name}")
                        .product("open_ifood_sdk/v1.0.0")
                        .medium("S")
                        .platform("SYSTEM")
                        .scheduled(false)
                        .test(true)
                        .deliveryMethod(new DeliveryMethod("DEFAULT"))
                        .paymentSources(
                                new PaymentSources(
                                        List.of(
                                                PaymentSource.builder()
                                                        .source("TOKEN")
                                                        .paymentMethod(
                                                                PaymentMethod.builder()
                                                                        .id(UUID.randomUUID().toString())
                                                                        .type(
                                                                                PaymentMethodType.builder()
                                                                                        .name("ONLINE")
                                                                                        .description(
                                                                                                "Payment " +
                                                                                                        "via" +
                                                                                                        " APP")
                                                                                        .build()
                                                                        )
                                                                        .name("PIX")
                                                                        .method(
                                                                                PaymentMethod.Method.builder()
                                                                                        .name("PIX")
                                                                                        .description("PIX")
                                                                                        .build()
                                                                        )
                                                                        .liability("IFOOD")
                                                                        .brand(
                                                                                Brand.builder()
                                                                                        .id(UUID.randomUUID().toString())
                                                                                        .name("PIX")
                                                                                        .description("pix")
                                                                                        .build()
                                                                        )
                                                                        .build()
                                                        )
                                                        .amount(
                                                                PaymentSource.Amount.builder()
                                                                        .value(BigDecimal.ONE)
                                                                        .currency("BRL")
                                                                        .build()
                                                        )
                                                        .build()
                                        )
                                )
                        )
                        .build()
        ));

        Assertions.assertNotNull(exception);
    }

    @Test
    void shouldRetrieveOrderStatus() {
        val exception = Assertions.assertThrows(IFoodBusinessException.class, () -> orderClient.status(
                createSession(),
                UUID.randomUUID().toString()
        ));

        Assertions.assertNotNull(exception);
    }

    @Test
    void shouldRetrieveOrderEvents() {
        val exception = Assertions.assertThrows(IFoodBusinessException.class, () -> orderClient.events(
                createSession(),
                UUID.randomUUID().toString()
        ));

        Assertions.assertNotNull(exception);
    }

    private UserSession createSession() {
        return UserSession.from(
                AuthenticationResponse.builder()
                        .accessToken("myaccesstoken")
                        .build()
        );
    }
}
