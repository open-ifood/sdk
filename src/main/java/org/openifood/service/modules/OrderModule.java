package org.openifood.service.modules;

import lombok.NonNull;
import org.openifood.dto.authentication.response.UserSession;
import org.openifood.dto.order.Order;
import org.openifood.dto.order.OrderCheckout;
import org.openifood.dto.order.OrderEvent;
import org.openifood.dto.order.OrderStatus;
import org.openifood.service.modules.impl.OrderModuleImpl;

import java.util.List;

/**
 * Order module for create a new order or get the status of that.
 */
public interface OrderModule {

    @NonNull Order.Data create(@NonNull OrderCheckout checkout);

    /**
     * @return get order status.
     */
    @NonNull OrderStatus status(@NonNull Order order);

    /**
     * @return get order status.
     */
    @NonNull OrderStatus status(@NonNull String orderId);

    @NonNull List<OrderEvent> events(@NonNull Order order);

    @NonNull List<OrderEvent> events(@NonNull String orderId);

    static OrderModule initialize(@NonNull UserSession session) {
        return OrderModuleImpl.getInstance(session);
    }
}
