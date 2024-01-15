package org.openifood.dto.address;

import lombok.Value;

@Value
public class Address {
    String city;
    String state;
    String country;
    String streetName;
    String neighborhood;
    Coordinate coordinates;
    AddressQuality quality;
}
