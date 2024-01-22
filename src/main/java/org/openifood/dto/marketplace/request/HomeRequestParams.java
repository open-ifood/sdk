package org.openifood.dto.marketplace.request;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class HomeRequestParams {
    String latitude;
    String longitude;
    /** default: IFOOD */
    String channel;
    /** Quantity merchants to retrieve per page */
    Integer size;
    /** default: PIX */
    String payment_types;
    /** choose a field to sort (e.g. by rating -> user_rating:asc) */
    String sort;
    String price_range_to;
    String price_range_from;
    /** default: HOME_FOOD_DELIVERY */
    String alias;
}
