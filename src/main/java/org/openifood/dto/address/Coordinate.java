package org.openifood.dto.address;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Coordinate {

    String latitude;
    String longitude;
}
