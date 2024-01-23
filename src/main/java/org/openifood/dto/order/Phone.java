package org.openifood.dto.order;

import lombok.Value;

@Value
public class Phone {
    Long id;
    String areaCode;
    String countryCode;
    String phone;
    String fullNumber;
}
