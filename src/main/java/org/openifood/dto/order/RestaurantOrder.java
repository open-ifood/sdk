package org.openifood.dto.order;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class RestaurantOrder {
    List<Item> itens;
    Restaurant restaurant;
}
