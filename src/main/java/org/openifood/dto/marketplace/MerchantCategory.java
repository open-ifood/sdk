package org.openifood.dto.marketplace;

import lombok.Value;

import java.util.List;

@Value
public class MerchantCategory {
    /** Category UUID_V4 identification. */
    String code;
    String name;
    List<Product> itens;
}
