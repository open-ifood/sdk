package org.openifood.service.modules;

import lombok.NonNull;
import org.openifood.dto.order.Order;
import org.openifood.dto.order.OrderCheckout;
import org.openifood.dto.order.OrderStatus;

/**
 * Order module for create a new order or get the status of that.
 */
public interface OrderModule {

    // TODO: inject user session to implementation for sending auth token
    @NonNull Order create(@NonNull OrderCheckout checkout);

    /**
     * @return get order status.
     */
    @NonNull OrderStatus status(@NonNull Order order);

    /**
     * @return get order status.
     */
    @NonNull OrderStatus status(@NonNull String orderId);
}
