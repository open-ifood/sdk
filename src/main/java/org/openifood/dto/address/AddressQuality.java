package org.openifood.dto.address;

import lombok.Value;

@Value
public class AddressQuality {
    Integer city;
    Integer state;
    Integer country;
    Integer streetName;
    Integer streetNumber;
    Integer neighborhood;
    Integer postalCode;
    CoordinateQuality coordinates;
}
