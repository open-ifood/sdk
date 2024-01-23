package org.openifood.dto.order;

import lombok.Value;

@Value
public class Order {

    String code;
    Data data;

    @Value
    public static class Data {
        OrderCheckout orderCheckout;
    }

    @Value
    public static class OrderCheckout {

        String id;
        String shortId;
        Integer date;
        Integer deliveryDate;
        Integer expectedTakeOutDate;
        Integer expectedDeliveryTime;
        Boolean scheduled;
        /** Observation (additional info) */
        String obs;
        Double deliveryFee;
        Double discount;
        Double totalAmount;
        Integer estimatedDeliveryTime;
        String browser;
        String product;

        Customer customer;
        OrderAddress address;
    }

    // TODO: save temporary user session
    // TODO: public method for getting order status
}
