package org.openifood.dto.marketplace;

import lombok.Builder;
import lombok.Value;
import org.openifood.dto.address.Coordinate;

import java.util.List;

@Value
@Builder
public class MerchantFilter {
    Coordinate coordinate;
    Integer size;
    List<String> paymentTypes;
    String sort;
}
