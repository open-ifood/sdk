package org.openifood.dto.marketplace;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.util.List;

@Value
@Builder
public class Product {
    String id;
    /** same uuid_v4 at {@link #id} */
    String code;
    String description;
    /** relative path of image on bucket. */
    String logoUrl;
    /** product ingredients */
    String details;
    /** If current project has additional choices (extras, options). */
    Boolean needChoices;
    BigDecimal unitPrice;
    BigDecimal unitMinPrice;
    BigDecimal unitOriginalPrice;
    List<ProductChoice> choices;
}
