package org.openifood.dto.order;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PaymentMethodType {
    String name;
    String description;
}
