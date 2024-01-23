package org.openifood.client.impl;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Request;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.openifood.client.OrderClient;
import org.openifood.config.InstanceConfig;
import org.openifood.dto.authentication.response.UserSession;
import org.openifood.dto.order.Order;
import org.openifood.dto.order.OrderCheckout;
import org.openifood.dto.order.OrderEvent;
import org.openifood.dto.order.OrderStatus;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.NONE)
public class OrderClientImpl extends WSStoreIFoodClient implements OrderClient {

    private OrderClientImpl(@NonNull InstanceConfig config) {
        super(config);
    }

    private final Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
            .create();

    public static OrderClient getInstance(@NonNull InstanceConfig config) {
        return new OrderClientImpl(config);
    }

    @Override
    public Order create(@NonNull UserSession session, @NonNull OrderCheckout checkout) {
        return evaluate(
                new Request.Builder()
                        .url(resolve("ifood-ws-v3/v6/order/checkout"))
                        .post(body(checkout, gson))
                        .build(),
                Order.class,
                session,
                FieldNamingPolicy.IDENTITY
        );
    }

    @Override
    public OrderStatus status(@NonNull UserSession session, @NonNull String orderId) {
        return evaluate(
                new Request.Builder()
                        .get()
                        .url(resolve("v3/customers/me/orders/" + orderId))
                        .build(),
                OrderStatus.class,
                session,
                FieldNamingPolicy.IDENTITY
        );
    }

    @Override
    public List<OrderEvent> events(@NonNull UserSession session, @NonNull String orderId) {
        return evaluateList(
                new Request.Builder()
                        .get()
                        .url(resolve("v1/customers/me/orders/" + orderId + "/events"))
                        .build(),
                OrderEvent.class,
                session
        );
    }
}
