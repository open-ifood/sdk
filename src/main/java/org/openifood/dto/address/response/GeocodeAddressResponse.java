package org.openifood.dto.address.response;

import lombok.Builder;
import lombok.Value;
import org.openifood.dto.address.GeocodeRule;

import java.util.List;

@Value
@Builder
public class GeocodeAddressResponse {
    List<CustomerAddress> addresses;
    List<GeocodeRule> rules;
    String provider;
}
