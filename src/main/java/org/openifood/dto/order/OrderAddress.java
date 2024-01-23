package org.openifood.dto.order;

import lombok.Value;

@Value
public class OrderAddress {

    String uuid;
    Long addressId;
    Integer streetNumber;
    String streetNumberText;
    Location location;
    Boolean favorite;

    @Value
    public static class Location {
        Integer zipCode;
        String address;
        String district;
        String city;
        String state;
        String country;
        /** latitude */
        String lat;
        /** longitude */
        String lon;

        /** if user input must fill address complement field */
        Boolean requireCompl;
    }
}
