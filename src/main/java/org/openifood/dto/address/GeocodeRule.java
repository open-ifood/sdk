package org.openifood.dto.address;

import lombok.Value;

@Value
public class GeocodeRule {
    String key;
    String localizedLabel;
    String required;
}
