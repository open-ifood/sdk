package org.openifood.service.modules.impl;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.openifood.client.OrderClient;
import org.openifood.config.InstanceConfig;
import org.openifood.dto.authentication.response.UserSession;
import org.openifood.dto.order.Order;
import org.openifood.dto.order.OrderCheckout;
import org.openifood.dto.order.OrderEvent;
import org.openifood.dto.order.OrderStatus;
import org.openifood.service.modules.OrderModule;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.NONE, force = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderModuleImpl implements OrderModule {

    private final UserSession session;

    private final OrderClient orderClient = OrderClient.initialize(InstanceConfig.config());

    public static OrderModule getInstance(@NonNull UserSession session) {
        return new OrderModuleImpl(session);
    }

    @Override
    public @NonNull Order.Data create(@NonNull OrderCheckout checkout) {
        return orderClient.create(session, checkout)
                .getData();
    }

    @Override
    public @NonNull OrderStatus status(@NonNull Order order) {
        return orderClient.status(
                session,
                getOrderId(order)
        );
    }

    @Override
    public @NonNull OrderStatus status(@NonNull String orderId) {
        return orderClient.status(session, orderId);
    }

    @Override
    public @NonNull List<OrderEvent> events(@NonNull Order order) {
        return orderClient.events(session, getOrderId(order));
    }

    @Override
    public @NonNull List<OrderEvent> events(@NonNull String orderId) {
        return orderClient.events(session, orderId);
    }

    private @NonNull String getOrderId(@NonNull Order order) {
        return Optional.of(order)
                .map(Order::getData)
                .map(Order.Data::getOrderCheckout)
                .map(Order.OrderCheckout::getId)
                .orElseThrow(() -> new IllegalStateException("failed during get order id"));
    }
}
