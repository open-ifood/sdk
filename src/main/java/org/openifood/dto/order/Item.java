package org.openifood.dto.order;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.util.List;

@Value
@Builder
public class Item {
    String code;
    BigDecimal unitPrice;
    String obs;
    Integer qty;
    List<ItemChoice> choices;
}
