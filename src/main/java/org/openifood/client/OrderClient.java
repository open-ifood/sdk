package org.openifood.client;

import lombok.NonNull;
import org.openifood.client.impl.OrderClientImpl;
import org.openifood.config.InstanceConfig;
import org.openifood.dto.authentication.response.UserSession;
import org.openifood.dto.order.Order;
import org.openifood.dto.order.OrderCheckout;
import org.openifood.dto.order.OrderEvent;
import org.openifood.dto.order.OrderStatus;

import java.util.List;

public interface OrderClient {

    Order create(@NonNull UserSession session, @NonNull OrderCheckout checkout);

    OrderStatus status(@NonNull UserSession session, @NonNull String orderId);

    List<OrderEvent> events(@NonNull UserSession session, @NonNull String orderId);

    static OrderClient initialize(@NonNull InstanceConfig instanceConfig) {
        return OrderClientImpl.getInstance(instanceConfig);
    }

}
