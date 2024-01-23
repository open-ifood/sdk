package org.openifood.dto.order;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PaymentMethod {
    String id;
    PaymentMethodType type;
    String name;
    Method method;
    String liability;
    Brand brand;

    @Value
    @Builder
    public static class Method {
        String name;
        String description;
    }
}