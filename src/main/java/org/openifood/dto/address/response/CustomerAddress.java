package org.openifood.dto.address.response;

import lombok.Value;
import org.openifood.dto.address.Coordinate;

import java.util.Date;

@Value
public class CustomerAddress {
    String id;
    String externalId;
    String accountId;
    String alias;
    Boolean favorite;
    String country;
    String state;
    String city;
    String neighborhood;
    String streetName;
    String streetNumber;
    String complement;
    String reference;
    String postalCode;
    String establishment;
    Coordinate coordinates;
    String locationId;
    String provider;
    Date createdAt;
    Date updatedAt;
}
