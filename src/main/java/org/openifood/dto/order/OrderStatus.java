package org.openifood.dto.order;

import lombok.Value;

import java.util.Date;

@Value
public class OrderStatus {
    String id;
    String shortId;
    String orderNumber;
    Date createdAt;
    Date updatedAt;
    Date closedAt;
    String lastStatus;
    String handshakeCode;
}
