package org.openifood.dto.marketplace;

import com.google.gson.JsonObject;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SectionCard {
    String id;
    String cardType;
    JsonObject data;
}
