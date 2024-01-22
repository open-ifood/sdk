package org.openifood.dto.marketplace;

import lombok.Builder;

@Builder
public class Merchant {

    String id;
    String action;
    Boolean available;
    String currency;
    Double distance;
    String imageUrl;
    Boolean isIfoodDelivery;
    Boolean isNew;
    Boolean isSuperRestaurant;
    String mainCategory;
    String name;
    String formattedName;
    Boolean supportsTracking;
    Double userRating;
    Boolean isFavorite;

    // TODO: public method for getting catalog from here
}
