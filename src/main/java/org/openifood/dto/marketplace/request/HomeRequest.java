package org.openifood.dto.marketplace.request;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class HomeRequest {
    List<String> supportedHeaders;
    List<String> supportedCards;
    List<String> supportedActions;
}
