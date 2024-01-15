package org.openifood.dto.address.request;

import lombok.Builder;
import lombok.Value;
import org.openifood.dto.address.Coordinate;

@Value
@Builder
public class CreateAddressRequest {
    String city;
    String state;
    String country;
    String streetName;
    String streetNumber;
    String neighborhood;
    Coordinate coordinates;
    Integer portalCode;
    String reference;
    String complement;
    String provider;
}
