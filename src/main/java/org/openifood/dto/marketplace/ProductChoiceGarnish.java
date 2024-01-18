package org.openifood.dto.marketplace;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class ProductChoiceGarnish {

    /** uuid_v4 garnish representation. */
    String id;

    /** same {@link #id} */
    String code;

    String description;

    /** garnish ingredients */
    String details;

    BigDecimal unitPrice;
}
