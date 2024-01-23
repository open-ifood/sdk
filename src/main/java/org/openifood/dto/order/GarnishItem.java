package org.openifood.dto.order;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class GarnishItem {
    String code;
    Integer qty;
    BigDecimal unitPrice;
}
