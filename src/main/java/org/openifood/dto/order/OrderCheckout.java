package org.openifood.dto.order;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class OrderCheckout {

    Address address;
    Account account;
    List<RestaurantOrder> restaurantOrder;

    /** information available to delivery man */
    String deliveryAdditionalInfo;

    /** client identification (e.g. sdk_client_v1.0.0) */
    String product;

    /** default: S */
    String medium;

    /** default: SYSTEM */
    String platform;

    Boolean scheduled;

    /** indicates whether the order is a test order */
    Boolean test;

    DeliveryMethod deliveryMethod;
    PaymentSources paymentSources;

    @Value
    @RequiredArgsConstructor
    public static class Address {

        String addressId;
    }

    @Value
    @RequiredArgsConstructor
    public static class Account {

        String id;
    }

}
