package org.openifood.dto.order;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class PaymentSource {

    String source;
    PaymentMethod paymentMethod;
    Amount amount;

    @Value
    @Builder
    public static class Amount {
        BigDecimal value;
        String currency;
    }


}
