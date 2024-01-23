package org.openifood.dto.order;

import lombok.Value;

import java.util.Date;

@Value
public class OrderEvent {

    String orderId;
    Date timestamp;
    Date processedAt;

    /** Status: (PLACED -> CONFIRMED -> HANDSHAKE_CODE_VALUE -> DISPATCHED -> RECEIVED_BY_CUSTOMER -> CONCLUDED) */
    String value;

}
