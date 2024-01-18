package org.openifood.dto.marketplace;

import lombok.Value;

import java.util.List;

@Value
public class ProductChoice {
    /** choice short code (e.g. 2BCE4C) */
    String code;
    String name;
    Integer min;
    Integer max;
    List<ProductChoiceGarnish> garnishItens;
}
