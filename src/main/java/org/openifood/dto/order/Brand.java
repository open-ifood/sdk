package org.openifood.dto.order;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Brand {
    String id;
    String name;
    String description;
}
