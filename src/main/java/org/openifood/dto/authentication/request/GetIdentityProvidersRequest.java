package org.openifood.dto.authentication.request;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GetIdentityProvidersRequest {
    String tenantId;
    Integer countryCode;
    Integer areaCode;
    String phoneNumber;
}
